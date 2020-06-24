package com.foxminded.printer;

import com.foxminded.storage.Representation;
import com.foxminded.storage.Storage;

import java.util.Arrays;
import java.util.List;

public class ConsolePrinter implements Printer {
    @Override
    public void print(Storage storage) {
        List<Representation> representations = storage.getRepresentations();
        int i = 0;
        char minus = '_';
        char dash = '-';

        printHeader(storage);
        representations.remove(0);
        representations.remove(0);
        for (Representation representation : representations) {
            i++;
            if (i == representations.size()) {
                System.out.printf("%s%s%n",
                        repeatCharSomeTimes(' ', representation.getPosition() + 1),
                        representation.getNumber());
            } else if (i % 2 != 0) {
                System.out.printf("%s%s%s%n",
                        repeatCharSomeTimes(' ', representation.getPosition()),
                        minus,
                        representation.getNumber());
            } else {
                System.out.printf("%s%s%n",
                        repeatCharSomeTimes(' ', representation.getPosition() + 1),
                        representation.getNumber());
                System.out.printf("%s%s%n",
                        repeatCharSomeTimes(' ', representation.getPosition() + 1),
                        repeatCharSomeTimes(dash, lengthInt(representation.getNumber())));
            }
        }
    }

    private void printHeader(Storage storage) {
        List<Representation> representations = storage.getRepresentations();
        char minus = '_';
        char dash = '-';

        System.out.printf("%s%s%s|%s%n",
                minus,
                repeatCharSomeTimes(' ', representations.get(0).getPosition()),
                representations.get(0).getNumber(),
                storage.getDivider());
        int spacesAmount = lengthInt(storage.getDividend()) -
                lengthInt(representations.get(1).getNumber()) -
                representations.get(1).getPosition();
        int minusAmount = Math.max(lengthInt(storage.getDivider()),
                lengthInt(storage.getResult()));
        System.out.printf("%s%s%s|%s%n",
                repeatCharSomeTimes(' ', representations.get(1).getPosition() + 1),
                representations.get(1).getNumber(),
                repeatCharSomeTimes(' ', spacesAmount),
                repeatCharSomeTimes(dash, minusAmount));
        System.out.printf("%s%s%s|%s%n",
                repeatCharSomeTimes(' ', representations.get(1).getPosition() + 1),
                repeatCharSomeTimes(dash, lengthInt(representations.get(1).getNumber())),
                repeatCharSomeTimes(' ', spacesAmount),
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
