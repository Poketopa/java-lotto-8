package lotto.domain;

import lotto.exception.ErrorCode;

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
            throw new IllegalArgumentException(ErrorCode.NEGATIVE_BUY_AMOUNT.message());
        }
    }

    private void checkMultipleOfThousand(int buyAmount){
        if(buyAmount % LottoInfo.LOTTO_PRICE != 0){
            throw new IllegalArgumentException(ErrorCode.BUY_PRICE_IS_NOT_MULTIPLE_OF_THOUSAND.message());
        }
    }

    public int getBuyAmount(){
        return buyAmount / LottoInfo.LOTTO_PRICE;
    }
}
