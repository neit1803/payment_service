package com.momo.command;

import com.momo.command.Impl.AddFundCommand;
import com.momo.command.Impl.CreateBillCommand;
import com.momo.command.Impl.DeleteBillCommand;
import com.momo.command.Impl.ExitCommand;
import com.momo.command.Impl.ListBillCommand;
import com.momo.command.Impl.SearchBillByProviderCommand;
import com.momo.command.Impl.UpdateBillCommand;
import com.momo.service.AccountService;
import com.momo.service.BillService;
import java.util.HashMap;
import java.util.Map;

public final class CommandRegistry {
    private CommandRegistry() {
    }

    public static Map<String, CommandHandler> minimum() {
        return withServices(new AccountService(), new BillService());
    }

    public static Map<String, CommandHandler> withAccountService(AccountService accountService) {
        return withServices(accountService, new BillService());
    }

    public static Map<String, CommandHandler> withServices(AccountService accountService, BillService billService) {
        Map<String, CommandHandler> commands = new HashMap<String, CommandHandler>();
        CommandHandler addFundCommand = new AddFundCommand(accountService);
        commands.put("CASH_IN", addFundCommand);
        commands.put("ADD_FUND", addFundCommand);
        commands.put("CREATE_BILL", new CreateBillCommand(billService));
        commands.put("UPDATE_BILL", new UpdateBillCommand(billService));
        commands.put("DELETE_BILL", new DeleteBillCommand(billService));
        commands.put("LIST_BILL", new ListBillCommand(billService));
        commands.put("SEARCH_BILL_BY_PROVIDER", new SearchBillByProviderCommand(billService));
        commands.put("EXIT", new ExitCommand());
        return commands;
    }
}
