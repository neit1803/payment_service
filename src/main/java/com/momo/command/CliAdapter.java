package com.momo.command;

import com.momo.utils.CommandLine;
import com.momo.utils.CommandResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class CliAdapter {
    private final Map<String, CommandHandler> commands;

    public CliAdapter() {
        this(CommandRegistry.minimum());
    }

    public CliAdapter(Map<String, CommandHandler> commands) {
        Map<String, CommandHandler> normalized = new HashMap<String, CommandHandler>();
        for (Map.Entry<String, CommandHandler> entry : commands.entrySet()) {
            normalized.put(entry.getKey().toUpperCase(Locale.ROOT), entry.getValue());
        }
        this.commands = Collections.unmodifiableMap(normalized);
    }

    public CommandResult execute(CommandLine commandLine) {
        if (commandLine.name().isEmpty()) {
            return CommandResult.continueWith(new ArrayList<String>());
        }

        CommandHandler handler = commands.get(commandLine.name());
        if (handler == null) {
            return CommandResult.continueWith(single("Unknown command."));
        }

        try {
            return handler.execute(commandLine.args());
        } catch (Exception ex) {
            String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
            return CommandResult.continueWith(single(message));
        }
    }

    private static List<String> single(String value) {
        List<String> lines = new ArrayList<String>();
        lines.add(value);
        return lines;
    }
}
