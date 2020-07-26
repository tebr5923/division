package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


abstract class AbstractIntegerDividerTest {
    protected IntegerDividerTemplate integerDivider;

    protected AbstractIntegerDividerTest(IntegerDividerTemplate divider) {
        this.integerDivider = divider;
    }

    @Test
    void divide_shouldThrowIllegalArgumentException_whenDividendIsNegative() {
        int dividend = -1;
        int divider = 55;

        // TODO: 24.06.2020 add test message
        assertThrows(IllegalArgumentException.class, () ->
                integerDivider.divide(dividend, divider)
        );
    }

    @Test
    void divide_shouldThrowIllegalArgumentException_whenDividerIsZero() {
        int dividend = 999999;
        int divider = 0;

        // TODO: 24.06.2020 add test message
        assertThrows(IllegalArgumentException.class, () ->
                integerDivider.divide(dividend, divider)
        );
    }

    @Test
    void divide_shouldThrowIllegalArgumentException_whenDividerIsNegative() {
        int dividend = 999999;
        int divider = -1;

        // TODO: 24.06.2020 add test message
        assertThrows(IllegalArgumentException.class, () ->
                integerDivider.divide(dividend, divider)
        );
    }

    @Test
    void divide_shouldReturnResult_whenDividerMoreThenDividend() {
        int dividend = 999;
        int divider = 9999;
        IntegerStorage excepted = new IntegerStorageBuilder(dividend, divider)
                .addStep(dividend, 0)
                .addStep(0, 2)
                .addStep(dividend, 0)
                .build();

        IntegerStorage actual = integerDivider.divide(dividend, divider);

        assertEquals(excepted, actual);
    }

    @Test
    void divide_shouldReturnResult_whenDividendEqualsDivider() {
        int dividend = 55;
        int divider = 55;
        IntegerStorage excepted = new IntegerStorageBuilder(dividend, divider)
                .addStep(dividend, 0)
                .addStep(55, 0)
                .addStep(0, 1)
                .build();

        IntegerStorage actual = integerDivider.divide(dividend, divider);

        assertEquals(excepted, actual);
    }

    @Test
    void divide_shouldReturnResult_whenOneDigitDividendAndDivider() {
        int dividend = 9;
        int divider = 5;
        IntegerStorage excepted = new IntegerStorageBuilder(dividend, divider)
                .addStep(dividend, 0)
                .addStep(5, 0)
                .addStep(4, 0)
                .build();

        IntegerStorage actual = integerDivider.divide(dividend, divider);

        assertEquals(excepted, actual);
    }

    @Test
    void divide_shouldReturnResult_whenOneDigitDividerAndBitLargerDividend() {
        int dividend = 84;
        int divider = 5;
        IntegerStorage excepted = new IntegerStorageBuilder(dividend, divider)
                .addStep(dividend, 0)
                .addStep(5, 0)
                .addStep(34, 0)
                .addStep(30, 0)
                .addStep(4, 1)
                .build();

        IntegerStorage actual = integerDivider.divide(dividend, divider);

        assertEquals(excepted, actual);
    }

    @Test
    void divide_shouldReturnResultWithZeroReminder() {
        int dividend = 840;
        int divider = 24;
        IntegerStorage excepted = new IntegerStorageBuilder(dividend, divider)
                .addStep(dividend, 0)
                .addStep(72, 0)
                .addStep(120, 0)
                .addStep(120, 0)
                .addStep(0, 2)
                .build();

        IntegerStorage actual = integerDivider.divide(dividend, divider);

        assertEquals(excepted, actual);
    }

    @Test
    void divide_shouldReturnResultWithPositiveReminder() {
        int dividend = 100154;
        int divider = 24;
        IntegerStorage excepted = new IntegerStorageBuilder(dividend, divider)
                .addStep(dividend, 0)
                .addStep(96, 1)
                .addStep(41, 2)
                .addStep(24, 2)
                .addStep(175,2)
                .addStep(168,2)
                .addStep(74,4)
                .addStep(72,4)
                .addStep(2,5)
                .build();
        IntegerStorage actual = integerDivider.divide(dividend, divider);

        assertEquals(excepted, actual);
    }

    @Test
    abstract void divide_shouldReturnResult_whenDividendAndDividerMultipleOfTen();

    @Test
    abstract void divide_shouldReturnResultWithZeroStage();

    @Test
    abstract void divide_shouldReturnResultWithConsequentZeroStage();

}
