package com.momo.command;

import com.momo.command.Impl.AddFundCommand;
import com.momo.command.Impl.ExitCommand;
import com.momo.service.AccountService;
import java.util.HashMap;
import java.util.Map;

public final class CommandRegistry {
    private CommandRegistry() {
    }

    public static Map<String, CommandHandler> minimum() {
        return withAccountService(new AccountService());
    }

    public static Map<String, CommandHandler> withAccountService(AccountService accountService) {
        Map<String, CommandHandler> commands = new HashMap<String, CommandHandler>();
        CommandHandler addFundCommand = new AddFundCommand(accountService);
        commands.put("CASH_IN", addFundCommand);
        commands.put("ADD_FUND", addFundCommand);
        commands.put("EXIT", new ExitCommand());
        return commands;
    }
}
