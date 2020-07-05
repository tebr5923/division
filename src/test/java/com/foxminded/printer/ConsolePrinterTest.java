package com.foxminded.printer;

import com.foxminded.storage.IntegerStorage;
import com.foxminded.storage.NumberWithPosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsolePrinterTest {
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
        StringJoiner excepted = new StringJoiner(System.lineSeparator()).
                add("_999999|255").
                add(" 765   |----").
                add(" ---   |3921").
                add("_2349").
                add(" 2295").
                add(" ----").
                add("  _549").
                add("   510").
                add("   ---").
                add("   _399").
                add("    255").
                add("    ---").
                add("    144").
                add("");

        int dividend = 999999;
        int divider = 255;
        List<NumberWithPosition> representations = new ArrayList<>();
        representations.add(new NumberWithPosition(dividend, 0));
        representations.add(new NumberWithPosition(765, 0));
        representations.add(new NumberWithPosition(2349, 0));
        representations.add(new NumberWithPosition(2295, 0));
        representations.add(new NumberWithPosition(549, 2));
        representations.add(new NumberWithPosition(510, 2));
        representations.add(new NumberWithPosition(399, 3));
        representations.add(new NumberWithPosition(255, 3));
        representations.add(new NumberWithPosition(144, 3));
        IntegerStorage integerStorage = new IntegerStorage(representations, dividend, divider);
        ConsolePrinter consolePrinter = new ConsolePrinter();
        consolePrinter.print(integerStorage);

        assertEquals(excepted.toString(), output.toString());
    }
}
