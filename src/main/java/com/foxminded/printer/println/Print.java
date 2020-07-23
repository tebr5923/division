package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;
import com.foxminded.storage.Storage;

import java.util.ListIterator;

public class Print {
    private LinePrinter linePrinter;

    public Print(Storage<?> storage) {
        this.linePrinter = new FirstLinePrinter(this, storage);
    }

    void setLinePrinter(LinePrinter linePrinter) {
        this.linePrinter = linePrinter;
    }

    public void printLine(ListIterator<? extends NumberWithPosition<?>> iterator) {
        linePrinter.printLine(iterator);
    }
}
