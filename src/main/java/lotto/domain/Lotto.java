package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.exception.ErrorCode;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        checkLottoNumberSize(numbers);
        checkLottoNumberRange(numbers);
        checkLottoNumberDuplication(numbers);
    }

    private void checkLottoNumberSize(List<Integer> numbers) {
        if (numbers.size() != LottoInfo.LOTTO_SIZE) {
            throw new IllegalArgumentException(ErrorCode.INVALID_LOTTO_NUMBER_SIZE.message());
        }
    }

    private void checkLottoNumberRange(List<Integer> numbers) {
        for (int lottoNumber : numbers) {
            if (lottoNumber < LottoInfo.LOTTO_MIN || lottoNumber > LottoInfo.LOTTO_MAX) {
                throw new IllegalArgumentException(ErrorCode.INVALID_LOTTO_NUMBER_RANGE.message());
            }
        }
    }

    private void checkLottoNumberDuplication(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>(numbers);
        if (set.size() != LottoInfo.LOTTO_SIZE) {
            throw new IllegalArgumentException(ErrorCode.LOTTO_NUMBER_DUPLICATION.message());
        }
    }

    public boolean contains(int inputNumber) {
        if (numbers.contains(inputNumber)) {
            return true;
        }
        return false;
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }
}
