package com.foxminded.divider;

import com.foxminded.storage.Representation;

public class StepResultStorage {
    private final Representation multiplication;
    private final Representation mod;

    StepResultStorage(Representation multiplication, Representation mod) {
        this.multiplication = multiplication;
        this.mod = mod;
    }

    int getNextPosition(int currentPosition) {
        int nextPosition;
        if (getMultNumber() == 0) {
            nextPosition = currentPosition + getModPosition();
        } else {
            nextPosition = currentPosition +
                    Math.max(getMultPosition(), getModPosition());
        }
        return nextPosition;
    }

    Representation getMultiplication() {
        return multiplication;
    }

    Representation getMod() {
        return mod;
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
}
