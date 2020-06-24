package com.foxminded.printer;

import com.foxminded.storage.Representation;
import com.foxminded.storage.Storage;

import java.util.Arrays;
import java.util.List;

public class ConsolePrinter implements Printer {
    private static final char MINUS = '_';
    private static final char DASH = '-';
    private static final char SPACE = ' ';

    @Override
    public void print(Storage storage) {
        List<Representation> representations = storage.getRepresentations();
        int i = 0;

        printHeader(storage);
        representations = representations.subList(2, representations.size());
        for (Representation representation : representations) {
            i++;
            if (i == representations.size()) {
                System.out.printf("%s%s%n",
                        repeatCharSomeTimes(SPACE, representation.getPosition() + 1),
                        representation.getNumber());
            } else if (i % 2 != 0) {
                System.out.printf("%s%s%s%n",
                        repeatCharSomeTimes(SPACE, representation.getPosition()),
                        MINUS,
                        representation.getNumber());
            } else {
                System.out.printf("%s%s%n",
                        repeatCharSomeTimes(SPACE, representation.getPosition() + 1),
                        representation.getNumber());
                System.out.printf("%s%s%n",
                        repeatCharSomeTimes(SPACE, representation.getPosition() + 1),
                        repeatCharSomeTimes(DASH, lengthInt(representation.getNumber())));
            }
        }
    }

    private void printHeader(Storage storage) {
        List<Representation> representations = storage.getRepresentations();

        System.out.printf("%s%s%s|%s%n",
                MINUS,
                repeatCharSomeTimes(SPACE, representations.get(0).getPosition()),
                representations.get(0).getNumber(),
                storage.getDivider());
        int spacesAmount = lengthInt(storage.getDividend()) -
                lengthInt(representations.get(1).getNumber()) -
                representations.get(1).getPosition();
        int minusAmount = Math.max(lengthInt(storage.getDivider()),
                lengthInt(storage.getResult()));
        System.out.printf("%s%s%s|%s%n",
                repeatCharSomeTimes(SPACE, representations.get(1).getPosition() + 1),
                representations.get(1).getNumber(),
                repeatCharSomeTimes(SPACE, spacesAmount),
                repeatCharSomeTimes(DASH, minusAmount));
        System.out.printf("%s%s%s|%s%n",
                repeatCharSomeTimes(SPACE, representations.get(1).getPosition() + 1),
                repeatCharSomeTimes(DASH, lengthInt(representations.get(1).getNumber())),
                repeatCharSomeTimes(SPACE, spacesAmount),
                storage.getResult());
    }

    private int lengthInt(int integer) {
        return Integer.toString(integer).length();
    }

    private String repeatCharSomeTimes(char ch, int times) {
        char[] chars = new char[times];
        Arrays.fill(chars, ch);
        return new String(chars);
    }
}
