package com.momo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public final class Shell {
    private final String[] args;
    private final PrintStream out;

    public Shell(String[] args, PrintStream out) {
        this.args = args;
        this.out = out;
    }

    public void run() throws IOException {
        out.println("Raw args: " + Arrays.toString(args));
        if (args.length > 0) {
            CommandLine commandLine = CommandLine.fromArgs(args);
            out.println("CLI command name: " + commandLine.name());
            out.println("CLI command args: " + commandLine.args());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            out.print("> ");
            out.flush();
            String line = reader.readLine();
            if (line == null) {
                out.println("Good bye!");
                return;
            }

            CommandLine commandLine = CommandLine.parse(line);
            out.println("Input command name: " + commandLine.name());
            out.println("Input command args: " + commandLine.args());
        }
    }

    public void print(List<String> lines) {
        for (String line : lines) {
            out.println(line);
        }
    }
}
