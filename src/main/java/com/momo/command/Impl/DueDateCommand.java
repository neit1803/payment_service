package com.momo.command.Impl;

import com.momo.command.CommandHandler;
import com.momo.service.BillService;
import com.momo.utils.CommandResult;
import com.momo.utils.TableFormatter;
import java.util.ArrayList;
import java.util.List;

public final class DueDateCommand implements CommandHandler {
    private final BillService billService;

    public DueDateCommand(BillService billService) {
        this.billService = billService;
    }

    @Override
    public CommandResult execute(List<String> args) {
        if (!args.isEmpty()) {
            throw new IllegalArgumentException("Usage: DUE_DATE");
        }

        List<String> lines = new ArrayList<String>();
        TableFormatter.bills(billService.dueDateBills(), lines);
        return CommandResult.continueWith(lines);
    }
}
