package lotto.util;

import java.util.Arrays;
import java.util.List;
import lotto.exception.ErrorCode;

public class InputParser {
    private InputParser(){}

    public static int parseBuyAmount(String buyAmount){
        return Integer.parseInt(buyAmount);
    }

    public static List<Integer> parseWinningNumbers(String winningNumbers){
        if (winningNumbers == null) {
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT_FORMAT.message());
        }

        try {
            return Arrays.stream(winningNumbers.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT_FORMAT.message());
        }
    }

    public static int parseBonusNumber(String bonusNumber){
        int parsedBonusNumber;
        try{
            parsedBonusNumber = Integer.parseInt(bonusNumber);
        } catch(NumberFormatException e){
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT_FORMAT.message());
        }
        return Integer.parseInt(bonusNumber);
    }
}
