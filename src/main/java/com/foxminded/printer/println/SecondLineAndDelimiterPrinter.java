package com.foxminded.printer.println;

import com.foxminded.storage.NumberWithPosition;

class SecondLineAndDelimiterPrinter extends LinePrinter {

    SecondLineAndDelimiterPrinter(Print print) {
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
        int spacesAmount = getPrintedLength(print.getStorage().getDividend()) -
                getPrintedLength(numberWithPosition.getNumber()) -
                numberWithPosition.getPosition();
        int dashAmount = Math.max(getPrintedLength(print.getStorage().getDivider()),
                getPrintedLength(print.getStorage().getResult()));
        System.out.printf("%s%s%s|%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                numberWithPosition.getNumber(),
                repeatCharSomeTimes(SPACE, spacesAmount),
                repeatCharSomeTimes(DASH, dashAmount));
        System.out.printf("%s%s%s|%s%n",
                repeatCharSomeTimes(SPACE, numberWithPosition.getPosition() + 1),
                repeatCharSomeTimes(DASH, getPrintedLength(numberWithPosition.getNumber())),
                repeatCharSomeTimes(SPACE, spacesAmount),
                print.getStorage().getResult());
    }
}
