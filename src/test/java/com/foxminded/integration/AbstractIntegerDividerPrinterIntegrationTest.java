package com.foxminded.integration;

import com.foxminded.divider.IntegerDividerTemplate;
import com.foxminded.printer.ConsoleStoragePrinter;
import com.foxminded.storage.IntegerStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class AbstractIntegerDividerPrinterIntegrationTest {
    protected IntegerDividerTemplate integerDivider;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    public AbstractIntegerDividerPrinterIntegrationTest(IntegerDividerTemplate integerDivider) {
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
    void printIntegerDivider_shouldPrintCorrectResult_whenOnlyOneStep() {
        StringJoiner excepted = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
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
}
