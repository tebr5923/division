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
    void printIntegerDivider_shouldPrintCorrectResult_whenOnlyOneStepDivider() {
        LineStringJoiner excepted = new LineStringJoiner()
                .add("_8|5")
                .add(" 5|-")
                .add(" -|1")
                .add(" 3");

        int dividend = 8;
        int divider = 5;
        IntegerStorage integerStorage = integerDivider.divide(dividend, divider);
        ConsoleStoragePrinter consolePrinter = new ConsoleStoragePrinter();
        consolePrinter.print(integerStorage);

        assertEquals(excepted.toString(), output.toString());
    }

    @Test
    void printIntegerDivider_shouldPrintCorrectResult_whenNotOneStepDivider() {
        LineStringJoiner excepted = new LineStringJoiner()
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

        int dividend = 999999;
        int divider = 255;
        IntegerStorage integerStorage = integerDivider.divide(dividend, divider);
        ConsoleStoragePrinter consolePrinter = new ConsoleStoragePrinter();
        consolePrinter.print(integerStorage);

        assertEquals(excepted.toString(), output.toString());
    }
}
