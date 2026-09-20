package com.momo.command;

import com.momo.service.AccountService;
import com.momo.utils.CommandLine;
import com.momo.utils.CommandResult;
import java.util.List;

public final class AddFundCommandTest {
    public static void main(String[] args) {
        addsFundAndKeepsBalance();
        supportsAddFundAlias();
        rejectsInvalidAmount();
        System.out.println("PASS AddFundCommandTest");
    }

    private static void addsFundAndKeepsBalance() {
        CliAdapter adapter = adapter();

        CommandResult first = adapter.execute(CommandLine.parse("CASH_IN 100000"));
        CommandResult second = adapter.execute(CommandLine.parse("CASH_IN 50000"));

        assertEquals(false, first.shouldExit());
        assertLines(first.lines(), "Your available balance: 100000");
        assertLines(second.lines(), "Your available balance: 150000");
    }

    private static void supportsAddFundAlias() {
        CliAdapter adapter = adapter();

        CommandResult result = adapter.execute(CommandLine.parse("ADD_FUND 200000"));

        assertLines(result.lines(), "Your available balance: 200000");
    }

    private static void rejectsInvalidAmount() {
        CliAdapter adapter = adapter();

        CommandResult result = adapter.execute(CommandLine.parse("CASH_IN 0"));

        assertLines(result.lines(), "Amount must be greater than zero");
    }

    private static CliAdapter adapter() {
        return new CliAdapter(CommandRegistry.withAccountService(new AccountService()));
    }

    private static void assertLines(List<String> actual, String expected) {
        assertEquals(1, actual.size());
        assertEquals(expected, actual.get(0));
    }

    private static void assertEquals(Object expected, Object actual) {
        if (expected == null ? actual != null : !expected.equals(actual)) {
            throw new AssertionError("Expected <" + expected + "> but was <" + actual + ">");
        }
    }
}
