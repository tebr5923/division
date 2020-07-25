package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;
import com.foxminded.storage.Storage;

import java.util.ListIterator;

class HeaderPrinter extends LinePrinter {
    private final Storage<?> storage;

    HeaderPrinter(Print print, Storage<?> storage) {
        super(print);
        this.storage = storage;
    }

    @Override
    protected LinePrinter getNextLinePrinter() {
        return new ShortDividendPrinter(print);
    }

    @SuppressWarnings("squid:S1192")
    @Override
    protected String formatLine(ListIterator<? extends NumberWithPosition<?>> iterator) {
        NumberWithPosition<?> numberWithPosition = iterator.next();
        String line1 = String.format("%s%s%s|%s%n",
                MINUS,
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition()),
                numberWithPosition.getNumber(),
                storage.getDivider());

        numberWithPosition = iterator.next();
        int spacesAmount = getPrintedLength(storage.getDividend()) -
                getPrintedLength(numberWithPosition.getNumber()) -
                numberWithPosition.getPosition();
        int dashAmount = Math.max(getPrintedLength(storage.getDivider()),
                getPrintedLength(storage.getResult()));
        String line2 = String.format("%s%s%s|%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                numberWithPosition.getNumber(),
                repeatCharSomeTimes(SPACE, spacesAmount),
                repeatCharSomeTimes(DASH, dashAmount));
        String line3 = String.format("%s%s%s|%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                repeatCharSomeTimes(DASH, getPrintedLength(numberWithPosition.getNumber())),
                repeatCharSomeTimes(SPACE, spacesAmount),
                storage.getResult());
        return line1 + line2 + line3;
    }
}
