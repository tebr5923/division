package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;

class RemainderPrinter extends LinePrinter {
    RemainderPrinter(Print print) {
        super(print);
    }

    @Override
    void printLine(NumberWithPosition numberWithPosition) {
        System.out.printf("%s%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                numberWithPosition.getNumber());
    }
}
