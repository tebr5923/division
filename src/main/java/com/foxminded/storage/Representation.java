package com.foxminded.storage;

import java.util.Objects;

public class Representation {
    private final int number;
    private final int position;

    public Representation(int number, int position) {
        this.number = number;
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Representation that = (Representation) o;
        return number == that.number &&
                position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, position);
    }
}
