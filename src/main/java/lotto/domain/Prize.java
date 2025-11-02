package lotto.domain;

public enum Prize {
    PRIZE_FIRST(2000000000L),
    PRIZE_SECOND(30000000L),
    PRIZE_THIRD(1500000L),
    PRIZE_FOURTH(50000L),
    PRIZE_FIFTH(5000L),
    NO_PRIZE(0L);

    private final long prizeMoney;

    Prize(long prizeMoney) {
        this.prizeMoney = prizeMoney;
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }
}
