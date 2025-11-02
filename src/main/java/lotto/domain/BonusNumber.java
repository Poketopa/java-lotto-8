package lotto.domain;

import lotto.exception.ErrorCode;

public class BonusNumber {
    private final int bonusNumber;

    public BonusNumber(int bonusNumber) {
        validate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validate(int bonusNumber){
        checkRange(bonusNumber);
    }

    private void checkRange(int bonusNumber){
        if(bonusNumber < 1 || bonusNumber > 45){
            throw new IllegalArgumentException(ErrorCode.INVALID_BONUS_NUMBER_RANGE.message());
        }
    }

    public int getBonusNumber(){
        return bonusNumber;
    }
}
