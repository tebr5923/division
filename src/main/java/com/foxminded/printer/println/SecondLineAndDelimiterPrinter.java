package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;
import com.foxminded.storage.Storage;

public class SecondLineAndDelimiterPrinter extends LinePrinter{
    private final Storage<?> storage;

    public SecondLineAndDelimiterPrinter(Storage<?> storage) {
        this.storage = storage;
    }

    @Override
    void printLine(NumberWithPosition numberWithPosition) {

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
    }
}
