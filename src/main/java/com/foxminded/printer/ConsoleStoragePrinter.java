package com.foxminded.printer;

import com.foxminded.printer.println.Print;
import com.foxminded.storage.NumberWithPosition;
import com.foxminded.storage.Storage;

import java.util.ListIterator;

public class ConsoleStoragePrinter implements StoragePrinter<Storage<?>> {
    @Override
    public void print(Storage<?> storage) {
        Print print = new Print(storage);
        ListIterator<? extends NumberWithPosition<?>> iterator = storage.getRepresentations().listIterator();
        while (iterator.hasNext()) {
            print.printLine(iterator);
        }
    }
}
