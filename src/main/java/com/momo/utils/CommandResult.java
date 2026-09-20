package com.momo.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CommandResult {
    private final boolean exit;
    private final List<String> lines;

    private CommandResult(boolean exit, List<String> lines) {
        this.exit = exit;
        this.lines = Collections.unmodifiableList(new ArrayList<String>(lines));
    }

    public static CommandResult continueWith(List<String> lines) {
        return new CommandResult(false, lines);
    }

    public static CommandResult exit(String line) {
        List<String> lines = new ArrayList<String>();
        lines.add(line);
        return new CommandResult(true, lines);
    }

    public boolean shouldExit() {
        return exit;
    }

    public List<String> lines() {
        return lines;
    }
}
