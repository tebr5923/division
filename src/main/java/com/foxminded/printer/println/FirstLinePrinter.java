package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;
import com.foxminded.storage.Storage;

public class FirstLinePrinter extends LinePrinter{

    FirstLinePrinter(Print print) {
        super(print);
    }

    @Override
    void printLine(NumberWithPosition numberWithPosition) {
        print.setLinePrinter(new SecondLineAndDelimiterPrinter(print));
        System.out.printf("%s%s%s|%s%n",
                MINUS,
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition()),
                numberWithPosition.getNumber(),
                print.getStorage().getDivider());
    }
}
