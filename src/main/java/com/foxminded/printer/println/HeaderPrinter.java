package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;
import com.foxminded.storage.Storage;

import java.util.ListIterator;

public class HeaderPrinter extends LinePrinter {
    private final Storage<?> storage;

    HeaderPrinter(Print print, Storage<?> storage) {
        super(print);
        this.storage = storage;
    }

    @Override
    void printLine(ListIterator<? extends NumberWithPosition<?>> iterator) {
        NumberWithPosition<?> numberWithPosition = iterator.next();
        System.out.printf("%s%s%s|%s%n",
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
        System.out.printf("%s%s%s|%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                numberWithPosition.getNumber(),
                repeatCharSomeTimes(SPACE, spacesAmount),
                repeatCharSomeTimes(DASH, dashAmount));
        System.out.printf("%s%s%s|%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                repeatCharSomeTimes(DASH, getPrintedLength(numberWithPosition.getNumber())),
                repeatCharSomeTimes(SPACE, spacesAmount),
                storage.getResult());

        print.setLinePrinter(new ShortDividendPrinter(print));
    }
}
