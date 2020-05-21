package com.foxminded.storage;

import java.util.ArrayList;
import java.util.List;

public class IntegerStorage implements Storage {
    private List<String> representation;

    private Integer dividend;
    private Integer divider;
    private Integer mod;
    private Integer result;
    private List<Integer> column;

    public IntegerStorage(Integer dividend, Integer divider,
                          Integer mod, Integer result, List<Integer> column) {
        this.dividend = dividend;
        this.divider = divider;
        this.mod = mod;
        this.result = result;
        this.column = column;
    }

    public void generateRepresentation() {
        representation = new ArrayList<>();

        String minus = "_";

        representation.add(minus + dividend + "|" + divider);
        representation.add(" " + column.get(0) + " " + "|" + "-----");
        representation.add(" " + "--" + " " + "|" + result);
        representation.add(" " + minus + column.get(1));
        representation.add(" " + " " + column.get(2));
        representation.add(" " + " " + "--");
        representation.add(" " + " " + " " + mod);
    }

    public List<String> getRepresentation() {
        return representation;
    }
}
