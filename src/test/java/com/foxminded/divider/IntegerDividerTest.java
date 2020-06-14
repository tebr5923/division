package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;
import com.foxminded.storage.StepRepresentation;
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
        int mod = 144;
        int result = 3921;
        List<StepRepresentation> stepRepresentations = new ArrayList<>();
        stepRepresentations.add(new StepRepresentation(dividend, 0));
        stepRepresentations.add(new StepRepresentation(765, 0));
        stepRepresentations.add(new StepRepresentation(2349, 0));
        stepRepresentations.add(new StepRepresentation(2295, 0));
        stepRepresentations.add(new StepRepresentation(549, 2));
        stepRepresentations.add(new StepRepresentation(510, 2));
        stepRepresentations.add(new StepRepresentation(399, 3));
        stepRepresentations.add(new StepRepresentation(255, 3));
        stepRepresentations.add(new StepRepresentation(144, 3));
        IntegerStorage excepted = new IntegerStorage(stepRepresentations, dividend, divider, mod, result);

        //IntegerDivider integerDivider = new IntegerDivider();
        IntegerStorage integerStorageResult = integerDivider.divide(dividend, divider);

        assertEquals(excepted, integerStorageResult);
    }

    @Test
    public void divide_shouldThrowNullThrowNullPointerException_whenDividerIsNull() {
        int dividend = 999999;
        int divider = 0;

        //IntegerDivider integerDivider = new IntegerDivider();

        assertThrows(NullPointerException.class, () ->
                integerDivider.divide(dividend, divider)
        );
    }

    @Test
    public void divide_shouldThrowIllegalArgumentException_whenDividerIsNegative() {
        int dividend = 999999;
        int divider = -1;

        //IntegerDivider integerDivider = new IntegerDivider();

        assertThrows(IllegalArgumentException.class, () ->
                integerDivider.divide(dividend, divider)
        );
    }

    @Test
    public void divide_shouldThrowIllegalArgumentException_whenDividerMoreThenDividend() {
        int dividend = 999;
        int divider = 9999;

        //IntegerDivider integerDivider = new IntegerDivider();

        assertThrows(IllegalArgumentException.class, () ->
                integerDivider.divide(dividend, divider)
        );
    }
}
