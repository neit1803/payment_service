package com.momo.command.Impl;

import com.momo.command.CommandHandler;
import com.momo.service.PaymentService;
import com.momo.utils.CommandResult;
import com.momo.utils.TableFormatter;
import java.util.ArrayList;
import java.util.List;

public final class ListPaymentCommand implements CommandHandler {
    private final PaymentService paymentService;

    public ListPaymentCommand(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public CommandResult execute(List<String> args) {
        if (!args.isEmpty()) {
            throw new IllegalArgumentException("Usage: LIST_PAYMENT");
        }

        List<String> lines = new ArrayList<String>();
        TableFormatter.payments(paymentService.listTransactions(), lines);
        return CommandResult.continueWith(lines);
    }
}
