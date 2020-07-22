package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerDividerWithNoZeroStepTest extends AbstractIntegerDividerTest {

    public IntegerDividerWithNoZeroStepTest() {
        super(new IntegerDividerWithNoZeroStep());
    }

    @Test
    void divide_shouldReturnResultWithPositiveReminder() {
        int dividend = 100154;
        int divider = 249;
        IntegerStorage excepted = new IntegerStorageBuilder(dividend, divider)
                .addStep(dividend, 0)
                .addStep(996, 1)
                .addStep(554, 3)
                .addStep(498, 3)
                .addStep(56, 4)
                .build();
        IntegerStorage actual = integerDivider.divide(dividend, divider);

        assertEquals(excepted, actual);
    }

    @Test
    void divide_shouldReturnResult_whenDividendAndDividerMultipleOfTen() {
        int dividend = 10000;
        int divider = 10;
        IntegerStorage excepted = new IntegerStorageBuilder(dividend, divider)
                .addStep(dividend, 0)
                .addStep(10, 0)
                .addStep(0, 4)
                .build();

        IntegerStorage actual = integerDivider.divide(dividend, divider);

        assertEquals(excepted, actual);
    }

    @Test
    void divide_shouldReturnResultWithZeroStage() {
        int dividend = 80468;
        int divider = 5;
        IntegerStorage excepted = new IntegerStorageBuilder(dividend, divider)
                .addStep(dividend, 0)
                .addStep(5, 0)
                .addStep(30, 0)
                .addStep(30, 0)
                .addStep(46, 2)
                .addStep(45, 2)
                .addStep(18, 3)
                .addStep(15, 3)
                .addStep(3, 4)
                .build();

        IntegerStorage actual = integerDivider.divide(dividend, divider);

        assertEquals(excepted, actual);
    }

    @Test
    void divide_shouldReturnResultWithConsequentZeroStage() {
        int dividend = 999100084;
        int divider = 998;
        IntegerStorage excepted = new IntegerStorageBuilder(dividend, divider)
                .addStep(dividend, 0)
                .addStep(998, 0)
                .addStep(1100, 2)
                .addStep(998, 3)
                .addStep(1020, 3)
                .addStep(998, 4)
                .addStep(2284, 5)
                .addStep(1996, 5)
                .addStep(288, 6)
                .build();

        IntegerStorage actual = integerDivider.divide(dividend, divider);

        assertEquals(excepted, actual);
    }

    @Test
    void divide_shouldReturnResultWithConsequentZeroStage2() {
        int dividend = 1000010123;
        int divider = 10;
        IntegerStorage excepted = new IntegerStorageBuilder(dividend, divider)
                .addStep(dividend, 0)
                .addStep(10, 0)
                .addStep(10, 5)
                .addStep(10, 5)
                .addStep(12, 7)
                .addStep(10, 7)
                .addStep(23, 8)
                .addStep(20, 8)
                .addStep(3, 9)
                .build();

        IntegerStorage actual = integerDivider.divide(dividend, divider);

        assertEquals(excepted, actual);
    }
}
