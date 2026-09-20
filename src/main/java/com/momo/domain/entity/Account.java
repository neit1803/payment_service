package com.momo.domain.entity;


import java.math.BigDecimal;
import java.util.Objects;

public final class Account {
    private final int id;
    private final BigDecimal balance;
    
    public Account(int id, BigDecimal balance) {
        this.id = id;
        this.balance = Objects.requireNonNull(balance);
    }

    public int getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Account withDraw(BigDecimal fund) {
        return new Account(id, fund);
    }

    public Account debit(BigDecimal fund) {
        return new Account(id, fund);
    }
}
