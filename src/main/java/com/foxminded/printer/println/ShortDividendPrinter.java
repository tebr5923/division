package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;

import java.util.ListIterator;

class ShortDividendPrinter extends LinePrinter {
    ShortDividendPrinter(Print print) {
        super(print);
    }

    @Override
    void printLine(ListIterator<NumberWithPosition> iterator) {
        NumberWithPosition numberWithPosition = iterator.next();
        if (iterator.hasNext()) {
            System.out.printf("%s%s%s%n",
                    repeatCharSomeTimes(SPACE, numberWithPosition.getPosition()),
                    MINUS,
                    numberWithPosition.getNumber());
            print.setLinePrinter(new MultiplicationResultAndDelimiterPrinter(print));
        } else {
            iterator.previous();
            print.setLinePrinter(new RemainderPrinter(print));
        }
    }
}
