package com.momo.command;

import com.momo.command.Impl.ExitCommand;
import java.util.HashMap;
import java.util.Map;

public final class CommandRegistry {
    private CommandRegistry() {
    }

    public static Map<String, CommandHandler> minimum() {
        Map<String, CommandHandler> commands = new HashMap<String, CommandHandler>();
        commands.put("EXIT", new ExitCommand());
        return commands;
    }
}
