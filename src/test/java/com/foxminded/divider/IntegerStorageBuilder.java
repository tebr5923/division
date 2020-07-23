package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;
import com.foxminded.storage.NumberWithPosition;

import java.util.ArrayList;
import java.util.List;

public class IntegerStorageBuilder {
    private final List<NumberWithPosition<Integer>> representations;
    private final int dividend;
    private final int divider;

    public IntegerStorageBuilder(int dividend, int divider) {
        this.dividend = dividend;
        this.divider = divider;
        this.representations = new ArrayList<>();
    }

    public IntegerStorageBuilder addStep(int number, int position) {
        representations.add(new NumberWithPosition<>(number, position));
        return this;
    }

    public IntegerStorage build() {
        return new IntegerStorage(representations, dividend, divider);
    }
}
