package com.foxminded.printer;

import com.foxminded.storage.Storage;

public interface Printer {
    void print(Storage<?> storage);
}
