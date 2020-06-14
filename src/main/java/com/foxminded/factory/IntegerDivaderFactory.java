package com.foxminded.factory;

import com.foxminded.divider.Divider;
import com.foxminded.divider.IntegerDivider;
import com.foxminded.printer.ConsolePrinter;
import com.foxminded.printer.Printer;
import com.foxminded.storage.IntegerStorage;
import com.foxminded.storage.Storage;

public class IntegerDivaderFactory implements CalculationFactory {
    @Override
    public Divider getDivider() {
        return new IntegerDivider();
    }

/*
    @Override
    public Storage getStorage() {
        return new IntegerStorage();
    }
*/

    @Override
    public Printer getPrinter() {
        return new ConsolePrinter();
    }
}
