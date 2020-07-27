package com.foxminded.util;

import java.util.StringJoiner;

public class LineStringJoiner {
    private static final CharSequence DELIMITER = System.lineSeparator();
    private static final CharSequence PREFIX = "";
    private static final CharSequence SUFFIX = System.lineSeparator();

    private final StringJoiner stringJoiner;

    public LineStringJoiner() {
        this.stringJoiner = new StringJoiner(DELIMITER, PREFIX, SUFFIX);
    }

    public LineStringJoiner add(String string) {
        stringJoiner.add(string);
        return this;
    }

    @Override
    public String toString() {
        return stringJoiner.toString();
    }
}
