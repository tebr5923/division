package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;
import com.foxminded.storage.NumberWithPosition;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public abstract class AbstractIntegerDividerTest {
    protected IntegerDividerTemplate integerDivider;

    protected AbstractIntegerDividerTest(IntegerDividerTemplate divider) {
        this.integerDivider = divider;
    }

    @Test
    public void divide_shouldThrowIllegalArgumentException_whenDividendIsNegative() {
        int dividend = -1;
        int divider = 55;

        // TODO: 24.06.2020 add test message
        assertThrows(IllegalArgumentException.class, () ->
                integerDivider.divide(dividend, divider)
        );
    }

    @Test
    public void divide_shouldThrowIllegalArgumentException_whenDividerIsNull() {
        int dividend = 999999;
        int divider = 0;

        // TODO: 24.06.2020 add test message
        assertThrows(IllegalArgumentException.class, () ->
                integerDivider.divide(dividend, divider)
        );
    }

    @Test
    public void divide_shouldThrowIllegalArgumentException_whenDividerIsNegative() {
        int dividend = 999999;
        int divider = -1;

        // TODO: 24.06.2020 add test message
        assertThrows(IllegalArgumentException.class, () ->
                integerDivider.divide(dividend, divider)
        );
    }

    @Test
    public void divide_shouldReturnResult_whenArgumentsIsRandom() {
        int dividend = 100154;
        int divider = 249;
        List<NumberWithPosition> representations = new ArrayList<>();
        representations.add(new NumberWithPosition(dividend, 0));
        representations.add(new NumberWithPosition(996, 1));
        representations.add(new NumberWithPosition(554, 3));
        representations.add(new NumberWithPosition(498, 3));
        representations.add(new NumberWithPosition(56, 4));
        IntegerStorage excepted = new IntegerStorage(representations, dividend, divider);

        IntegerStorage integerStorageResult = integerDivider.divide(dividend, divider);

        assertEquals(excepted, integerStorageResult);
    }

    @Test
    public void divide_shouldReturnResult_whenDividerMoreThenDividend() {
        int dividend = 999;
        int divider = 9999;
        List<NumberWithPosition> representations = new ArrayList<>();
        representations.add(new NumberWithPosition(dividend, 0));
        representations.add(new NumberWithPosition(0, 2));
        representations.add(new NumberWithPosition(dividend, 0));
        IntegerStorage excepted = new IntegerStorage(representations, dividend, divider);

        IntegerStorage integerStorageResult = integerDivider.divide(dividend, divider);

        assertEquals(excepted, integerStorageResult);
    }

    @Test
    public void divide_shouldReturnResult_whenDividendEqualsDivider() {
        int dividend = 55;
        int divider = 55;
        List<NumberWithPosition> representations = new ArrayList<>();
        representations.add(new NumberWithPosition(dividend, 0));
        representations.add(new NumberWithPosition(55, 0));
        representations.add(new NumberWithPosition(0, 1));
        IntegerStorage excepted = new IntegerStorage(representations, dividend, divider);

        IntegerStorage integerStorageResult = integerDivider.divide(dividend, divider);

        assertEquals(excepted, integerStorageResult);
    }

    @Test
    public void divide_shouldReturnResult_whenOneDigitDividendAndDivider() {
        int dividend = 9;
        int divider = 5;
        List<NumberWithPosition> representations = new ArrayList<>();
        representations.add(new NumberWithPosition(dividend, 0));
        representations.add(new NumberWithPosition(5, 0));
        representations.add(new NumberWithPosition(4, 0));
        IntegerStorage excepted = new IntegerStorage(representations, dividend, divider);

        IntegerStorage integerStorageResult = integerDivider.divide(dividend, divider);

        assertEquals(excepted, integerStorageResult);
    }

    @Test
    public void divide_shouldReturnResult_whenOneDigitDividerAndBitLargerDividend() {
        int dividend = 84;
        int divider = 5;
        List<NumberWithPosition> representations = new ArrayList<>();
        representations.add(new NumberWithPosition(dividend, 0));
        representations.add(new NumberWithPosition(5, 0));
        representations.add(new NumberWithPosition(34, 0));
        representations.add(new NumberWithPosition(30, 0));
        representations.add(new NumberWithPosition(4, 1));
        IntegerStorage excepted = new IntegerStorage(representations, dividend, divider);

        IntegerStorage integerStorageResult = integerDivider.divide(dividend, divider);

        assertEquals(excepted, integerStorageResult);
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

        IntegerStorage integerStorageResult = integerDivider.divide(dividend, divider);

        assertEquals(excepted, integerStorageResult);
    }

    @Test
    public void divide_shouldReturnResultWithZeroReminder() {
        int dividend = 840;
        int divider = 24;
        List<NumberWithPosition> representations = new ArrayList<>();
        representations.add(new NumberWithPosition(dividend, 0));
        representations.add(new NumberWithPosition(72, 0));
        representations.add(new NumberWithPosition(120, 0));
        representations.add(new NumberWithPosition(120, 0));
        representations.add(new NumberWithPosition(0, 2));
        IntegerStorage excepted = new IntegerStorage(representations, dividend, divider);

        IntegerStorage integerStorageResult = integerDivider.divide(dividend, divider);

        assertEquals(excepted, integerStorageResult);
    }

    @Test
    abstract void divide_shouldReturnResult_whenDividendAndDividerMultipleOfTen();

    @Test
    abstract void divide_shouldReturnResultWithZeroStage();

    @Test
    abstract void divide_shouldReturnResultWithConsequentZeroStage();

    @Test
    abstract void divide_shouldReturnResultWithConsequentZeroStage2();
}
