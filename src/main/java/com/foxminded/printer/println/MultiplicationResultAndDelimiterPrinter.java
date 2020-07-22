package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;

import java.util.ListIterator;

class MultiplicationResultAndDelimiterPrinter extends LinePrinter {
    MultiplicationResultAndDelimiterPrinter(Print print) {
        super(print);
    }

    @Override
    void printLine(ListIterator<NumberWithPosition> iterator) {
        NumberWithPosition numberWithPosition = iterator.next();
        System.out.printf("%s%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                numberWithPosition.getNumber());
        System.out.printf("%s%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                repeatCharSomeTimes(DASH, getPrintedLength(numberWithPosition.getNumber())));
        print.setLinePrinter(new ShortDividendPrinter(print));
    }
}
