package com.foxminded.printer;

import com.foxminded.storage.Storage;

public interface Printer<T extends Storage<?>> {
    void print(T storage);
}
