package lotto.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.BuyAmount;
import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.Lottos;
import lotto.domain.Prize;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningResult;
import lotto.dto.ResultResopnse;
import lotto.util.InputParser;
import lotto.util.WinningChecker;

public class LottoService {
    private final LottoFactory lottoFactory;

    public LottoService(LottoFactory lottoFactory) {
        this.lottoFactory = lottoFactory;
    }

    public Lottos buyLotto(String rawBuyAmount){
        BuyAmount buyAmount = new BuyAmount(InputParser.parseBuyAmount(rawBuyAmount));

        int lottoCount = buyAmount.getBuyAmount();
        List<Lotto> lottos = new ArrayList<>();
        for(int i=0;i<lottoCount;i++){
            lottos.add(lottoFactory.generateLotto());
        }
        return new Lottos(lottos);
    }

    public WinningNumbers createWinningNumbers(String rawWinningNumbers){
        String[] rawWinningNumbersList = rawWinningNumbers.split(",");
        return new WinningNumbers(Arrays.stream(rawWinningNumbersList)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList());
    }

    public BonusNumber createBonusNumber(String rawBonusNumber){
        int bonusNumber = Integer.parseInt(rawBonusNumber);
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
        int totalLottoBuyPrize = 1000 * lottos.getBuyAmout();
        for(Prize prize : Prize.values()){
            if(winningResult.checkPrize(prize)){
                totalWinningPrize += winningResult.get(prize) * prize.getPrizeMoney();
            }
        }
        BigDecimal profitRate = BigDecimal.valueOf(totalWinningPrize)
                .divide(BigDecimal.valueOf(totalLottoBuyPrize), 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
        return new ResultResopnse(winningResult, profitRate);
    }
}
