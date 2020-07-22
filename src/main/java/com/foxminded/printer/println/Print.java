package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;
import com.foxminded.storage.Storage;

public class Print {
    private LinePrinter linePrinter;
    private final Storage<?> storage;

    public Print(Storage<?> storage) {
        this.linePrinter = new FirstLinePrinter(this);
        this.storage = storage;
    }

    void setLinePrinter(LinePrinter linePrinter) {
        this.linePrinter = linePrinter;
    }

    public void printLine(NumberWithPosition numberWithPosition) {
        linePrinter.printLine(numberWithPosition);
    }

    Storage<?> getStorage() {
        return storage;
    }
}
