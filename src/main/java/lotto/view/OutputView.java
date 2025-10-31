package lotto.view;

import lotto.dto.LottoResult;

public class OutputView {
    public void printGeneratedLotto(LottoResult lottoResult) {
        System.out.println(String.format(Messages.BUY_COUNT_MESSAGE, lottoResult.buyAmount()));
        lottoResult.lottos().forEach(System.out::println);
    }
}
