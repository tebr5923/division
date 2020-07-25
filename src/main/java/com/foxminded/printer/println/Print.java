package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;
import com.foxminded.storage.Storage;

import java.util.ListIterator;

public class Print {
    private LinePrinter linePrinter;
    private final Storage<?> storage;

    public Print(Storage<?> storage) {
        this.storage = storage;
    }

    void setLinePrinter(LinePrinter linePrinter) {
        this.linePrinter = linePrinter;
    }

    public void print() {
        this.linePrinter = new HeaderPrinter(this, storage);
        ListIterator<? extends NumberWithPosition<?>> iterator = storage.getRepresentations().listIterator();
        while (iterator.hasNext()) {
            linePrinter.printLine(iterator);
        }
    }
}
