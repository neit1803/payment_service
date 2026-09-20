package com.momo.domain.vo;

import java.math.BigDecimal;

import com.momo.domain.enums.PaymentState;

public record BillSnapshot(
    int id, 
    int billId,
    BigDecimal amount,
    PaymentState state) {

    public int id() {
        return id;
    }

    public int billId() {
        return billId;
    }

    public BigDecimal amount() {
        return amount;
    }

    public PaymentState state() {
        return state;
    }
        
}
