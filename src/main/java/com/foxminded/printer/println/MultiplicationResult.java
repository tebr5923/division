package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;

import java.util.ListIterator;

class MultiplicationResult extends LinePrinter {
    MultiplicationResult(Print print) {
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
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                numberWithPosition.getNumber(),
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                repeatCharSomeTimes(DASH, getPrintedLength(numberWithPosition.getNumber())));
    }
}
