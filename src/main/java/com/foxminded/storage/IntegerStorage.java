package com.foxminded.storage;

import java.util.List;

public class IntegerStorage implements Storage {
    private final List<StepRepresentation> stepRepresentations;
    private final int dividend;
    private final int divider;
    private final int mod;
    private final int result;

    public IntegerStorage(List<StepRepresentation> stepRepresentations,
                          int dividend,
                          int divider,
                          int mod,
                          int result) {
        this.stepRepresentations = stepRepresentations;
        this.dividend = dividend;
        this.divider = divider;
        this.mod = mod;
        this.result = result;
    }

    @Override
    public List<StepRepresentation> getStepRepresentations() {
        return stepRepresentations;
    }

    @Override
    public int getDividend() {
        return dividend;
    }

    @Override
    public int getDivider() {
        return divider;
    }

    @Override
    public int getMod() {
        return mod;
    }

    @Override
    public int getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegerStorage that = (IntegerStorage) o;

        if (dividend != that.dividend) return false;
        if (divider != that.divider) return false;
        if (mod != that.mod) return false;
        if (result != that.result) return false;
        return stepRepresentations.equals(that.stepRepresentations);
    }

    @Override
    public int hashCode() {
        int result1 = stepRepresentations.hashCode();
        result1 = 31 * result1 + dividend;
        result1 = 31 * result1 + divider;
        result1 = 31 * result1 + mod;
        result1 = 31 * result1 + result;
        return result1;
    }
}
