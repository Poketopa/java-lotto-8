package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomNumberGenerator implements LottoGenerator {
    @Override
    public List<Integer> generateLotto() {
        return Randoms.pickUniqueNumbersInRange(LottoInfo.LOTTO_MIN, LottoInfo.LOTTO_MAX, LottoInfo.LOTTO_SIZE);
    }
}
