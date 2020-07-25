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
        String line1 = String.format("%s%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                numberWithPosition.getNumber());
        String line2 = String.format("%s%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                repeatCharSomeTimes(DASH, getPrintedLength(numberWithPosition.getNumber())));
        return String.join("", line1, line2);
    }
}
