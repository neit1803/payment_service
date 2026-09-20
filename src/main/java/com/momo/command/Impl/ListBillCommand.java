package com.momo.command.Impl;

import com.momo.command.CommandHandler;
import com.momo.service.BillService;
import com.momo.utils.CommandResult;
import com.momo.utils.TableFormatter;
import java.util.ArrayList;
import java.util.List;

public final class ListBillCommand implements CommandHandler {
    private final BillService billService;

    public ListBillCommand(BillService billService) {
        this.billService = billService;
    }

    @Override
    public CommandResult execute(List<String> args) {
        if (!args.isEmpty()) {
            throw new IllegalArgumentException("Usage: LIST_BILL");
        }

        List<String> lines = new ArrayList<String>();
        TableFormatter.bills(billService.listBills(), lines);
        return CommandResult.continueWith(lines);
    }
}
