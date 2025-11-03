package lotto;

import lotto.domain.*;
import lotto.dto.ResultResopnse;
import lotto.service.LottoService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.*;

public class LottoServiceTest {
    private LottoService serviceWithGenerator(List<List<Integer>> predefined) {
        AtomicInteger idx = new AtomicInteger(0);
        LottoGenerator generator = () -> predefined.get(idx.getAndIncrement());
        return new LottoService(new LottoFactory(generator));
    }

    @Test
    void buyLottos_형식오류는_IllegalArgumentException으로_변환() {
        LottoService service = serviceWithGenerator(List.of(List.of(1, 2, 3, 4, 5, 6)));
        assertThatThrownBy(() -> service.buyLottos("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void buyLottos_정상적으로_장수만큼_생성() {
        LottoService service = serviceWithGenerator(List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 8, 9, 10, 11, 12),
                List.of(13, 14, 15, 16, 17, 18)
        ));

        Lottos lottos = service.buyLottos("3000");

        assertThat(lottos.getBuyAmout()).isEqualTo(3);
        assertThat(lottos.getLottos().get(0).getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(lottos.getLottos().get(1).getNumbers()).containsExactly(7, 8, 9, 10, 11, 12);
        assertThat(lottos.getLottos().get(2).getNumbers()).containsExactly(13, 14, 15, 16, 17, 18);
    }

    @Test
    void createWinningNumbers_정상() {
        LottoService service = serviceWithGenerator(List.of(List.of(1, 2, 3, 4, 5, 6)));
        WinningNumbers winningNumbers = service.createWinningNumbers("1,2,3,4,5,6");
        assertThat(winningNumbers.contains(1)).isTrue();
        assertThat(winningNumbers.contains(7)).isFalse();
    }

    @Test
    void createBonusNumber_당첨번호와_중복시_예외() {
        LottoService service = serviceWithGenerator(List.of(List.of(1, 2, 3, 4, 5, 6)));
        WinningNumbers winningNumbers = service.createWinningNumbers("1,2,3,4,5,6");
        assertThatThrownBy(() -> service.createBonusNumber("6", winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void getTotalPrize_계산() {
        LottoService service = serviceWithGenerator(List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 7),
                List.of(1, 2, 3, 4, 5, 8)
        ));
        Lottos lottos = service.buyLottos("3000");
        WinningNumbers winningNumbers = service.createWinningNumbers("1,2,3,4,5,6");
        BonusNumber bonusNumber = service.createBonusNumber("7", winningNumbers);

        ResultResopnse resultResopnse = service.getTotalPrize(lottos, winningNumbers, bonusNumber);

        assertThat(resultResopnse.winningResult().get(Prize.PRIZE_FIRST)).isEqualTo(1);
        assertThat(resultResopnse.winningResult().get(Prize.PRIZE_SECOND)).isEqualTo(1);
        assertThat(resultResopnse.winningResult().get(Prize.PRIZE_THIRD)).isEqualTo(1);
        assertThat(resultResopnse.profitRate()).isEqualByComparingTo(new BigDecimal("67716666.67"));
    }
}
