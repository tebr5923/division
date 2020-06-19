package com.foxminded.storage;

import java.util.Objects;

public class Representation {
    private int number;
    private int position;

    public Representation(int number, int position) {
        this.number = number;
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
