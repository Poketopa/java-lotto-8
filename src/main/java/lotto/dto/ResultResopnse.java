package lotto.dto;

import java.math.BigDecimal;
import java.util.Map;
import lotto.domain.BonusNumber;
import lotto.domain.Prize;

public record WinningResult(Map<Prize, Integer> winningResult, BigDecimal profitRate) {
}
