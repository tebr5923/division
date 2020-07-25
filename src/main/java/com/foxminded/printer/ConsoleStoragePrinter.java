package com.foxminded.printer;

import com.foxminded.printer.println.Print;
import com.foxminded.storage.Storage;

public class ConsoleStoragePrinter implements StoragePrinter<Storage<?>> {
    @Override
    public void print(Storage<?> storage) {
        Print print = new Print(storage);
        print.print();
    }
}
