package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;

import java.util.Iterator;

class RemainderPrinter extends LinePrinter {
    RemainderPrinter(Print print) {
        super(print);
    }

    @Override
    void printLine(Iterator<NumberWithPosition> iterator) {
        NumberWithPosition numberWithPosition = iterator.next();
        System.out.printf("%s%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                numberWithPosition.getNumber());
    }

   /* @Override
    void printLine(NumberWithPosition numberWithPosition) {
        System.out.printf("%s%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                numberWithPosition.getNumber());
    }*/
}
