package lotto.view;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.Prize;
import lotto.dto.LottoResult;
import lotto.dto.ResultResopnse;

public class OutputView {
    public void printGeneratedLotto(LottoResult lottoResult) {
        System.out.printf(Messages.BUY_COUNT_MESSAGE + "%n", lottoResult.buyAmount());
        lottoResult.lottos().forEach(System.out::println);
    }

    public void printLottoResult(ResultResopnse resultResopnse){
        Map<Prize, Integer> lottoResult = resultResopnse.winningResult().getResult();
        BigDecimal profitRate = resultResopnse.profitRate();

        System.out.println(Messages.WINNING_STAT_MESSAGE);
        System.out.println(String.format(Messages.PRIZE_FIFTH, lottoResult.getOrDefault(Prize.PRIZE_FIFTH, 0)));
        System.out.println(String.format(Messages.PRIZE_FOURTH, lottoResult.getOrDefault(Prize.PRIZE_FOURTH, 0)));
        System.out.println(String.format(Messages.PRIZE_THIRD, lottoResult.getOrDefault(Prize.PRIZE_THIRD, 0)));
        System.out.println(String.format(Messages.PRIZE_SECOND, lottoResult.getOrDefault(Prize.PRIZE_SECOND, 0)));
        System.out.println(String.format(Messages.PRIZE_FIRST, lottoResult.getOrDefault(Prize.PRIZE_FIRST, 0)));
    }
}
