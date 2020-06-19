package com.foxminded.utils;

import com.foxminded.storage.Representation;

public class StepResultStorage {
    private final Representation multiplication;
    private final Representation mod;

    public StepResultStorage(Representation multiplication, Representation mod) {
        this.multiplication = multiplication;
        this.mod = mod;
    }

    public Representation getMultiplication() {
        return multiplication;
    }


    public Representation getMod() {
        return mod;
    }

}
