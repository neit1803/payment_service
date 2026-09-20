package com.momo.command;

import com.momo.service.AccountService;
import com.momo.service.BillService;
import com.momo.utils.CommandLine;
import com.momo.utils.CommandResult;
import java.util.List;

public final class CommandTest {
    public static void main(String[] args) {
        supportsMainCliFlow();
        exitsApplication();
        System.out.println("PASS CommandTest");
    }

    private static void supportsMainCliFlow() {
        CliAdapter adapter = adapter();

        assertLines(adapter.execute(CommandLine.parse("CASH_IN 1000000")).lines(),
                "Your available balance: 1000000");
        assertLines(adapter.execute(CommandLine.parse("CREATE_BILL ELECTRIC 200000 25/10/2020 EVN HCMC")).lines(),
                "Bill with id 1 has been created.");
        assertLines(adapter.execute(CommandLine.parse("CREATE_BILL WATER 175000 30/10/2020 SAVACO HCMC")).lines(),
                "Bill with id 2 has been created.");

        List<String> bills = adapter.execute(CommandLine.parse("LIST_BILL")).lines();
        assertEquals(3, bills.size());
        assertEquals("Bill No. Type Amount Due Date State PROVIDER", bills.get(0));
        assertEquals("1. ELECTRIC 200000 25/10/2020 NOT_PAID EVN HCMC", bills.get(1));
        assertEquals("2. WATER 175000 30/10/2020 NOT_PAID SAVACO HCMC", bills.get(2));
    }

    private static CliAdapter adapter() {
        return new CliAdapter(CommandRegistry.withServices(new AccountService(), new BillService()));
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
