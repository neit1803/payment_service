package com.momo.domain.entity;

import com.momo.domain.enums.PaymentState;
import com.momo.domain.vo.BillSnapshot;
import java.time.LocalDate;
import java.util.Objects;

public final class PaymentTransaction {
    private final int id;
    private final int accountId;
    private final PaymentState state;
    private final BillSnapshot billSnapshot;
    private final LocalDate paymentDate;

    public PaymentTransaction(int id, int accountId, PaymentState state, BillSnapshot snapshot, LocalDate paymentDate) {
        this.id = id;
        this.accountId = accountId;
        this.state = Objects.requireNonNull(state);
        this.billSnapshot = Objects.requireNonNull(snapshot);
        this.paymentDate = Objects.requireNonNull(paymentDate);
    }

    public int id() {
        return id;
    }

    public int accountId() {
        return accountId;
    }

    public PaymentState state() {
        return state;
    }

    public BillSnapshot billSnapshot() {
        return billSnapshot;
    }

    public LocalDate paymentDate() {
        return paymentDate;
    }
}
