package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class WinningResult {
    private final Map<Prize, Integer> winningResult;

    public WinningResult(Map<Prize, Integer> winningResult) {
        this.winningResult = winningResult;
    }

    public void put(Prize prize) {
        winningResult.put(prize, winningResult.getOrDefault(prize, 0) + 1);
    }

    public int get(Prize prize) {
        return winningResult.get(prize);
    }

    public boolean checkPrize(Prize prize) {
        if (winningResult.containsKey(prize)) {
            return true;
        }
        return false;
    }

    public Map<Prize, Integer> getResult() {
        return new HashMap<>(winningResult);
    }
}
