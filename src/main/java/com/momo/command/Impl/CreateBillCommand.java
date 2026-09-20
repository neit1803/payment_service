package com.momo.command.Impl;

import com.momo.command.CommandHandler;
import com.momo.domain.entity.Bill;
import com.momo.domain.vo.Money;
import com.momo.service.BillService;
import com.momo.utils.CommandResult;
import com.momo.utils.Dates;
import java.util.ArrayList;
import java.util.List;

public final class CreateBillCommand implements CommandHandler {
    private final BillService billService;

    public CreateBillCommand(BillService billService) {
        this.billService = billService;
    }

    @Override
    public CommandResult execute(List<String> args) {
        if (args.size() < 4) {
            throw new IllegalArgumentException("Usage: CREATE_BILL type amount due_date provider");
        }

        Bill bill = billService.createBill(args.get(0), Money.positive(args.get(1)),
                Dates.parseUserDate(args.get(2)), joinFrom(args, 3));
        List<String> lines = new ArrayList<String>();
        lines.add("Bill with id " + bill.id() + " has been created.");
        return CommandResult.continueWith(lines);
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
