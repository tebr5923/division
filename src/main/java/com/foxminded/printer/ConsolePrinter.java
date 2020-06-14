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
                stringList.add(String.format("%s%s%s%s%s",
                        minus,
                        repeatCharSomeTimes(' ', representation.getPosition()),
                        representation.getNumber(),
                        '|',
                        storage.getDivider()
                        )
                );
            } else if (i == 2) {
                int timesForSpace = lengthInt(storage.getDividend()) -
                        lengthInt(representation.getNumber()) -
                        representation.getPosition(); //pos of 1st line == 0
                int timesForMinus = Math.max(lengthInt(storage.getDivider()),
                        lengthInt(storage.getResult()));
                stringList.add(String.format("%s%s%s%s%s",
                        repeatCharSomeTimes(' ', representation.getPosition() + 1),
                        representation.getNumber(),
                        repeatCharSomeTimes(' ', timesForSpace),
                        '|',
                        repeatCharSomeTimes('-', timesForMinus)
                        )
                );
                stringList.add(String.format("%s%s%s%s%s",
                        repeatCharSomeTimes(' ', representation.getPosition() + 1),
                        repeatCharSomeTimes('-', lengthInt(representation.getNumber())),
                        repeatCharSomeTimes(' ', timesForSpace),
                        '|',
                        storage.getResult()
                        )
                );
            } else if (i == representations.size()) {
                stringList.add(String.format("%s%s",
                        repeatCharSomeTimes(' ', representation.getPosition() + 1),
                        representation.getNumber()
                        )
                );
            } else if (i % 2 != 0) {
                stringList.add(String.format("%s%s%s",
                        repeatCharSomeTimes(' ', representation.getPosition()),
                        minus,
                        representation.getNumber()
                        )
                );
            } else if (i % 2 == 0) {
                stringList.add(String.format("%s%s",
                        repeatCharSomeTimes(' ', representation.getPosition() + 1),
                        representation.getNumber()
                        )
                );
                stringList.add(String.format("%s%s",
                        repeatCharSomeTimes(' ', representation.getPosition() + 1),
                        repeatCharSomeTimes('-', lengthInt(representation.getNumber()))
                        )
                );
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
