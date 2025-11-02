package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import lotto.util.InputParser;
import org.junit.jupiter.api.Test;

class InputParserTest {
    @Test
    void parseWinningNumbers_정상() {
        List<Integer> list = InputParser.parseWinningNumbers("1, 2,3, 4,5,6");
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void parseWinningNumbers_null_예외() {
        assertThatThrownBy(() -> InputParser.parseWinningNumbers(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void parseWinningNumbers_형식오류_예외() {
        assertThatThrownBy(() -> InputParser.parseWinningNumbers("1, two, 3, 4, 5, 6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void parseBonusNumber_형식오류_예외() {
        assertThatThrownBy(() -> InputParser.parseBonusNumber("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void parseBuyAmount_정상() {
        int amount = InputParser.parseBuyAmount("8000");
        assertThat(amount).isEqualTo(8000);
    }
}
