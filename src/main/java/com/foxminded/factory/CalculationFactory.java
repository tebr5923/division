package com.foxminded.factory;

import com.foxminded.divider.Divider;
import com.foxminded.printer.Printer;
import com.foxminded.storage.Storage;

public interface CalculationFactory {
    Divider getDivider();
    //Storage getStorage();
    Printer getPrinter();
}
