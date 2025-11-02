package lotto.util;

import java.util.Arrays;
import java.util.List;
import lotto.exception.ErrorCode;

public class InputParser {
    private InputParser(){}
    private static final String COMMA = ",";

    public static int parseBuyAmount(String buyAmount){
        return Integer.parseInt(buyAmount);
    }

    public static List<Integer> parseWinningNumbers(String winningNumbers){
        if (winningNumbers == null) {
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT_FORMAT.message());
        }

        try {
            return Arrays.stream(winningNumbers.split(COMMA))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT_FORMAT.message());
        }
    }

    public static int parseBonusNumber(String bonusNumber){
        try{
            return Integer.parseInt(bonusNumber);
        } catch(NumberFormatException e){
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT_FORMAT.message());
        }
    }
}
