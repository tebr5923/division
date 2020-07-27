package com.foxminded.integration;

import com.foxminded.divider.IntegerDividerWithNoZeroStep;
import com.foxminded.util.LineStringJoiner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerDividerWithNoZeroStepPrinterIntegrationTest extends AbstractIntegerDividerPrinterIntegrationTest {
    public IntegerDividerWithNoZeroStepPrinterIntegrationTest() {
        super(new IntegerDividerWithNoZeroStep());
    }

    @Test
    void printIntegerDivider_shouldPrintCorrectResult_whenResultWithZeroStage() {
        LineStringJoiner excepted = new LineStringJoiner()
                .add("_80468|5")
                .add(" 5    |-----")
                .add(" -    |16093")
                .add("_30")
                .add(" 30")
                .add(" --")
                .add("  _46")
                .add("   45")
                .add("   --")
                .add("   _18")
                .add("    15")
                .add("    --")
                .add("     3");

        performDivisionPrint(80468, 5);

        assertEquals(excepted.toString(), getOutputAsString());
    }
}
