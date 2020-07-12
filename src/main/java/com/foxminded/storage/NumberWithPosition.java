package com.foxminded.storage;

import java.util.Objects;

public class NumberWithPosition {
    private final int number;
    private final int position;

    public NumberWithPosition(int number, int position) {
        this.number = number;
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public int getPosition() {
        return position;
    }

    public NumberWithPosition addOffset(int offset) {
        return new NumberWithPosition(number, this.position + offset);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberWithPosition that = (NumberWithPosition) o;
        return number == that.number &&
                position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, position);
    }
}
