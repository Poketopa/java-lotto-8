package lotto.exception;

public enum ErrorCode {
    INVALID_INPUT_FORMAT("[ERROR] 입력 형식이 올바르지 않습니다."),
    INVALID_LOTTO_NUMBER_SIZE("[ERROR] 로또 번호의 개수는 6개여야 합니다."),
    INVALID_LOTTO_NUMBER_RANGE("[ERROR] 로또 번호는 1이상 45이하의 정수여야 합니다."),
    LOTTO_NUMBER_DUPLICATION("[ERROR] 로또 번호는 중복되지 않는 6자리 숫자여야 합니다."),
    INVALID_WINNING_NUMBER_SIZE("[ERROR] 당첨 번호의 개수는 6개여야 합니다."),
    INVALID_BONUS_NUMBER_RANGE("[ERROR] 보너스 번호는 1이상 45이하의 정수여야 합니다."),
    INVALID_WINNING_NUMBER_RANGE("[ERROR] 당첨 번호는 1이상 45이하의 정수여야 합니다."),
    WINNING_NUMBER_DUPLICATION("[ERROR] 당첨 번호는 중복되지 않는 6자리 숫자여야 합니다."),
    NEGATIVE_BUY_AMOUNT("[ERROR] 구입 금액은 양의 정수여야 합니다."),
    WINNING_NUMBER_IS_NOT_MULTIPLE_OF_THOUSAND("[ERROR] 당첨 금액은 1,000으로 나누어 떨어져야 합니다."),
    BONUS_NUMBER_DUPLICATED_WITH_WINNING_NUMBER("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");






    private final String message;
    ErrorCode(String message) {
        this.message = message;
    }
    public String message() {
        return message;
    }
}
