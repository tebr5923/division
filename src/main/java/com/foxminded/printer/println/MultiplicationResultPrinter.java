package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;

import java.util.ListIterator;

class MultiplicationResultPrinter extends LinePrinter {
    MultiplicationResultPrinter(Print print) {
        super(print);
    }

    @Override
    protected LinePrinter getNextLinePrinter() {
        return new ShortDividendPrinter(print);
    }

    @Override
    protected String formatLine(ListIterator<? extends NumberWithPosition<?>> iterator) {
        NumberWithPosition<?> numberWithPosition = iterator.next();
        return String.format("%s%s%n%s%s%n",
                repeatChar(SPACE, numberWithPosition.getPosition() + 1),
                numberWithPosition.getValue(),
                repeatChar(SPACE, numberWithPosition.getPosition() + 1),
                repeatChar(DASH, getPrintedLength(numberWithPosition.getValue())));
    }
}
