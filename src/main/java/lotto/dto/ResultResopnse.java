package lotto.dto;

import java.math.BigDecimal;
import lotto.domain.WinningResult;

public record ResultResopnse(WinningResult winningResult, BigDecimal profitRate) {
}
