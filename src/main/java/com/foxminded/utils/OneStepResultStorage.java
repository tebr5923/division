package com.foxminded.utils;

import com.foxminded.storage.StepRepresentation;

public class OneStepResultStorage {
    private StepRepresentation multiplication;
    private StepRepresentation mod;

    public OneStepResultStorage(StepRepresentation multiplication, StepRepresentation mod) {
        this.multiplication = multiplication;
        this.mod = mod;
    }

    public StepRepresentation getMultiplication() {
        return multiplication;
    }

    public void setMultiplication(StepRepresentation multiplication) {
        this.multiplication = multiplication;
    }

    public StepRepresentation getMod() {
        return mod;
    }

    public void setMod(StepRepresentation mod) {
        this.mod = mod;
    }
}
