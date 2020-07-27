package com.foxminded.integration;

import com.foxminded.divider.IntegerDividerWithZeroStep;

public class IntegerDividerWithNoZeroStepPrinterIntegrationTest extends AbstractIntegerDividerPrinterIntegrationTest {

    public IntegerDividerWithNoZeroStepPrinterIntegrationTest() {
        super(new IntegerDividerWithZeroStep());
    }
}
