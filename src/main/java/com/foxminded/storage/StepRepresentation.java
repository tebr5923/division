package com.foxminded.storage;

public class StepRepresentation {
    private int number;
    private int position;

    public StepRepresentation(int number, int position) {
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

        StepRepresentation that = (StepRepresentation) o;

        if (number != that.number) return false;
        return position == that.position;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + position;
        return result;
    }
}
