package com.momo.service;

import com.momo.domain.entity.Bill;
import com.momo.domain.enums.BillState;
import com.momo.domain.vo.Money;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public List<Bill> unpaidBillsByIds(List<Integer> ids) {
        List<Bill> selected = new ArrayList<Bill>();
        for (Integer id : ids) {
            Bill bill = billById(id.intValue());
            if (bill.state() != BillState.NOT_PAID) {
                throw new IllegalArgumentException("Bill with id " + bill.id() + " has already been paid");
            }
            selected.add(bill);
        }
        Collections.sort(selected, new Comparator<Bill>() {
            @Override
            public int compare(Bill left, Bill right) {
                int byDate = left.dueDate().compareTo(right.dueDate());
                if (byDate != 0) {
                    return byDate;
                }
                return Integer.compare(left.id(), right.id());
            }
        });
        return selected;
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

    public List<Bill> searchByProvider(String provider) {
        String keyword = requireText(provider, "provider").toLowerCase();
        List<Bill> matched = new ArrayList<Bill>();
        for (Bill bill : bills) {
            if (bill.provider().toLowerCase().contains(keyword)) {
                matched.add(bill);
            }
        }
        return matched;
    }

    public List<Bill> dueDateBills() {
        List<Bill> dueBills = new ArrayList<Bill>();
        for (Bill bill : bills) {
            if (bill.state() == BillState.NOT_PAID) {
                dueBills.add(bill);
            }
        }
        Collections.sort(dueBills, new Comparator<Bill>() {
            @Override
            public int compare(Bill left, Bill right) {
                int byDate = left.dueDate().compareTo(right.dueDate());
                if (byDate != 0) {
                    return byDate;
                }
                return Integer.compare(left.id(), right.id());
            }
        });
        return dueBills;
    }

    private int indexOf(int id) {
        for (int i = 0; i < bills.size(); i++) {
            if (bills.get(i).id() == id) {
                return i;
            }
        }
        throw new IllegalArgumentException("Bill not found");
    }

    private Bill billById(int id) {
        return bills.get(indexOf(id));
    }

    private static String requireText(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " is required");
        }
        return value.trim();
    }
}
