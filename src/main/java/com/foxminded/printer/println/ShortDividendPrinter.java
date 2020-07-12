package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;

public class ShortDividendPrinter extends LinePrinter{
    @Override
    void printLine(NumberWithPosition numberWithPosition) {
        System.out.printf("%s%s%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition()),
                MINUS,
                numberWithPosition.getNumber());
    }
}
