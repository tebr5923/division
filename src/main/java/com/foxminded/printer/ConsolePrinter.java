package com.foxminded.printer;

import com.foxminded.storage.Representation;
import com.foxminded.storage.Storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsolePrinter implements Printer {
    private List<String> stringList = new ArrayList<>();

    @Override
    public void print(Storage storage) {
        if (stringList.isEmpty()) {
            generateStingList(storage);
        }
        stringList.forEach(System.out::println);
    }

    private void generateStingList(Storage storage) {
        List<Representation> representations = storage.getRepresentations();
        int i = 0;
        String minus = "_";

        for (Representation representation : representations) {
            i++;
            if (i == 1) {
                stringList.add(new StringBuilder()
                        .append(minus)
                        .append(repeatCharSomeTimes(' ', representation.getPosition()))
                        .append(representation.getNumber())
                        .append('|')
                        .append(storage.getDivider())
                        .toString());
            } else if (i == 2) {
                int timesForSpace = lengthInt(storage.getDividend()) -
                        lengthInt(representation.getNumber()) -
                        representation.getPosition(); //pos of 1st line == 0
                int timesForMinus = Math.max(lengthInt(storage.getDivider()),
                        lengthInt(storage.getResult()));
                stringList.add(new StringBuilder()
                        .append(repeatCharSomeTimes(' ', representation.getPosition() + 1))
                        .append(representation.getNumber())
                        .append(repeatCharSomeTimes(' ', timesForSpace))
                        .append('|')
                        .append(repeatCharSomeTimes('-', timesForMinus))
                        .toString());
                stringList.add(new StringBuilder()
                        .append(repeatCharSomeTimes(' ', representation.getPosition() + 1))
                        .append(repeatCharSomeTimes('-', lengthInt(representation.getNumber())))
                        .append(repeatCharSomeTimes(' ', timesForSpace))
                        .append('|')
                        .append(storage.getResult())
                        .toString());
            } else if (i == representations.size()) {
                stringList.add(new StringBuilder()
                        .append(repeatCharSomeTimes(' ', representation.getPosition() + 1))
                        .append(representation.getNumber())
                        .toString());
            } else if (i % 2 != 0) {
                stringList.add(new StringBuilder()
                        .append(repeatCharSomeTimes(' ', representation.getPosition()))
                        .append(minus)
                        .append(representation.getNumber())
                        .toString());
            } else if (i % 2 == 0) {
                stringList.add(new StringBuilder()
                        .append(repeatCharSomeTimes(' ', representation.getPosition() + 1))
                        .append(representation.getNumber())
                        .toString());
                stringList.add(new StringBuilder()
                        .append(repeatCharSomeTimes(' ', representation.getPosition() + 1))
                        .append(repeatCharSomeTimes('-', lengthInt(representation.getNumber())))
                        .toString());
            }
        }
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
