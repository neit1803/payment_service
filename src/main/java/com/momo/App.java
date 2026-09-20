package com.momo;

import com.momo.command.CliAdapter;
import com.momo.utils.CommandLine;
import com.momo.utils.CommandResult;
import com.momo.utils.Shell;

public class App {
    public static void main(String[] args) throws Exception {
        CliAdapter adapter = new CliAdapter();
        Shell shell = new Shell(adapter, System.out);
        if (args.length == 0) {
            shell.run();
            return;
        }

        CommandResult result = adapter.execute(CommandLine.fromArgs(args));
        shell.print(result.lines());
    }
}
