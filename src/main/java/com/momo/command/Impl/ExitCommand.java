package com.momo.command.Impl;

import com.momo.command.CommandHandler;
import com.momo.utils.CommandResult;

import java.util.List;

public final class ExitCommand implements CommandHandler {
    @Override
    public CommandResult execute(List<String> args) {
        return CommandResult.exit("Good bye!");
    }
}
