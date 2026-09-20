package com.momo.domain.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Money implements Comparable<Money> {
    public static final Money ZERO = new Money(BigDecimal.ZERO); 
    private final BigDecimal value;

    public Money(BigDecimal value) {
        this.value = value;
    }

    public static Money of(String raw) {
        Objects.requireNonNull(raw, "amount");
        try {
            BigDecimal parsed = new BigDecimal(raw.trim());
            if (parsed.scale() > 0) {
                parsed = parsed.setScale(0, RoundingMode.UNNECESSARY);
            }
            if (parsed.signum() < 0) {
                throw new IllegalArgumentException("Amount must not be negative");
            }
            return new Money(parsed);
        } catch (ArithmeticException | NumberFormatException ex) {
            throw new IllegalArgumentException("Amount must be a non-negative VND integer");
        }
    }

    public Money plus(Money o) {
        return new Money(value.add(o.value));
    }

    public Money minus(Money o) {
        BigDecimal result = value.subtract(o.value);
        if (result.signum() < 0) {
            throw new IllegalArgumentException("Money cannot become negative");
        }
        return new Money(result);
    }

    public boolean isLessThan(Money o) {
        return compareTo(o) < 0;
    }

    public boolean isZero() {
        return value.signum() == 0;
    }

    public static Money positive(String raw) {
        Money money = of(raw);
        if (money.isZero()) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        return money;
    }

    @Override
    public int compareTo(Money o) {
        return value.compareTo(o.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money)) {
            return false;
        }
        Money money = (Money) o;
        return value.compareTo(money.value) == 0;
    }

    @Override
    public int hashCode() {
        return value.stripTrailingZeros().hashCode();
    }

    @Override
    public String toString() {
        return value.toPlainString();
    }
}
