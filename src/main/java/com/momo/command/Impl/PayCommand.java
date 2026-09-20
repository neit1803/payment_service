package com.momo.command.Impl;

import com.momo.command.CommandHandler;
import com.momo.domain.entity.Bill;
import com.momo.domain.vo.Money;
import com.momo.service.AccountService;
import com.momo.service.BillService;
import com.momo.utils.CommandResult;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class PayCommand implements CommandHandler {
    private final AccountService accountService;
    private final BillService billService;

    public PayCommand(AccountService accountService, BillService billService) {
        this.accountService = accountService;
        this.billService = billService;
    }

    @Override
    public CommandResult execute(List<String> args) {
        if (args.isEmpty()) {
            throw new IllegalArgumentException("Usage: PAY bill_id...");
        }

        List<Integer> ids = parseIds(args);
        List<Bill> bills = billService.unpaidBillsByIds(ids);
        Money total = Money.ZERO;
        for (Bill bill : bills) {
            total = total.plus(bill.amount());
        }

        if (accountService.balance().isLessThan(total)) {
            List<String> lines = new ArrayList<String>();
            lines.add("Sorry! Not enough fund to proceed with payment.");
            return CommandResult.continueWith(lines);
        }

        accountService.deduct(total);
        List<String> lines = new ArrayList<String>();
        for (Bill bill : bills) {
            billService.markPaid(bill.id());
            lines.add("Payment has been completed for Bill with id " + bill.id() + ".");
        }
        lines.add("Your current balance is: " + accountService.balance());
        return CommandResult.continueWith(lines);
    }

    private static List<Integer> parseIds(List<String> args) {
        List<Integer> ids = new ArrayList<Integer>();
        Set<Integer> seen = new HashSet<Integer>();
        for (String arg : args) {
            int id = parseId(arg);
            if (seen.contains(Integer.valueOf(id))) {
                throw new IllegalArgumentException("Bill id must not be duplicated");
            }
            seen.add(Integer.valueOf(id));
            ids.add(Integer.valueOf(id));
        }
        return ids;
    }

    private static int parseId(String raw) {
        try {
            int id = Integer.parseInt(raw);
            if (id <= 0) {
                throw new NumberFormatException();
            }
            return id;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Id must be a positive integer");
        }
    }
}
