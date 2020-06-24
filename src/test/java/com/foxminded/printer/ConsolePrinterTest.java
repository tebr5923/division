package com.foxminded.printer;

import com.foxminded.storage.IntegerStorage;
import com.foxminded.storage.Representation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsolePrinterTest {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        System.setOut(null);
    }

    @Test
    void print_shouldPrintToSystemOut_whenArgumentsIsRandom() {
        String excepted= String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n",
                "_999999|255",
                " 765   |----",
                " ---   |3921",
                "_2349",
                " 2295",
                " ----",
                "  _549",
                "   510",
                "   ---",
                "   _399",
                "    255",
                "    ---",
                "    144");

        int dividend = 999999;
        int divider = 255;
        List<Representation> representations = new ArrayList<>();
        representations.add(new Representation(dividend, 0));
        representations.add(new Representation(765, 0));
        representations.add(new Representation(2349, 0));
        representations.add(new Representation(2295, 0));
        representations.add(new Representation(549, 2));
        representations.add(new Representation(510, 2));
        representations.add(new Representation(399, 3));
        representations.add(new Representation(255, 3));
        representations.add(new Representation(144, 3));
        IntegerStorage integerStorage = new IntegerStorage(representations, dividend, divider);
        ConsolePrinter consolePrinter = new ConsolePrinter();
        consolePrinter.print(integerStorage);

        assertEquals(excepted, output.toString());
    }
}
