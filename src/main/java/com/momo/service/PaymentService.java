package com.momo.service;

import com.momo.domain.entity.Bill;
import com.momo.domain.entity.PaymentTransaction;
import com.momo.domain.enums.PaymentState;
import com.momo.domain.vo.BillSnapshot;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PaymentService {
    private final List<PaymentTransaction> transactions = new ArrayList<PaymentTransaction>();
    private int nextId = 1;

    public PaymentTransaction recordProcessed(Bill bill) {
        PaymentTransaction transaction = new PaymentTransaction(nextId, bill.accountId(), PaymentState.PROCESSED,
                new BillSnapshot(bill.id(), bill.type(), bill.amount(), bill.dueDate(), bill.provider()), LocalDate.now());
        nextId++;
        transactions.add(transaction);
        return transaction;
    }

    public List<PaymentTransaction> listTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
