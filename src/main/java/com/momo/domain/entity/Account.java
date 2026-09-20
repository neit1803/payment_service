package com.momo.domain.entity;

import com.momo.domain.vo.Money;
import java.util.Objects;

public final class Account {
    private final int id;
    private final Money balance;

    public Account(int id, Money balance) {
        if (id <= 0) {
            throw new IllegalArgumentException("Account id must be positive");
        }
        this.id = id;
        this.balance = Objects.requireNonNull(balance, "balance");
    }

    public int id() {
        return id;
    }

    public Money balance() {
        return balance;
    }

    public Account credit(Money amount) {
        return new Account(id, balance.plus(amount));
    }

    public Account debit(Money amount) {
        return new Account(id, balance.minus(amount));
    }
}
