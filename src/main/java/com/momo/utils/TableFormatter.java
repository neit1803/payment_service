package com.momo.utils;

import com.momo.domain.entity.Bill;
import com.momo.domain.entity.PaymentTransaction;
import com.momo.domain.entity.SchedulePayment;
import java.util.List;

public final class TableFormatter {
    private TableFormatter() {
    }

    public static void bills(List<Bill> bills, List<String> out) {
        out.add("Bill No. Type Amount Due Date State PROVIDER");
        for (Bill bill : bills) {
            out.add(bill.id() + ". " + bill.type() + " " + bill.amount() + " " + Dates.formatUserDate(bill.dueDate())
                    + " " + bill.state() + " " + bill.provider());
        }
    }

    public static void payments(List<PaymentTransaction> transactions, List<String> out) {
        out.add("No. Amount Payment Date State Bill Id");
        for (PaymentTransaction transaction : transactions) {
            out.add(transaction.id() + ". " + transaction.billSnapshot().amount() + " " + Dates.formatUserDate(transaction.paymentDate())
                    + " " + transaction.state() + " " + transaction.billSnapshot().billId());
        }
    }

    public static void schedules(List<SchedulePayment> schedules, List<String> out) {
        out.add("No. Bill Id Scheduled Date State");
        for (SchedulePayment schedule : schedules) {
            out.add(schedule.id() + ". " + schedule.billId() + " " + Dates.formatUserDate(schedule.scheduledDate())
                    + " " + schedule.state());
        }
    }

}
