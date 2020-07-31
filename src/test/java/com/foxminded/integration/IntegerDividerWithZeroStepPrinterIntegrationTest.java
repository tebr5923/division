package com.foxminded.integration;

import com.foxminded.divider.IntegerDividerWithZeroStep;
import com.foxminded.util.LineStringJoiner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerDividerWithZeroStepPrinterIntegrationTest extends AbstractIntegerDividerPrinterIntegrationTest {
    protected IntegerDividerWithZeroStepPrinterIntegrationTest() {
        super(new IntegerDividerWithZeroStep());
    }

    @Override
    @Test
    void printIntegerDivider_shouldPrintCorrectResult_whenResultWithZeroStage() {
        LineStringJoiner expected = new LineStringJoiner()
                .add("_80468|5")
                .add(" 5    |-----")
                .add(" -    |16093")
                .add("_30")
                .add(" 30")
                .add(" --")
                .add("  _4")
                .add("   0")
                .add("   -")
                .add("  _46")
                .add("   45")
                .add("   --")
                .add("   _18")
                .add("    15")
                .add("    --")
                .add("     3");

        performDivisionPrint(80468, 5);

        assertEquals(expected.toString(), getOutputAsString());
    }
}
