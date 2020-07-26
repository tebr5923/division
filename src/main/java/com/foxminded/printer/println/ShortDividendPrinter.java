package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;

import java.util.ListIterator;

class ShortDividendPrinter extends LinePrinter {
    ShortDividendPrinter(Printer printer) {
        super(printer);
    }

    @Override
    protected LinePrinter getNextLinePrinter() {
        return new MultiplicationResultPrinter(printer);
    }

    @Override
    protected String formatLine(ListIterator<? extends NumberWithPosition<?>> iterator) {
        NumberWithPosition<?> numberWithPosition = iterator.next();
        return String.format("%s%s%s%n",
                repeatChar(SPACE, numberWithPosition.getPosition()),
                iterator.hasNext() ? MINUS : SPACE,
                numberWithPosition.getValue());
    }
}
