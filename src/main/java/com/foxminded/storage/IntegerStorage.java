package com.foxminded.storage;

import java.util.List;
import java.util.Objects;

public class IntegerStorage implements Storage {
    private final List<Representation> representations;
    private final int dividend;
    private final int divider;
    private final int mod;
    private final int result;

    public IntegerStorage(List<Representation> representations,
                          int dividend,
                          int divider,
                          int mod,
                          int result) {
        this.representations = representations;
        this.dividend = dividend;
        this.divider = divider;
        this.mod = mod;
        this.result = result;
    }

    @Override
    public List<Representation> getRepresentations() {
        return representations;
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
        return dividend == that.dividend &&
                divider == that.divider;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dividend, divider);
    }
}
