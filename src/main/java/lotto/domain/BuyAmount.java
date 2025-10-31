package lotto.domain;

public class BuyAmount {
    private final int buyAmount;

    public BuyAmount(int buyAmount) {
        validate(buyAmount);
        this.buyAmount = buyAmount;
    }

    private void validate(int buyAmount){
        checkNegativeNumber(buyAmount);
        checkMultipleOfThousand(buyAmount);
    }

    private void checkNegativeNumber(int buyAmount){
        if(buyAmount <= 0){
            throw new IllegalArgumentException();
        }
    }

    private void checkMultipleOfThousand(int buyAmount){
        if(buyAmount % 1000 != 0){
            throw new IllegalArgumentException();
        }
    }

    public int getBuyAmount(){
        return buyAmount;
    }
}
