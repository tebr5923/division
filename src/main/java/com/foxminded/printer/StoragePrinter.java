package com.foxminded.printer;

import com.foxminded.storage.Storage;

public interface StoragePrinter<T extends Storage<?>> {
    void print(T storage);
}
