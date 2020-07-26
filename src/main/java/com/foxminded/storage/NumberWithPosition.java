package com.foxminded.storage;

import java.util.Objects;

public class NumberWithPosition<T> {
    private final T number;
    private final int position;

    public NumberWithPosition(T number, int position) {
        this.number = number;
        this.position = position;
    }

    public T getValue() {
        return number;
    }

    public int getPosition() {
        return position;
    }

    public NumberWithPosition<T> addOffset(int offset) {
        return new NumberWithPosition<>(number, this.position + offset);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberWithPosition<?> that = (NumberWithPosition<?>) o;
        return position == that.position &&
                number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, position);
    }
}
