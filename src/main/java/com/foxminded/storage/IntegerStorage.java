package com.foxminded.storage;

import java.util.List;
import java.util.Objects;

public class IntegerStorage implements Storage<Integer> {
    private final List<Representation> representations;
    private final int dividend;
    private final int divider;
    private final int mod;
    private final int result;

    public IntegerStorage(List<Representation> representations, int dividend, int divider) {
        this.representations = representations;
        this.dividend = dividend;
        this.divider = divider;
        this.mod = dividend % divider;
        this.result = dividend / divider;
    }

    @Override
    public List<Representation> getRepresentations() {
        return representations;
    }

    @Override
    public Integer getDividend() {
        return dividend;
    }

    @Override
    public Integer getDivider() {
        return divider;
    }

    @Override
    public Integer getMod() {
        return mod;
    }

    @Override
    public Integer getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerStorage that = (IntegerStorage) o;
        return dividend == that.dividend &&
                divider == that.divider &&
                representations.equals(that.representations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(representations, dividend, divider);
    }
}
