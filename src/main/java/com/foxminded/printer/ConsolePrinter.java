package com.foxminded.printer;

import com.foxminded.storage.Storage;

public class ConsolePrinter implements Printer {
    public void print(Storage storage) {
        storage.getRepresentation().forEach(System.out::println);
    }
}
