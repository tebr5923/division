package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;
import com.foxminded.storage.Representation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntegerDividerTest {
    private IntegerDivider integerDivider = new IntegerDivider();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void divide_shouldReturnResult_whenArgumentsIsRandom() {
        int dividend = 999999;
        int divider = 255;
        int mod = dividend % divider;
        int result = dividend / divider;
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
        IntegerStorage excepted = new IntegerStorage(representations, dividend, divider, mod, result);

        IntegerStorage integerStorageResult = integerDivider.divide(dividend, divider);

        assertEquals(excepted, integerStorageResult);
    }

    @Test
    void divide_shouldReturnResult_whenDividendAndDividerMultipleOfTen() {
        int dividend = 10000;
        int divider = 10;
        int mod = dividend % divider;
        int result = dividend / divider;
        List<Representation> representations = new ArrayList<>();
        representations.add(new Representation(dividend, 0));
        representations.add(new Representation(10, 0));
        representations.add(new Representation(0, 2));
        representations.add(new Representation(0, 2));
        representations.add(new Representation(0, 3));
        representations.add(new Representation(0, 3));
        representations.add(new Representation(0, 4));
        representations.add(new Representation(0, 4));
        representations.add(new Representation(0, 4));
        IntegerStorage excepted = new IntegerStorage(representations, dividend, divider, mod, result);

        IntegerStorage integerStorageResult = integerDivider.divide(dividend, divider);

        assertEquals(excepted, integerStorageResult);
    }

    @Test
    public void divide_shouldThrowNullThrowNullPointerException_whenDividerIsNull() {
        int dividend = 999999;
        int divider = 0;

        assertThrows(NullPointerException.class, () ->
                integerDivider.divide(dividend, divider)
        );
    }

    @Test
    public void divide_shouldThrowIllegalArgumentException_whenDividerIsNegative() {
        int dividend = 999999;
        int divider = -1;

        assertThrows(IllegalArgumentException.class, () ->
                integerDivider.divide(dividend, divider)
        );
    }

    @Test
    public void divide_shouldThrowIllegalArgumentException_whenDividerMoreThenDividend() {
        int dividend = 999;
        int divider = 9999;

        assertThrows(IllegalArgumentException.class, () ->
                integerDivider.divide(dividend, divider)
        );
    }
}
