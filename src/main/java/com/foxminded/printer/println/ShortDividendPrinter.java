package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;

class ShortDividendPrinter extends LinePrinter{
    ShortDividendPrinter(Print print) {
        super(print);
    }

    @Override
    void printLine(NumberWithPosition numberWithPosition) {
        print.setLinePrinter(new MultiplicationResultAndDelimiterPrinter(print));
        System.out.printf("%s%s%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition()),
                MINUS,
                numberWithPosition.getNumber());
    }
}
