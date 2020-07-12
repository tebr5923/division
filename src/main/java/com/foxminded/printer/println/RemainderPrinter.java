package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;

public class RemainderPrinter extends LinePrinter {
    @Override
    void printLine(NumberWithPosition numberWithPosition) {
        System.out.printf("%s%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                numberWithPosition.getNumber());
    }
}
