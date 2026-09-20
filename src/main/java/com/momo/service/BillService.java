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

    public Bill updateBill(int id, String type, Money amount, LocalDate dueDate, String provider) {
        int index = indexOf(id);
        Bill current = bills.get(index);
        if (current.state() != BillState.NOT_PAID) {
            throw new IllegalArgumentException("Only unpaid bills can be updated");
        }

        Bill updated = new Bill(current.id(), current.accountId(), type, amount, dueDate, current.state(), provider);
        bills.set(index, updated);
        return updated;
    }

    public Bill markPaid(int id) {
        int index = indexOf(id);
        Bill current = bills.get(index);
        Bill paid = new Bill(current.id(), current.accountId(), current.type(), current.amount(), current.dueDate(),
                BillState.PAID, current.provider());
        bills.set(index, paid);
        return paid;
    }

    public void deleteBill(int id) {
        int index = indexOf(id);
        Bill current = bills.get(index);
        if (current.state() != BillState.NOT_PAID) {
            throw new IllegalArgumentException("Only unpaid bills can be deleted");
        }
        bills.remove(index);
    }

    public List<Bill> listBills() {
        return Collections.unmodifiableList(bills);
    }

    private int indexOf(int id) {
        for (int i = 0; i < bills.size(); i++) {
            if (bills.get(i).id() == id) {
                return i;
            }
        }
        throw new IllegalArgumentException("Bill not found");
    }
}
