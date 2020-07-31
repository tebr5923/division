package com.foxminded.integration;

import com.foxminded.divider.IntegerDividerTemplate;
import com.foxminded.printer.ConsoleStoragePrinter;
import com.foxminded.storage.IntegerStorage;
import com.foxminded.util.LineStringJoiner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class AbstractIntegerDividerPrinterIntegrationTest {
    protected IntegerDividerTemplate integerDivider;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    protected AbstractIntegerDividerPrinterIntegrationTest(IntegerDividerTemplate integerDivider) {
        this.integerDivider = integerDivider;
    }

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        System.setOut(null);
    }

    @Test
    void printIntegerDivider_shouldPrintCorrectResult_whenOneDigitDividendAndDivider() {
        LineStringJoiner expected = new LineStringJoiner()
                .add("_8|5")
                .add(" 5|-")
                .add(" -|1")
                .add(" 3");

        performDivisionPrint(8, 5);

        assertEquals(expected.toString(), getOutputAsString());
    }

    @Test
    void printIntegerDivider_shouldPrintCorrectResult_whenDividendMuchLargerDivider() {
        LineStringJoiner expected = new LineStringJoiner()
                .add("_999999|255")
                .add(" 765   |----")
                .add(" ---   |3921")
                .add("_2349")
                .add(" 2295")
                .add(" ----")
                .add("  _549")
                .add("   510")
                .add("   ---")
                .add("   _399")
                .add("    255")
                .add("    ---")
                .add("    144");

        performDivisionPrint(999999, 255);

        assertEquals(expected.toString(), getOutputAsString());
    }

    @Test
    void printIntegerDivider_shouldPrintCorrectResult_whenResultWithZeroReminder() {
        LineStringJoiner expected = new LineStringJoiner()
                .add("_840|24")
                .add(" 72 |--")
                .add(" -- |35")
                .add("_120")
                .add(" 120")
                .add(" ---")
                .add("   0");

        performDivisionPrint(840, 24);

        assertEquals(expected.toString(), getOutputAsString());
    }

    @Test
    abstract void printIntegerDivider_shouldPrintCorrectResult_whenResultWithZeroStage();

    protected void performDivisionPrint(int dividend, int divider) {
        IntegerStorage integerStorage = integerDivider.divide(dividend, divider);
        ConsoleStoragePrinter consolePrinter = new ConsoleStoragePrinter();
        consolePrinter.print(integerStorage);
    }

    protected String getOutputAsString() {
        return output.toString();
    }
}
