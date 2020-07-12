package com.foxminded.printer;

import com.foxminded.storage.NumberWithPosition;
import com.foxminded.storage.Storage;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ConsolePrinter implements Printer<Storage<?>> {
    private static final char MINUS = '_';
    private static final char DASH = '-';
    private static final char SPACE = ' ';

    @Override
    public void print(Storage<?> storage) {
        printHeader(storage);

        List<NumberWithPosition> representations = storage.getRepresentations();
        representations = representations.subList(2, representations.size());
        Iterator<NumberWithPosition> iterator = representations.iterator();
        while (iterator.hasNext()) {
            NumberWithPosition print = iterator.next();
            if (iterator.hasNext()) {
                printShortDividend(print);
                printMultiplicationResultAndDelimiter(iterator.next());
            } else {
                printRemainder(print);
            }
        }
    }

    private void printHeader(Storage<?> storage) {
        List<NumberWithPosition> representations = storage.getRepresentations();

        System.out.printf("%s%s%s|%s%n",
                MINUS,
                repeatCharSomeTimes(SPACE, representations.get(0).getPosition()),
                representations.get(0).getNumber(),
                storage.getDivider());
        int spacesAmount = getPrintedLength(storage.getDividend()) -
                lengthInt(representations.get(1).getNumber()) -
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
                repeatCharSomeTimes(DASH, lengthInt(representations.get(1).getNumber())),
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
                repeatCharSomeTimes(DASH, lengthInt(representation.getNumber())));
    }

    private void printRemainder(NumberWithPosition representation) {
        System.out.printf("%s%s%n",
                repeatCharSomeTimes(SPACE, representation.getPosition() + 1),
                representation.getNumber());
    }

    private int lengthInt(int integer) {
        return Integer.toString(integer).length();
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
