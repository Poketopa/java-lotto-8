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
        System.out.println("[" + lottoResult.lottos().stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")) + "]");
    }

    public void printLottoResult(ResultResopnse resultResopnse){
        Map<Prize, Integer> lottoResult = resultResopnse.winningResult().getResult();
        BigDecimal profitRate = resultResopnse.profitRate();

        System.out.println(Messages.WINNING_STAT_MESSAGE);
        System.out.println(String.format(Messages.PRIZE_FIFTH, lottoResult.get(Prize.PRIZE_FIFTH)));
        System.out.println(String.format(Messages.PRIZE_FOURTH, lottoResult.get(Prize.PRIZE_FOURTH)));
        System.out.println(String.format(Messages.PRIZE_THIRD, lottoResult.get(Prize.PRIZE_THIRD)));
        System.out.println(String.format(Messages.PRIZE_SECOND, lottoResult.get(Prize.PRIZE_SECOND)));
        System.out.println(String.format(Messages.PRIZE_FIRST, lottoResult.get(Prize.PRIZE_FIRST)));
    }
}
