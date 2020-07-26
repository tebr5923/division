package com.foxminded.divider;

import com.foxminded.storage.NumberWithPosition;

class StepResultStorage<T> {
    private final NumberWithPosition<T> multiplicationResult;
    private final NumberWithPosition<T> remainder;

    StepResultStorage(NumberWithPosition<T> multiplicationResult, NumberWithPosition<T> remainder) {
        this.multiplicationResult = multiplicationResult;
        this.remainder = remainder;
    }

    T getMultiplicationResultNumber() {
        return multiplicationResult.getValue();
    }

    int getMultiplicationResultPosition() {
        return multiplicationResult.getPosition();
    }

    T getRemainderNumber() {
        return remainder.getValue();
    }

    int getRemainderPosition() {
        return remainder.getPosition();
    }

    NumberWithPosition<T> getMultiplicationResult() {
        return multiplicationResult;
    }

    NumberWithPosition<T> getRemainder() {
        return remainder;
    }
}
