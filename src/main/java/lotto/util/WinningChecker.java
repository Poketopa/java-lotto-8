package lotto.util;

import lotto.domain.Prize;

public class WinningChecker {
    private WinningChecker(){}

    public static Prize checkWinning(int correctNumberCount, boolean isBonusNumberCorrect){
        if(correctNumberCount == 6){
            return Prize.PRIZE_FIRST;
        }
        if(correctNumberCount == 5 && isBonusNumberCorrect){
            return Prize.PRIZE_SECOND;
        }
        if(correctNumberCount == 5){
            return Prize.PRIZE_THIRD;
        }
        if(correctNumberCount == 4){
            return Prize.PRIZE_FOURTH;
        }
        if(correctNumberCount == 3){
            return Prize.PRIZE_FIFTH;
        }
        return Prize.NO_PRIZE;
    }
}
