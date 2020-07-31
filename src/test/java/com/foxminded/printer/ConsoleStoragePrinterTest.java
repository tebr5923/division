package com.foxminded.printer;

import com.foxminded.storage.IntegerStorage;
import com.foxminded.util.IntegerStorageBuilder;
import com.foxminded.util.LineStringJoiner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleStoragePrinterTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        System.setOut(null);
    }

    @Test
    void print_shouldPrintToSystemOut_whenNotOneStep() {
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

        int dividend = 999999;
        int divider = 255;
        IntegerStorage integerStorage = new IntegerStorageBuilder(dividend, divider)
                .addStep(dividend, 0)
                .addStep(765, 0)
                .addStep(2349, 0)
                .addStep(2295, 0)
                .addStep(549, 2)
                .addStep(510, 2)
                .addStep(399, 3)
                .addStep(255, 3)
                .addStep(144, 3)
                .build();
        ConsoleStoragePrinter consolePrinter = new ConsoleStoragePrinter();
        consolePrinter.print(integerStorage);

        assertEquals(expected.toString(), output.toString());
    }

    @Test
    void print_shouldPrintOnlyHeaderAndReminder_whenOnlyOneStep() {
        LineStringJoiner expected = new LineStringJoiner()
                .add("_8|5")
                .add(" 5|-")
                .add(" -|1")
                .add(" 3");

        int dividend = 8;
        int divider = 5;
        IntegerStorage integerStorage = new IntegerStorageBuilder(dividend, divider)
                .addStep(dividend, 0)
                .addStep(5, 0)
                .addStep(3, 0)
                .build();
        ConsoleStoragePrinter consolePrinter = new ConsoleStoragePrinter();
        consolePrinter.print(integerStorage);

        assertEquals(expected.toString(), output.toString());
    }
}
