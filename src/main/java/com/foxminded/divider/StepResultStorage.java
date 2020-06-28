package com.foxminded.divider;

import com.foxminded.storage.NumberWithPosition;

public class StepResultStorage {
    private final NumberWithPosition multiplication;
    private final NumberWithPosition mod;

    StepResultStorage(NumberWithPosition multiplication, NumberWithPosition mod) {
        this.multiplication = multiplication;
        this.mod = mod;
    }

    int getNextPosition(int currentPosition) {
        return currentPosition
                + (getMultNumber() == 0
                ? getModPosition()
                : Math.max(getMultPosition(), getModPosition()));
    }

    int getMultNumber() {
        return multiplication.getNumber();
    }

    int getMultPosition() {
        return multiplication.getPosition();
    }

    int getModNumber() {
        return mod.getNumber();
    }

    int getModPosition() {
        return mod.getPosition();
    }

    NumberWithPosition getMultiplication() {
        return multiplication;
    }

    NumberWithPosition getMod() {
        return mod;
    }
}
