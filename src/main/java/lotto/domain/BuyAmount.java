package lotto.domain;

public class BuyAmount {
    private final int buyAmount;

    public BuyAmount(int buyAmount) {
        validate(buyAmount);
        this.buyAmount = buyAmount;
    }

    private void validate(int buyAmount){

    }
}
