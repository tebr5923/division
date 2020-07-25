package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;

import java.util.ListIterator;

class RemainderPrinter extends LinePrinter {
    RemainderPrinter(Print print) {
        super(print);
    }

    @Override
    protected LinePrinter getNextLinePrinter() {
        return new RemainderPrinter(print);
    }

    @Override
    protected String formatLine(ListIterator<? extends NumberWithPosition<?>> iterator) {
        NumberWithPosition<?> numberWithPosition = iterator.next();
        return String.format("%s%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                numberWithPosition.getNumber());
    }
}
