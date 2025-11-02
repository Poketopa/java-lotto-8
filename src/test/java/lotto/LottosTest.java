package lotto;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import org.junit.jupiter.api.Test;

public class LottosTest {
    @Test
    void 문자열_출력용_정렬_포맷_검증() {
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(6, 1, 5, 2, 4, 3))
        ));
        assertThat(lottos.getLottosToString())
                .containsExactly("[1, 2, 3, 4, 5, 6]");
    }

    @Test
    void 구매_장수_반환() {
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(7, 8, 9, 10, 11, 12)),
                new Lotto(List.of(13, 14, 15, 16, 17, 18))
        ));
        assertThat(lottos.getBuyAmout()).isEqualTo(3);
    }
}
