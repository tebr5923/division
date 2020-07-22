package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;
import com.foxminded.storage.Storage;

import java.util.Iterator;

public class Print {
    private LinePrinter linePrinter;
    //private final Storage<?> storage;

    public Print(Storage<?> storage) {
        //this.linePrinter = new FirstLinePrinter(this);
        this.linePrinter = new FirstLinePrinter(this, storage);
        //this.storage = storage;
    }

    void setLinePrinter(LinePrinter linePrinter) {
        this.linePrinter = linePrinter;
    }

 /*   public void printLine(NumberWithPosition numberWithPosition) {
        linePrinter.printLine(numberWithPosition);
    }*/

    public void printLine(Iterator<NumberWithPosition> iterator) {
        linePrinter.printLine(iterator);
    }

   /* Storage<?> getStorage() {
        return storage;
    }*/
}
