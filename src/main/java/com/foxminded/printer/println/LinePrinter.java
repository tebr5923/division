package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;

import java.util.Arrays;
import java.util.ListIterator;

abstract class LinePrinter {
    protected static final char MINUS = '_';
    protected static final char DASH = '-';
    protected static final char SPACE = ' ';

    protected final Printer printer;

    protected LinePrinter(Printer printer) {
        this.printer = printer;
    }

    protected int getPrintedLength(Object object) {
        return String.valueOf(object).length();
    }

    protected String repeatChar(char ch, int times) {
        char[] chars = new char[times];
        Arrays.fill(chars, ch);
        return new String(chars);
    }

    protected void printLine(ListIterator<? extends NumberWithPosition<?>> iterator) {
        printer.setLinePrinter(getNextLinePrinter());
        printFormattedLine(formatLine(iterator));
    }

    @SuppressWarnings("squid:S106")
    private void printFormattedLine(String formattedLine) {
        System.out.print(formattedLine);
    }

    protected abstract LinePrinter getNextLinePrinter();

    protected abstract String formatLine(ListIterator<? extends NumberWithPosition<?>> iterator);
}
