package com.foxminded.divider.utils;

import com.foxminded.storage.Representation;

public class StepResultStorage {
    private final Representation multiplication;
    private final Representation mod;

    public StepResultStorage(Representation multiplication, Representation mod) {
        this.multiplication = multiplication;
        this.mod = mod;
    }

    public int getNextPosition(int currentPosition) {
        int nextPosition;
        if (getMultNumber() == 0) {
            nextPosition = currentPosition + getModPosition();
        } else {
            nextPosition = currentPosition +
                    Math.max(getMultPosition(), getModPosition());
        }
        return nextPosition;
    }

    public Representation getMultiplication() {
        return multiplication;
    }

    public Representation getMod() {
        return mod;
    }

    public int getMultNumber() {
        return multiplication.getNumber();
    }

    public int getMultPosition() {
        return multiplication.getPosition();
    }

    public int getModNumber() {
        return mod.getNumber();
    }

    public int getModPosition() {
        return mod.getPosition();
    }
}
