package com.foxminded.storage;

import java.util.List;
import java.util.Objects;

public class IntegerStorage implements Storage<Integer> {
    private final List<NumberWithPosition<Integer>> representations;
    private final int dividend;
    private final int divider;

    public IntegerStorage(List<NumberWithPosition<Integer>> representations, int dividend, int divider) {
        this.representations = representations;
        this.dividend = dividend;
        this.divider = divider;
    }

    @Override
    public List<NumberWithPosition<Integer>> getRepresentations() {
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
    public Integer getRemainder() {
        return dividend % divider;
    }

    @Override
    public Integer getResult() {
        return dividend / divider;
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
