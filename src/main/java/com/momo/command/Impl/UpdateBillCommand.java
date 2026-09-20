package com.momo.command.Impl;

import com.momo.command.CommandHandler;
import com.momo.domain.entity.Bill;
import com.momo.domain.vo.Money;
import com.momo.service.BillService;
import com.momo.utils.CommandResult;
import com.momo.utils.Dates;
import java.util.ArrayList;
import java.util.List;

public final class UpdateBillCommand implements CommandHandler {
    private final BillService billService;

    public UpdateBillCommand(BillService billService) {
        this.billService = billService;
    }

    @Override
    public CommandResult execute(List<String> args) {
        if (args.size() < 5) {
            throw new IllegalArgumentException("Usage: UPDATE_BILL id type amount due_date provider");
        }

        Bill bill = billService.updateBill(parseId(args.get(0)), args.get(1), Money.positive(args.get(2)),
                Dates.parseUserDate(args.get(3)), joinFrom(args, 4));
        List<String> lines = new ArrayList<String>();
        lines.add("Bill with id " + bill.id() + " has been updated.");
        return CommandResult.continueWith(lines);
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

    private static String joinFrom(List<String> args, int start) {
        StringBuilder builder = new StringBuilder();
        for (int i = start; i < args.size(); i++) {
            if (i > start) {
                builder.append(' ');
            }
            builder.append(args.get(i));
        }
        return builder.toString();
    }
}
