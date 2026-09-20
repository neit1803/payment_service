package com.momo.domain.entity;

import com.momo.domain.enums.ScheduledState;
import java.time.LocalDate;
import java.util.Objects;

public final class SchedulePayment {
    private final int id;
    private final int billId;
    private final LocalDate scheduledDate;
    private final ScheduledState state;

    public SchedulePayment(int id, int billId, LocalDate scheduledDate, ScheduledState state) {
        this.id = id;
        this.billId = billId;
        this.scheduledDate = Objects.requireNonNull(scheduledDate);
        this.state = Objects.requireNonNull(state);
    }

    public int id() {
        return id;
    }

    public int billId() {
        return billId;
    }

    public LocalDate scheduledDate() {
        return scheduledDate;
    }

    public ScheduledState state() {
        return state;
    }


}
