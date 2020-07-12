package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;
import com.foxminded.storage.NumberWithPosition;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegerDividerWithNoZeroStepTest extends AbstractIntegerDividerTest{

    public IntegerDividerWithNoZeroStepTest() {
        super(new IntegerDividerWithNoZeroStep());
    }

    @Test
    public void divide_shouldReturnResultWithPositiveReminder() {
        int dividend = 100154;
        int divider = 249;
        List<NumberWithPosition> representations = new ArrayList<>();
        representations.add(new NumberWithPosition(dividend, 0));
        representations.add(new NumberWithPosition(996, 1));
        representations.add(new NumberWithPosition(554, 3));
        representations.add(new NumberWithPosition(498, 3));
        representations.add(new NumberWithPosition(56, 4));
        IntegerStorage excepted = new IntegerStorage(representations, dividend, divider);

        IntegerStorage actual = integerDivider.divide(dividend, divider);

        assertEquals(excepted, actual);
    }

    @Test
    public void divide_shouldReturnResult_whenDividendAndDividerMultipleOfTen() {
        int dividend = 10000;
        int divider = 10;
        List<NumberWithPosition> representations = new ArrayList<>();
        representations.add(new NumberWithPosition(dividend, 0));
        representations.add(new NumberWithPosition(10, 0));
        representations.add(new NumberWithPosition(0, 4));
        IntegerStorage excepted = new IntegerStorage(representations, dividend, divider);

        IntegerStorage actual = integerDivider.divide(dividend, divider);

        assertEquals(excepted, actual);
    }

    @Test
    public void divide_shouldReturnResultWithZeroStage() {
        int dividend = 80468;
        int divider = 5;
        List<NumberWithPosition> representations = new ArrayList<>();
        representations.add(new NumberWithPosition(dividend, 0));
        representations.add(new NumberWithPosition(5, 0));
        representations.add(new NumberWithPosition(30, 0));
        representations.add(new NumberWithPosition(30, 0));
        representations.add(new NumberWithPosition(46, 2));
        representations.add(new NumberWithPosition(45, 2));
        representations.add(new NumberWithPosition(18, 3));
        representations.add(new NumberWithPosition(15, 3));
        representations.add(new NumberWithPosition(3, 4));
        IntegerStorage excepted = new IntegerStorage(representations, dividend, divider);

        IntegerStorage actual = integerDivider.divide(dividend, divider);

        assertEquals(excepted, actual);
    }

    @Test
    public void divide_shouldReturnResultWithConsequentZeroStage() {
        int dividend = 999100084;
        int divider = 998;
        List<NumberWithPosition> representations = new ArrayList<>();
        representations.add(new NumberWithPosition(dividend, 0));
        representations.add(new NumberWithPosition(998, 0));
        representations.add(new NumberWithPosition(1100, 2));
        representations.add(new NumberWithPosition(998, 3));
        representations.add(new NumberWithPosition(1020, 3));
        representations.add(new NumberWithPosition(998, 4));
        representations.add(new NumberWithPosition(2284, 5));
        representations.add(new NumberWithPosition(1996, 5));
        representations.add(new NumberWithPosition(288, 6));
        IntegerStorage excepted = new IntegerStorage(representations, dividend, divider);

        IntegerStorage actual = integerDivider.divide(dividend, divider);

        assertEquals(excepted, actual);
    }

    @Test
    public void divide_shouldReturnResultWithConsequentZeroStage2() {
        int dividend = 1000010123;
        int divider = 10;
        List<NumberWithPosition> representations = new ArrayList<>();
        representations.add(new NumberWithPosition(dividend, 0));
        representations.add(new NumberWithPosition(10, 0));

        representations.add(new NumberWithPosition(10, 5));
        representations.add(new NumberWithPosition(10, 5));

        representations.add(new NumberWithPosition(12, 7));
        representations.add(new NumberWithPosition(10, 7));

        representations.add(new NumberWithPosition(23, 8));
        representations.add(new NumberWithPosition(20, 8));

        representations.add(new NumberWithPosition(3, 9));

        IntegerStorage excepted = new IntegerStorage(representations, dividend, divider);

        IntegerStorage actual = integerDivider.divide(dividend, divider);

        assertEquals(excepted, actual);
    }
}
