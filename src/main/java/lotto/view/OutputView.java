package lotto.view;

import java.util.stream.Collectors;
import lotto.dto.LottoResult;

public class OutputView {
    public void printGeneratedLotto(LottoResult lottoResult) {
        System.out.printf(Messages.BUY_COUNT_MESSAGE + "%n", lottoResult.buyAmount());
        System.out.println("[" + lottoResult.lottos().stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")) + "]");
    }
}
