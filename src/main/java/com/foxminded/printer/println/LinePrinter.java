package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;

import java.util.Arrays;
import java.util.ListIterator;

abstract class LinePrinter {
    protected static final char MINUS = '_';
    protected static final char DASH = '-';
    protected static final char SPACE = ' ';

    protected final Print print;

    protected LinePrinter(Print print) {
        this.print = print;
    }

    protected int getPrintedLength(Object object) {
        return String.valueOf(object).length();
    }

    protected String repeatCharSomeTimes(char ch, int times) {
        char[] chars = new char[times];
        Arrays.fill(chars, ch);
        return new String(chars);
    }

    abstract void printLine(ListIterator<? extends NumberWithPosition<?>> iterator);
}
