package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.HashMap;
import java.util.Map;
import lotto.domain.Prize;
import lotto.domain.WinningResult;
import org.junit.jupiter.api.Test;

public class WinningResultTest {
    @Test
    void put_get_checkPrize_검증() {
        WinningResult result = new WinningResult(new HashMap<>());
        result.put(Prize.PRIZE_FIRST);
        result.put(Prize.PRIZE_FIRST);
        result.put(Prize.PRIZE_THIRD);

        assertThat(result.checkPrize(Prize.PRIZE_FIRST)).isTrue();
        assertThat(result.checkPrize(Prize.PRIZE_SECOND)).isFalse();
        assertThat(result.get(Prize.PRIZE_FIRST)).isEqualTo(2);
        assertThat(result.get(Prize.PRIZE_THIRD)).isEqualTo(1);
    }

    @Test
    void getResult_방어적_복사_확인() {
        WinningResult result = new WinningResult(new HashMap<>());
        result.put(Prize.PRIZE_FIFTH);

        Map<Prize, Integer> copy = result.getResult();
        copy.put(Prize.PRIZE_SECOND, 5);

        assertThat(result.checkPrize(Prize.PRIZE_SECOND)).isFalse();
    }
}
