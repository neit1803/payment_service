package com.momo.utils;

import java.util.ArrayList;
import java.util.List;

public final class CommandLine {
    private final String name;
    private final List<String> args;

    private CommandLine(String name, List<String> args) {
        this.name = name;
        this.args = args;
    }

    public static CommandLine parse(String line) {
        List<String> tokens = tokenize(line);
        if (tokens.isEmpty()) {
            return new CommandLine("", tokens);
        }
        String name = tokens.remove(0).toUpperCase();
        return new CommandLine(name, tokens);
    }

    public static CommandLine fromArgs(String[] args) {
        List<String> tokens = new ArrayList<String>();
        for (String arg : args) {
            tokens.add(arg);
        }
        if (tokens.isEmpty()) {
            return new CommandLine("", tokens);
        }
        String name = tokens.remove(0).toUpperCase();
        return new CommandLine(name, tokens);
    }

    public String name() {
        return name;
    }

    public List<String> args() {
        return args;
    }

    private static List<String> tokenize(String line) {
        List<String> tokens = new ArrayList<String>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (ch == '"') {
                inQuotes = !inQuotes;
            } else if (Character.isWhitespace(ch) && !inQuotes) {
                if (current.length() > 0) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
            } else {
                current.append(ch);
            }
        }
        if (inQuotes) {
            throw new IllegalArgumentException("Missing closing quote");
        }
        if (current.length() > 0) {
            tokens.add(current.toString());
        }
        return tokens;
    }
}
