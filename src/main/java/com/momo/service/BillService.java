package com.momo.service;

import com.momo.domain.entity.Bill;
import com.momo.domain.enums.BillState;
import com.momo.domain.vo.Money;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BillService {
    private static final int DEFAULT_ACCOUNT_ID = 1;
    private final List<Bill> bills = new ArrayList<Bill>();
    private int nextId = 1;

    public Bill createBill(String type, Money amount, LocalDate dueDate, String provider) {
        Bill bill = new Bill(nextId, DEFAULT_ACCOUNT_ID, type, amount, dueDate, BillState.NOT_PAID, provider);
        nextId++;
        bills.add(bill);
        return bill;
    }

    public List<Bill> listBills() {
        return Collections.unmodifiableList(bills);
    }
}
