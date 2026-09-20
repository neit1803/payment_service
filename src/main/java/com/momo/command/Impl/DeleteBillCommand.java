package com.momo.command.Impl;

import com.momo.command.CommandHandler;
import com.momo.service.BillService;
import com.momo.utils.CommandResult;
import java.util.ArrayList;
import java.util.List;

public final class DeleteBillCommand implements CommandHandler {
    private final BillService billService;

    public DeleteBillCommand(BillService billService) {
        this.billService = billService;
    }

    @Override
    public CommandResult execute(List<String> args) {
        if (args.size() != 1) {
            throw new IllegalArgumentException("Usage: DELETE_BILL id");
        }

        int id = parseId(args.get(0));
        billService.deleteBill(id);
        List<String> lines = new ArrayList<String>();
        lines.add("Bill with id " + id + " has been deleted.");
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
}
