package com.momo.domain.vo;

import java.time.LocalDate;
import java.util.Objects;

public final class BillSnapshot {
    private final int billId;
    private final String type;
    private final Money amount;
    private final LocalDate dueDate;
    private final String provider;

    public BillSnapshot(int billId, String type, Money amount, LocalDate dueDate, String provider) {
        this.billId = billId;
        this.type = Objects.requireNonNull(type, "type");
        this.amount = Objects.requireNonNull(amount, "amount");
        this.dueDate = Objects.requireNonNull(dueDate, "dueDate");
        this.provider = requireText(provider, "provider");
    }

    public int billId() {
        return billId;
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

    public String provider() {
        return provider;
    }

    private static String requireText(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " is required");
        }
        return value.trim();
    }
}

