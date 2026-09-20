package com.momo.command.Impl;

import com.momo.command.CommandHandler;
import com.momo.service.BillService;
import com.momo.utils.CommandResult;
import com.momo.utils.TableFormatter;
import java.util.ArrayList;
import java.util.List;

public final class SearchBillByProviderCommand implements CommandHandler {
    private final BillService billService;

    public SearchBillByProviderCommand(BillService billService) {
        this.billService = billService;
    }

    @Override
    public CommandResult execute(List<String> args) {
        if (args.isEmpty()) {
            throw new IllegalArgumentException("Usage: SEARCH_BILL_BY_PROVIDER provider");
        }

        List<String> lines = new ArrayList<String>();
        TableFormatter.bills(billService.searchByProvider(join(args)), lines);
        return CommandResult.continueWith(lines);
    }

    private static String join(List<String> args) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < args.size(); i++) {
            if (i > 0) {
                builder.append(' ');
            }
            builder.append(args.get(i));
        }
        return builder.toString();
    }
}
