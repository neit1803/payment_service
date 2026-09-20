package com.momo.command.Impl;

import com.momo.command.CommandHandler;
import com.momo.domain.vo.Money;
import com.momo.service.AccountService;
import com.momo.utils.CommandResult;
import java.util.ArrayList;
import java.util.List;

public final class AddFundCommand implements CommandHandler {
    private final AccountService accountService;

    public AddFundCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public CommandResult execute(List<String> args) {
        if (args.size() != 1) {
            throw new IllegalArgumentException("Usage: CASH_IN amount");
        }

        Money balance = accountService.addFund(Money.positive(args.get(0)));
        List<String> lines = new ArrayList<String>();
        lines.add("Your available balance: " + balance);
        return CommandResult.continueWith(lines);
    }
}
