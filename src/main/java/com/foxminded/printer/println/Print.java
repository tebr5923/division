package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;
import com.foxminded.storage.Storage;

public class Print {
    private LinePrinter linePrinter;
    private final Storage<?> storage;

    public Print(Storage<?> storage) {
        this.linePrinter = new FirstLinePrinter(storage);
        this.storage = storage;
    }

    public void changePrinter() {
        if (linePrinter instanceof FirstLinePrinter) {
            linePrinter = new SecondLineAndDelimiterPrinter(storage);
        } else if (linePrinter instanceof SecondLineAndDelimiterPrinter) {
            linePrinter = new ShortDividendPrinter();
        } else if (linePrinter instanceof ShortDividendPrinter) {
            linePrinter = new MultiplicationResultAndDelimiterPrinter();
        } else if (linePrinter instanceof MultiplicationResultAndDelimiterPrinter) {
            linePrinter = new ShortDividendPrinter();
        }
    }

    public void setLinePrinter(LinePrinter linePrinter) {
        this.linePrinter = linePrinter;
    }

    public void printLine(NumberWithPosition numberWithPosition){
        linePrinter.printLine(numberWithPosition);
    }
}
