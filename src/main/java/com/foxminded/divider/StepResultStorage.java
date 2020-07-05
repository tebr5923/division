package com.foxminded.divider;

import com.foxminded.storage.NumberWithPosition;

public class StepResultStorage {
    private final NumberWithPosition multiplicationResult;
    private final NumberWithPosition remainder;

    StepResultStorage(NumberWithPosition multiplicationResult, NumberWithPosition remainder) {
        this.multiplicationResult = multiplicationResult;
        this.remainder = remainder;
    }

    int getNextPosition(int currentPosition) {
        return currentPosition
                + (getMultiplicationResultNumber() == 0
                ? getRemainderPosition()
                : Math.max(getMultiplicationResultPosition(), getRemainderPosition()));
    }

    int getMultiplicationResultNumber() {
        return multiplicationResult.getNumber();
    }

    int getMultiplicationResultPosition() {
        return multiplicationResult.getPosition();
    }

    int getRemainderNumber() {
        return remainder.getNumber();
    }

    int getRemainderPosition() {
        return remainder.getPosition();
    }

    NumberWithPosition getMultiplicationResult() {
        return multiplicationResult;
    }

    NumberWithPosition getRemainder() {
        return remainder;
    }
}
