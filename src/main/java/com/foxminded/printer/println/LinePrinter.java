package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;

import java.util.Arrays;

public abstract class LinePrinter {
    protected static final char MINUS = '_';
    protected static final char DASH = '-';
    protected static final char SPACE = ' ';

    abstract void printLine(NumberWithPosition numberWithPosition);


    protected int getPrintedLength(Object object) {
        return String.valueOf(object).length();
    }

    protected String repeatCharSomeTimes(char ch, int times) {
        char[] chars = new char[times];
        Arrays.fill(chars, ch);
        return new String(chars);
    }
}
