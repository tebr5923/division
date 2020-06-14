package com.foxminded.storage;

import java.util.List;

public class IntegerStorage implements Storage {
    private List<Representation> representations;
    private int dividend;
    private int divider;
    private int mod;
    private int result;

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

        if (dividend != that.dividend) return false;
        if (divider != that.divider) return false;
        if (mod != that.mod) return false;
        if (result != that.result) return false;
        return representations.equals(that.representations);
    }

    @Override
    public int hashCode() {
        int result1 = representations.hashCode();
        result1 = 31 * result1 + dividend;
        result1 = 31 * result1 + divider;
        result1 = 31 * result1 + mod;
        result1 = 31 * result1 + result;
        return result1;
    }
}
