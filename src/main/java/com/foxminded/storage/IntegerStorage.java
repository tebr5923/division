package com.foxminded.storage;

import java.util.List;

public class IntegerStorage implements Storage {
    private List<String> representation;
    private Integer dividend;
    private Integer divider;
    private Integer mod;
    private Integer result;

    public IntegerStorage(Integer dividend, Integer divider,
                          Integer mod, Integer result, List<String> representation) {
        this.dividend = dividend;
        this.divider = divider;
        this.mod = mod;
        this.result = result;
        this.representation = representation;
    }

    public List<String> getRepresentation() {
        return representation;
    }

    public Integer getDividend() {
        return dividend;
    }

    public Integer getDivider() {
        return divider;
    }

    public Integer getMod() {
        return mod;
    }

    public Integer getResult() {
        return result;
    }
}
