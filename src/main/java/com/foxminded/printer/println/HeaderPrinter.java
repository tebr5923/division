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

    @Override
    protected String formatLine(ListIterator<? extends NumberWithPosition<?>> iterator) {
        NumberWithPosition<?> numberWithPosition = iterator.next();
        StringBuilder stringBuilder = new StringBuilder(getFirstLine(numberWithPosition));
        numberWithPosition = iterator.next();
        stringBuilder.append(getSecondLine(numberWithPosition))
        .append(getThirdLine(numberWithPosition));
        return stringBuilder.toString();
    }

    @SuppressWarnings("squid:S1192") // reused format String
    private String getFirstLine(NumberWithPosition<?> numberWithPosition) {
        return String.format("%s%s%s|%s%n",
                MINUS,
                repeatChar(SPACE, numberWithPosition.getPosition()),
                numberWithPosition.getValue(),
                storage.getDivider());
    }

    private String getSecondLine(NumberWithPosition<?> numberWithPosition) {
        int spacesAmount = getPrintedLength(storage.getDividend()) -
                getPrintedLength(numberWithPosition.getValue()) -
                numberWithPosition.getPosition();
        int dashAmount = Math.max(getPrintedLength(storage.getDivider()),
                getPrintedLength(storage.getResult()));
        return String.format("%s%s%s|%s%n",
                repeatChar(SPACE, numberWithPosition.getPosition() + 1),
                numberWithPosition.getValue(),
                repeatChar(SPACE, spacesAmount),
                repeatChar(DASH, dashAmount));
    }

    private String getThirdLine(NumberWithPosition<?> numberWithPosition) {
        int spacesAmount = getPrintedLength(storage.getDividend()) -
                getPrintedLength(numberWithPosition.getValue()) -
                numberWithPosition.getPosition();
        return String.format("%s%s%s|%s%n",
                repeatChar(SPACE, numberWithPosition.getPosition() + 1),
                repeatChar(DASH, getPrintedLength(numberWithPosition.getValue())),
                repeatChar(SPACE, spacesAmount),
                storage.getResult());
    }
}
