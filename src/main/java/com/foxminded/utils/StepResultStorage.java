package com.foxminded.utils;

import com.foxminded.storage.Representation;

public class StepResultStorage {
    private Representation multiplication;
    private Representation mod;

    public StepResultStorage(Representation multiplication, Representation mod) {
        this.multiplication = multiplication;
        this.mod = mod;
    }

    public Representation getMultiplication() {
        return multiplication;
    }

    public void setMultiplication(Representation multiplication) {
        this.multiplication = multiplication;
    }

    public Representation getMod() {
        return mod;
    }

    public void setMod(Representation mod) {
        this.mod = mod;
    }
}
