package com.foxminded.printer;

import com.foxminded.printer.println.Printer;
import com.foxminded.storage.Storage;

public class ConsoleStoragePrinter implements StoragePrinter<Storage<?>> {
    @Override
    public void print(Storage<?> storage) {
        Printer printer = new Printer(storage);
        printer.print();
    }
}
