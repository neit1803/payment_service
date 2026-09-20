package com.momo.utils;

import com.momo.command.CliAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

public final class Shell {
    private final CliAdapter adapter;
    private final PrintStream out;

    public Shell(CliAdapter adapter, PrintStream out) {
        this.adapter = adapter;
        this.out = out;
    }

    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            out.print("> ");
            out.flush();
            String line = reader.readLine();
            if (line == null) {
                out.println("Good bye!");
                return;
            }

            CommandResult result = adapter.execute(CommandLine.parse(line));
            print(result.lines());
            if (result.shouldExit()) {
                return;
            }
        }
    }

    public void print(List<String> lines) {
        for (String line : lines) {
            out.println(line);
        }
    }
}
