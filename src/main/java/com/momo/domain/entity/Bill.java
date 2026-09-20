package com.momo.domain.entity;

import com.momo.domain.enums.BillState;
import com.momo.domain.vo.Money;
import java.time.LocalDate;
import java.util.Objects;

public final class Bill {
    private final int id;
    private final int accountId;
    private final String type;
    private final Money amount;
    private final LocalDate dueDate;
    private final BillState state;
    private final String provider;

    public Bill(int id, int accountId, String type, Money amount, LocalDate dueDate, BillState state, String provider) {
        if (id <= 0) {
            throw new IllegalArgumentException("Bill id must be positive");
        }
        if (accountId <= 0) {
            throw new IllegalArgumentException("Account id must be positive");
        }
        this.id = id;
        this.accountId = accountId;
        this.type = type;
        this.amount = Objects.requireNonNull(amount, "amount");
        this.dueDate = Objects.requireNonNull(dueDate, "dueDate");
        this.state = Objects.requireNonNull(state, "state");
        this.provider = requireText(provider, "provider");
    }

    static String requireText(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " is required");
        }
        return value.trim();
    }


    public int id() {
        return id;
    }

    public int accountId() {
        return accountId;
    }

    public String type() {
        return type;
    }

    public Money amount() {
        return amount;
    }

    public LocalDate dueDate() {
        return dueDate;
    }

    public BillState state() {
        return state;
    }

    public String provider() {
        return provider;
    }
}
