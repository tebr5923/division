package com.foxminded.integration;

import java.util.StringJoiner;

public class StringJoinerWithLineSeparator {
    static private final CharSequence DELIMITER = System.lineSeparator();
    static private final CharSequence PREFIX = "";
    static private final CharSequence SUFFIX = System.lineSeparator();

    private final StringJoiner stringJoiner;

    public StringJoinerWithLineSeparator() {
        this.stringJoiner = new StringJoiner(DELIMITER, PREFIX, SUFFIX);
    }

    public StringJoiner add(String string) {
        stringJoiner.add(string);
        return stringJoiner;
    }


}
