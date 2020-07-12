package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;
import com.foxminded.storage.Storage;

public class FirstLinePrinter extends LinePrinter{
    private final Storage<?> storage;

    public FirstLinePrinter(Storage<?> storage) {
        this.storage = storage;
    }
    
    @Override
    public void printLine(NumberWithPosition numberWithPosition) {
        System.out.printf("%s%s%s|%s%n",
                MINUS,
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition()),
                numberWithPosition.getNumber(),
                storage.getDivider());
    }
}
