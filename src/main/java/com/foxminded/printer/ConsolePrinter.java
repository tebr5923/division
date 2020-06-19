package com.foxminded.printer;

import com.foxminded.storage.Representation;
import com.foxminded.storage.Storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsolePrinter implements Printer {

    @Override
    public void print(Storage storage) {
        generateStingList(storage).forEach(System.out::println);
    }

    private List<String> generateStingList(Storage storage) {
        List<String> stringList = new ArrayList<>();
        List<Representation> representations = storage.getRepresentations();
        int i = 0;
        char minus = '_';
        char dash = '-';

        for (Representation representation : representations) {
            i++;
            if (i == 1) {
                stringList.add(String.format("%s%s%s|%s",
                        minus,
                        repeatCharSomeTimes(' ', representation.getPosition()),
                        representation.getNumber(),
                        storage.getDivider()));
            } else if (i == 2) {
                int spacesAmount = lengthInt(storage.getDividend()) -
                        lengthInt(representation.getNumber()) -
                        representation.getPosition();
                int minusAmount = Math.max(lengthInt(storage.getDivider()),
                        lengthInt(storage.getResult()));
                stringList.add(String.format("%s%s%s|%s",
                        repeatCharSomeTimes(' ', representation.getPosition() + 1),
                        representation.getNumber(),
                        repeatCharSomeTimes(' ', spacesAmount),
                        repeatCharSomeTimes(dash, minusAmount)));
                stringList.add(String.format("%s%s%s|%s",
                        repeatCharSomeTimes(' ', representation.getPosition() + 1),
                        repeatCharSomeTimes(dash, lengthInt(representation.getNumber())),
                        repeatCharSomeTimes(' ', spacesAmount),
                        storage.getResult()));
            } else if (i == representations.size()) {
                stringList.add(String.format("%s%s",
                        repeatCharSomeTimes(' ', representation.getPosition() + 1),
                        representation.getNumber()));
            } else if (i % 2 != 0) {
                stringList.add(String.format("%s%s%s",
                        repeatCharSomeTimes(' ', representation.getPosition()),
                        minus,
                        representation.getNumber()));
            } else if (i % 2 == 0) {
                stringList.add(String.format("%s%s",
                        repeatCharSomeTimes(' ', representation.getPosition() + 1),
                        representation.getNumber()));
                stringList.add(String.format("%s%s",
                        repeatCharSomeTimes(' ', representation.getPosition() + 1),
                        repeatCharSomeTimes(dash, lengthInt(representation.getNumber()))));
            }
        }
        return stringList;
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
