package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.exception.ErrorCode;

public class WinningNumbers {
    private final List<Integer> winningNumbers;

    public WinningNumbers(List<Integer> winningNumbers) {
        validate(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    private void validate(List<Integer> winningNumbers){
        checkWinningNumberRange(winningNumbers);
        checkWinningNumberSize(winningNumbers);
        checkWinningNumberDuplication(winningNumbers);
    }

    private void checkWinningNumberRange(List<Integer> winningNumbers){
        for(int winningNumber : winningNumbers){
            if(winningNumber < 1 || winningNumber > 45){
                throw new IllegalArgumentException(ErrorCode.INVALID_WINNING_NUMBER_RANGE.message());
            }
        }
    }

    private void checkWinningNumberSize(List<Integer> winningNumbers) {
        if(winningNumbers.size() != 6){
            throw new IllegalAccessError(ErrorCode.INVALID_WINNING_NUMBER_SIZE.message());
        }
    }

    private void checkWinningNumberDuplication(List<Integer> winningNumbers){
        Set<Integer> set = new HashSet<>(winningNumbers);
        if(set.size() != 6){
            throw new IllegalArgumentException(ErrorCode.WINNING_NUMBER_DUPLICATION.message());
        }
    }

    public boolean contains(int inputNumber){
        if(winningNumbers.contains(inputNumber)){
            return true;
        }
        return false;
    }
}
