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
        List<String> stringList;
        List<Representation> representations = storage.getRepresentations();
        int i = 0;
        char minus = '_';
        char dash = '-';

        stringList = generateHeader(storage);
        representations.remove(0);
        representations.remove(0);
        for (Representation representation : representations) {
            i++;
            if (i == representations.size()) {
                stringList.add(String.format("%s%s",
                        repeatCharSomeTimes(' ', representation.getPosition() + 1),
                        representation.getNumber()));
            } else if (i % 2 != 0) {
                stringList.add(String.format("%s%s%s",
                        repeatCharSomeTimes(' ', representation.getPosition()),
                        minus,
                        representation.getNumber()));
            } else {
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

    private List<String> generateHeader(Storage storage) {
        List<Representation> representations = storage.getRepresentations();
        List<String> headerList = new ArrayList<>();
        char minus = '_';
        char dash = '-';

        headerList.add(String.format("%s%s%s|%s",
                minus,
                repeatCharSomeTimes(' ', representations.get(0).getPosition()),
                representations.get(0).getNumber(),
                storage.getDivider()));
        int spacesAmount = lengthInt(storage.getDividend()) -
                lengthInt(representations.get(1).getNumber()) -
                representations.get(1).getPosition();
        int minusAmount = Math.max(lengthInt(storage.getDivider()),
                lengthInt(storage.getResult()));
        headerList.add(String.format("%s%s%s|%s",
                repeatCharSomeTimes(' ', representations.get(1).getPosition() + 1),
                representations.get(1).getNumber(),
                repeatCharSomeTimes(' ', spacesAmount),
                repeatCharSomeTimes(dash, minusAmount)));
        headerList.add(String.format("%s%s%s|%s",
                repeatCharSomeTimes(' ', representations.get(1).getPosition() + 1),
                repeatCharSomeTimes(dash, lengthInt(representations.get(1).getNumber())),
                repeatCharSomeTimes(' ', spacesAmount),
                storage.getResult()));
        return headerList;
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
