package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String inputBuyAmount(){
        System.out.println(Messages.BUY_AMOUNT_INPUT_MESSAGE);
        return Console.readLine();
    }

    public String inputWinningNumber(){
        System.out.println(Messages.WINNING_NUMBER_INPUT_MESSAGE);
        return Console.readLine();
    }

    public String inputBonusNumber(){
        System.out.println(Messages.BONUS_NUMBER_INPUT_MESSAGE);
        return Console.readLine();
    }
}
