package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;

class MultiplicationResultAndDelimiterPrinter extends LinePrinter {
    MultiplicationResultAndDelimiterPrinter(Print print) {
        super(print);
    }

    @Override
    void printLine(NumberWithPosition numberWithPosition) {
        int lastMultPos = print.getStorage().getRepresentations().size() - 2;
        if (numberWithPosition == print.getStorage().getRepresentations().get(lastMultPos)) {
            print.setLinePrinter(new RemainderPrinter(print));
        } else {
            print.setLinePrinter(new ShortDividendPrinter(print));
        }
        System.out.printf("%s%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                numberWithPosition.getNumber());
        System.out.printf("%s%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                repeatCharSomeTimes(DASH, getPrintedLength(numberWithPosition.getNumber())));
    }
}
