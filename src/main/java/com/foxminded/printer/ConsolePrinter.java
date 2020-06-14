package com.foxminded.printer;

import com.foxminded.storage.Representation;
import com.foxminded.storage.Storage;

import java.util.ArrayList;
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
                stringList.add(minus +
                        printStringSomeTimes(" ", representation.getPosition()) +
                        representation.getNumber() +
                        "|" +
                        storage.getDivider());
            } else if (i == 2) {
                int timesForSpace = lengthInt(storage.getDividend()) -
                        lengthInt(representation.getNumber()) -
                        representation.getPosition(); //pos of 1st line == 0
                int timesForMinus = Math.max(lengthInt(storage.getDivider()),
                        lengthInt(storage.getResult()));
                stringList.add(printStringSomeTimes(" ", representation.getPosition() + 1) +
                        representation.getNumber() +
                        printStringSomeTimes(" ", timesForSpace) +
                        "|" +
                        printStringSomeTimes("-", timesForMinus));
                stringList.add(printStringSomeTimes(" ", representation.getPosition() + 1) +
                        printStringSomeTimes("-", lengthInt(representation.getNumber())) +
                        printStringSomeTimes(" ", timesForSpace) +
                        "|" +
                        storage.getResult());
            } else if (i == representations.size()) {
                stringList.add(printStringSomeTimes(" ", representation.getPosition() + 1) +
                        representation.getNumber());
            } else if (i % 2 != 0) {
                stringList.add(printStringSomeTimes(" ", representation.getPosition()) +
                        minus +
                        representation.getNumber());
            } else if (i % 2 == 0) {
                stringList.add(printStringSomeTimes(" ", representation.getPosition() + 1) +
                        representation.getNumber());
                stringList.add(printStringSomeTimes(" ", representation.getPosition() + 1) +
                        printStringSomeTimes("-", lengthInt(representation.getNumber())));
            }
        }
    }

    private int lengthInt(int integer) {
        return Integer.toString(integer).length();
    }

    private String printStringSomeTimes(String string, int times) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < times; i++) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }
}
