package com.foxminded.printer;

import com.foxminded.printer.println.Print;
import com.foxminded.printer.println.RemainderPrinter;
import com.foxminded.storage.NumberWithPosition;
import com.foxminded.storage.Storage;

import java.util.Iterator;

public class ConsoleStoragePrinter implements StoragePrinter<Storage<?>> {
    @Override
    public void print(Storage<?> storage) {
        Print print = new Print(storage);
        Iterator<NumberWithPosition> iterator = storage.getRepresentations().iterator();
        while (iterator.hasNext()) {
            NumberWithPosition numberWithPosition = iterator.next();
            if (iterator.hasNext()) {
                print.printLine(numberWithPosition);
                print.changePrinter();
            } else {
                print.setLinePrinter(new RemainderPrinter());
                print.printLine(numberWithPosition);
            }
        }
    }
}
