package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.domain.BuyAmount;
import org.junit.jupiter.api.Test;

class BuyAmountTest {
    @Test
    void 구입금액이_0_이하이면_예외() {
        assertThatThrownBy(() -> new BuyAmount(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
        assertThatThrownBy(() -> new BuyAmount(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 구입금액이_1000의_배수가_아니면_예외() {
        assertThatThrownBy(() -> new BuyAmount(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 구입금액을_로또_장수로_환산() {
        BuyAmount buyAmount = new BuyAmount(8000);
        assertThat(buyAmount.getBuyAmount()).isEqualTo(8);
    }
}
