package lotto.util;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    private InputParser(){}

    public static int parseBuyAmount(String buyAmount){
        return Integer.parseInt(buyAmount);
    }

    public static List<Integer> parseWinningNumbers(String winningNumbers){
        return Arrays.stream(winningNumbers.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList();
    }

    public static int parseBonusNumber(String bonusNumber){
        return Integer.parseInt(bonusNumber);
    }
}
