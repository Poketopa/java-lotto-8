package lotto.dto;

import java.math.BigDecimal;
import java.util.Map;
import lotto.domain.Prize;
import lotto.domain.WinningResult;

public record ResultResopnse(WinningResult winningResult, BigDecimal profitRate) {
}
