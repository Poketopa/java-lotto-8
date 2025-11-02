package lotto.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.BuyAmount;
import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.Lottos;
import lotto.domain.LottoInfo;
import lotto.domain.Prize;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningResult;
import lotto.dto.ResultResopnse;
import lotto.exception.ErrorCode;
import lotto.util.InputParser;
import lotto.util.WinningChecker;

public class LottoService {
    private final LottoFactory lottoFactory;
    private static final int DECIMAL_POINT = 2;
    private static final int HUNDRED = 100;

    public LottoService(LottoFactory lottoFactory) {
        this.lottoFactory = lottoFactory;
    }

    public Lottos buyLottos(String rawBuyAmount){
        BuyAmount buyAmount;
        try{
            buyAmount = new BuyAmount(InputParser.parseBuyAmount(rawBuyAmount));
        } catch(NumberFormatException e){
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT_FORMAT.message());
        }

        int lottoCount = buyAmount.getBuyAmount();
        List<Lotto> lottos = new ArrayList<>();
        for(int i=0;i<lottoCount;i++){
            lottos.add(lottoFactory.generateLotto());
        }
        return new Lottos(lottos);
    }

    public WinningNumbers createWinningNumbers(String rawWinningNumbers){
        return new WinningNumbers(InputParser.parseWinningNumbers(rawWinningNumbers));
    }

    public BonusNumber createBonusNumber(String rawBonusNumber, WinningNumbers winningNumbers){
        int bonusNumber = InputParser.parseBonusNumber(rawBonusNumber);
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorCode.BONUS_NUMBER_DUPLICATED_WITH_WINNING_NUMBER.message());
        }
        return new BonusNumber(bonusNumber);
    }

    public ResultResopnse getTotalPrize(Lottos lottos, WinningNumbers winningNumbers, BonusNumber bonusNumber){
        // 열거형 맵 만들어서 한개씩 확인하는 메서드 필요
        WinningResult winningResult = new WinningResult(new HashMap<>());
        List<Lotto> lottoList = lottos.getLottos();
        for(Lotto lotto : lottoList){
            int correctNumberCount = 0;
            boolean isBonusNumberCorrect = false;
            if(lotto.contains(bonusNumber.getBonusNumber())){
                isBonusNumberCorrect = true;
            }
            for(int lottoNumber : lotto.getNumbers()){
                if(winningNumbers.contains(lottoNumber)){
                    correctNumberCount += 1;
                }
            }
            Prize result = WinningChecker.checkWinning(correctNumberCount, isBonusNumberCorrect);
            winningResult.put(result);
        }
        // 수익률 계산
        long totalWinningPrize = 0;
        int totalLottoBuyPrize = LottoInfo.LOTTO_PRICE * lottos.getBuyAmout();
        for(Prize prize : Prize.values()){
            if(winningResult.checkPrize(prize)){
                totalWinningPrize += winningResult.get(prize) * prize.getPrizeMoney();
            }
        }
        BigDecimal profitRate = BigDecimal.valueOf(totalWinningPrize)
                .divide(BigDecimal.valueOf(totalLottoBuyPrize), DECIMAL_POINT, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(HUNDRED));
        return new ResultResopnse(winningResult, profitRate);
    }
}
