package com.momo.command;

import com.momo.domain.entity.Bill;
import com.momo.domain.enums.BillState;
import com.momo.service.AccountService;
import com.momo.service.BillService;
import com.momo.utils.CommandLine;
import com.momo.utils.CommandResult;
import java.time.LocalDate;
import java.util.List;

public final class CreateBillCommandTest {
    public static void main(String[] args) {
        createsBill();
        joinsProviderWords();
        rejectsMissingArgs();
        System.out.println("PASS CreateBillCommandTest");
    }

    private static void createsBill() {
        BillService billService = new BillService();
        CliAdapter adapter = adapter(billService);

        CommandResult result = adapter.execute(CommandLine.parse("CREATE_BILL WATER 175000 25/10/2026 SAVACO"));

        assertLines(result.lines(), "Bill with id 1 has been created.");
        assertEquals(1, billService.listBills().size());
        Bill bill = billService.listBills().get(0);
        assertEquals(1, bill.id());
        assertEquals("WATER", bill.type());
        assertEquals("175000", bill.amount().toString());
        assertEquals(LocalDate.of(2026, 10, 25), bill.dueDate());
        assertEquals(BillState.NOT_PAID, bill.state());
        assertEquals("SAVACO", bill.provider());
    }

    private static void joinsProviderWords() {
        BillService billService = new BillService();
        CliAdapter adapter = adapter(billService);

        adapter.execute(CommandLine.parse("CREATE_BILL ELECTRIC 200000 30/10/2026 EVN HCMC"));

        assertEquals("EVN HCMC", billService.listBills().get(0).provider());
    }

    private static void rejectsMissingArgs() {
        CommandResult result = adapter(new BillService()).execute(CommandLine.parse("CREATE_BILL WATER 100000"));

        assertLines(result.lines(), "Usage: CREATE_BILL type amount due_date provider");
    }

    private static CliAdapter adapter(BillService billService) {
        return new CliAdapter(CommandRegistry.withServices(new AccountService(), billService));
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
