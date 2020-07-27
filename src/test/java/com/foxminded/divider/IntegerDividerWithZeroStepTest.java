package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;
import com.foxminded.util.IntegerStorageBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerDividerWithZeroStepTest extends AbstractIntegerDividerTest {

    public IntegerDividerWithZeroStepTest() {
        super(new IntegerDividerWithZeroStep());
    }

    @Test
    void divide_shouldReturnResult_whenDividendAndDividerMultipleOfTen() {
        int dividend = 10000;
        int divider = 10;
        IntegerStorage excepted = new IntegerStorageBuilder(dividend, divider)
                .addStep(dividend, 0)
                .addStep(10, 0)
                .addStep(0, 2)
                .addStep(0, 2)
                .addStep(0, 3)
                .addStep(0, 3)
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
                .addStep(4, 2)
                .addStep(0, 2)
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
        int dividend = 1000010123;
        int divider = 10;
        IntegerStorage excepted = new IntegerStorageBuilder(dividend, divider)
                .addStep(dividend, 0)
                .addStep(10, 0)
                .addStep(0, 2)
                .addStep(0, 2)
                .addStep(0, 3)
                .addStep(0, 3)
                .addStep(0, 4)
                .addStep(0, 4)
                .addStep(1, 5)
                .addStep(0, 5)
                .addStep(10, 5)
                .addStep(10, 5)
                .addStep(1, 7)
                .addStep(0, 7)
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
