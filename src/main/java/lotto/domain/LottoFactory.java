package lotto.domain;

import java.util.List;

public class LottoFactory {
    private final LottoGenerator lottoGenerator;

    public LottoFactory(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public Lotto generateLotto() {
        List<Integer> numbers = lottoGenerator.generateLotto();
        return new Lotto(numbers);
    }
}
