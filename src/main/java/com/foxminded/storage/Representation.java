package com.foxminded.storage;

import java.util.List;

public class Representation {
    private int dividend;
    private int divider;
    private int mod;
    private int result;
    private List<Integer> column;

    public Representation(int dividend, int divider, int mod, int result, List<Integer> column) {
        this.dividend = dividend;
        this.divider = divider;
        this.mod = mod;
        this.result = result;
        this.column = column;
    }

    public int getDividend() {
        return dividend;
    }

    public int getDivider() {
        return divider;
    }

    public int getMod() {
        return mod;
    }

    public int getResult() {
        return result;
    }

    public List<Integer> getColumn() {
        return column;
    }
}
