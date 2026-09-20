package com.momo.domain.entity;

import com.momo.domain.enums.BillState;
import java.math.BigDecimal;
import java.time.LocalDate;

public final class Bill {
    private final int id;
    private final String type;
    private final BigDecimal amount;
    private final LocalDate dueDate;
    private final BillState state;
    private final String provider;

    public Bill(int id, String type, BigDecimal amount, LocalDate dueDate, String provider) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.dueDate = dueDate;
        this.provider = provider.trim().toUpperCase();
        this.state = BillState.NOT_PAID;
    }

    public int id() {
        return id;
    }

    public String type() {
        return type;
    }

    public BigDecimal amount() {
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
