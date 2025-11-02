package lotto.domain;

import java.util.List;

public class WinningNumbers {
    private final List<Integer> winningNumbers;

    public WinningNumbers(List<Integer> winningNumbers) {
        validate(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    private void validate(List<Integer> winningNumbers){

    }

    public boolean contains(int inputNumber){
        if(winningNumbers.contains(inputNumber)){
            return true;
        }
        return false;
    }
}
