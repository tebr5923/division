package com.foxminded.printer;

import com.foxminded.printer.println.Print;
import com.foxminded.printer.println.RemainderPrinter;
import com.foxminded.storage.NumberWithPosition;
import com.foxminded.storage.Storage;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ConsoleStoragePrinter implements StoragePrinter<Storage<?>> {
    private static final char MINUS = '_';
    private static final char DASH = '-';
    private static final char SPACE = ' ';

    @Override
    public void print(Storage<?> storage) {
        Print print = new Print(storage);
        Iterator<NumberWithPosition> iterator = storage.getRepresentations().iterator();
        while (iterator.hasNext()) {
            NumberWithPosition numberWithPosition = iterator.next();
            if (iterator.hasNext()) {
                print.printLine(numberWithPosition);
                print.changePrinter();

               // printShortDividend(numberWithPosition);
               // printMultiplicationResultAndDelimiter(iterator.next());
            } else {
                //printRemainder(numberWithPosition);
                print.setLinePrinter(new RemainderPrinter());
                print.printLine(numberWithPosition);
            }
        }


        /*printHeader(storage);

        List<NumberWithPosition> representations;
        representations = storage.getRepresentations().subList(2, storage.getRepresentations().size());
        Iterator<NumberWithPosition> iterator = representations.iterator();
        while (iterator.hasNext()) {
            NumberWithPosition print = iterator.next();
            if (iterator.hasNext()) {
                printShortDividend(print);
                printMultiplicationResultAndDelimiter(iterator.next());
            } else {
                printRemainder(print);
            }
        }*/
    }

    private void printHeader(Storage<?> storage) {
        List<NumberWithPosition> representations = storage.getRepresentations();

        System.out.printf("%s%s%s|%s%n",
                MINUS,
                repeatCharSomeTimes(SPACE, representations.get(0).getPosition()),
                representations.get(0).getNumber(),
                storage.getDivider());
        int spacesAmount = getPrintedLength(storage.getDividend()) -
                getPrintedLength(representations.get(1).getNumber()) -
                representations.get(1).getPosition();
        int dashAmount = Math.max(getPrintedLength(storage.getDivider()),
                getPrintedLength(storage.getResult()));
        System.out.printf("%s%s%s|%s%n",
                repeatCharSomeTimes(SPACE, representations.get(1).getPosition() + 1),
                representations.get(1).getNumber(),
                repeatCharSomeTimes(SPACE, spacesAmount),
                repeatCharSomeTimes(DASH, dashAmount));
        System.out.printf("%s%s%s|%s%n",
                repeatCharSomeTimes(SPACE, representations.get(1).getPosition() + 1),
                repeatCharSomeTimes(DASH, getPrintedLength(representations.get(1).getNumber())),
                repeatCharSomeTimes(SPACE, spacesAmount),
                storage.getResult());
    }

    private void printShortDividend(NumberWithPosition representation) {
        System.out.printf("%s%s%s%n",
                repeatCharSomeTimes(SPACE, representation.getPosition()),
                MINUS,
                representation.getNumber());
    }

    private void printMultiplicationResultAndDelimiter(NumberWithPosition representation) {
        System.out.printf("%s%s%n",
                repeatCharSomeTimes(SPACE, representation.getPosition() + 1),
                representation.getNumber());
        System.out.printf("%s%s%n",
                repeatCharSomeTimes(SPACE, representation.getPosition() + 1),
                repeatCharSomeTimes(DASH, getPrintedLength(representation.getNumber())));
    }

    private void printRemainder(NumberWithPosition representation) {
        System.out.printf("%s%s%n",
                repeatCharSomeTimes(SPACE, representation.getPosition() + 1),
                representation.getNumber());
    }

    private int getPrintedLength(Object object) {
        return String.valueOf(object).length();
    }

    private String repeatCharSomeTimes(char ch, int times) {
        char[] chars = new char[times];
        Arrays.fill(chars, ch);
        return new String(chars);
    }
}
