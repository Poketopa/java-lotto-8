package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.domain.BonusNumber;
import lotto.domain.BuyAmount;
import org.junit.jupiter.api.Test;

class BonusNumberTest {
    @Test
    void 보너스번호_범위_위반_예외() {
        assertThatThrownBy(() -> new BonusNumber(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
        assertThatThrownBy(() -> new BonusNumber(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 보너스번호_정상() {
        BonusNumber bonusNumber = new BonusNumber(7);
        assertThat(bonusNumber.getBonusNumber()).isEqualTo(7);
    }
}
