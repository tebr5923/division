package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;

import java.util.Iterator;

class ShortDividendPrinter extends LinePrinter {
    ShortDividendPrinter(Print print) {
        super(print);
    }

    @Override
    void printLine(Iterator<NumberWithPosition> iterator) {
        NumberWithPosition numberWithPosition = iterator.next();
        if (iterator.hasNext()) {
            System.out.printf("%s%s%s%n",
                    repeatCharSomeTimes(SPACE, numberWithPosition.getPosition()),
                    MINUS,
                    numberWithPosition.getNumber());
            print.setLinePrinter(new MultiplicationResultAndDelimiterPrinter(print));
        } else {
            print.setLinePrinter(new RemainderPrinter(print));
        }
    }

  /*  @Override
    void printLine(NumberWithPosition numberWithPosition) {
        print.setLinePrinter(new MultiplicationResultAndDelimiterPrinter(print));
        System.out.printf("%s%s%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition()),
                MINUS,
                numberWithPosition.getNumber());
    }*/
}
