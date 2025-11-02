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
import lotto.domain.LottoInfo;
import lotto.domain.Lottos;
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
    private static final int ZERO = 0;

    public LottoService(LottoFactory lottoFactory) {
        this.lottoFactory = lottoFactory;
    }

    public Lottos buyLottos(String rawBuyAmount) {
        BuyAmount buyAmount;
        try {
            buyAmount = new BuyAmount(InputParser.parseBuyAmount(rawBuyAmount));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT_FORMAT.message());
        }

        List<Lotto> lottos = new ArrayList<>();
        int lottoCount = buyAmount.getBuyAmount();
        createLottos(lottoCount, lottos);

        return new Lottos(lottos);
    }

    private void createLottos(int lottoCount, List<Lotto> lottos) {
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(lottoFactory.generateLotto());
        }
    }

    public WinningNumbers createWinningNumbers(String rawWinningNumbers) {
        return new WinningNumbers(InputParser.parseWinningNumbers(rawWinningNumbers));
    }

    public BonusNumber createBonusNumber(String rawBonusNumber, WinningNumbers winningNumbers) {
        int bonusNumber = InputParser.parseBonusNumber(rawBonusNumber);
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorCode.BONUS_NUMBER_DUPLICATED_WITH_WINNING_NUMBER.message());
        }
        return new BonusNumber(bonusNumber);
    }

    public ResultResopnse getTotalPrize(Lottos lottos, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        WinningResult winningResult = new WinningResult(new HashMap<>());
        List<Lotto> lottoList = lottos.getLottos();
        checkResult(winningNumbers, bonusNumber, lottoList, winningResult);

        long totalWinningPrize = ZERO;
        int totalLottoBuyPrize = LottoInfo.LOTTO_PRICE * lottos.getBuyAmout();
        totalWinningPrize = getTotalWinningPrize(totalWinningPrize, winningResult);
        BigDecimal profitRate = BigDecimal.valueOf(totalWinningPrize)
                .divide(BigDecimal.valueOf(totalLottoBuyPrize), DECIMAL_POINT, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(HUNDRED));

        return new ResultResopnse(winningResult, profitRate);
    }

    private static long getTotalWinningPrize(long totalWinningPrize, WinningResult winningResult) {
        for (Prize prize : Prize.values()) {
            totalWinningPrize = getTotalWinningPrize(prize, winningResult, totalWinningPrize);
        }
        return totalWinningPrize;
    }

    private static void checkResult(WinningNumbers winningNumbers, BonusNumber bonusNumber, List<Lotto> lottoList,
                                    WinningResult winningResult) {
        for (Lotto lotto : lottoList) {
            checkResult(winningNumbers, bonusNumber, lotto, winningResult);
        }
    }

    private static void checkResult(WinningNumbers winningNumbers, BonusNumber bonusNumber, Lotto lotto,
                                    WinningResult winningResult) {
        boolean isBonusNumberCorrect = checkBonusNumber(bonusNumber, lotto);
        int correctNumberCount = checkCorrectNumber(winningNumbers, lotto);
        Prize result = WinningChecker.checkWinning(correctNumberCount, isBonusNumberCorrect);
        winningResult.put(result);
    }

    private static long getTotalWinningPrize(Prize prize, WinningResult winningResult, long totalWinningPrize) {
        if (winningResult.checkPrize(prize)) {
            totalWinningPrize += winningResult.get(prize) * prize.getPrizeMoney();
        }
        return totalWinningPrize;
    }

    private static int checkCorrectNumber(WinningNumbers winningNumbers, Lotto lotto) {
        int correctNumberCount = 0;
        correctNumberCount = getCorrectNumberCount(winningNumbers, lotto, correctNumberCount);
        return correctNumberCount;
    }

    private static int getCorrectNumberCount(WinningNumbers winningNumbers, Lotto lotto, int correctNumberCount) {
        for (int lottoNumber : lotto.getNumbers()) {
            correctNumberCount = checkWinningNumbersContainsLottoNumber(winningNumbers, lottoNumber,
                    correctNumberCount);
        }
        return correctNumberCount;
    }

    private static int checkWinningNumbersContainsLottoNumber(WinningNumbers winningNumbers, int lottoNumber,
                                                              int correctNumberCount) {
        if (winningNumbers.contains(lottoNumber)) {
            correctNumberCount += 1;
        }
        return correctNumberCount;
    }

    private static boolean checkBonusNumber(BonusNumber bonusNumber, Lotto lotto) {
        boolean isBonusNumberCorrect = false;
        if (lotto.contains(bonusNumber.getBonusNumber())) {
            isBonusNumberCorrect = true;
        }
        return isBonusNumberCorrect;
    }
}
