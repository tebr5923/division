package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;
import com.foxminded.storage.Storage;

import java.util.Iterator;

public class FirstLinePrinter extends LinePrinter {
    private final Storage<?> storage;

   /* FirstLinePrinter(Print print) {
        super(print);
    }*/

    FirstLinePrinter(Print print, Storage<?> storage) {
        super(print);
        this.storage = storage;
    }

    @Override
    void printLine(Iterator<NumberWithPosition> iterator) {
        NumberWithPosition numberWithPosition = iterator.next();
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

    /*@Override
    void printLine(NumberWithPosition numberWithPosition) {
        print.setLinePrinter(new SecondLineAndDelimiterPrinter(print));
        System.out.printf("%s%s%s|%s%n",
                MINUS,
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition()),
                numberWithPosition.getNumber(),
                print.getStorage().getDivider());
    }*/

}
