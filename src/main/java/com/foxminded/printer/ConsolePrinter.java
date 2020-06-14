package com.foxminded.printer;

import com.foxminded.storage.StepRepresentation;
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
        List<StepRepresentation> stepRepresentations = storage.getStepRepresentations();
        int i = 0;
        String minus = "_";

        for (StepRepresentation stepRepresentation : stepRepresentations) {
            i++;
            if (i == 1) {
                stringList.add(String.format("%s%s%s%s%s",
                        minus,
                        repeatCharSomeTimes(' ', stepRepresentation.getPosition()),
                        stepRepresentation.getNumber(),
                        '|',
                        storage.getDivider()
                        )
                );
            } else if (i == 2) {
                int timesForSpace = lengthInt(storage.getDividend()) -
                        lengthInt(stepRepresentation.getNumber()) -
                        stepRepresentation.getPosition(); //pos of 1st line == 0
                int timesForMinus = Math.max(lengthInt(storage.getDivider()),
                        lengthInt(storage.getResult()));
                stringList.add(String.format("%s%s%s%s%s",
                        repeatCharSomeTimes(' ', stepRepresentation.getPosition() + 1),
                        stepRepresentation.getNumber(),
                        repeatCharSomeTimes(' ', timesForSpace),
                        '|',
                        repeatCharSomeTimes('-', timesForMinus)
                        )
                );
                stringList.add(String.format("%s%s%s%s%s",
                        repeatCharSomeTimes(' ', stepRepresentation.getPosition() + 1),
                        repeatCharSomeTimes('-', lengthInt(stepRepresentation.getNumber())),
                        repeatCharSomeTimes(' ', timesForSpace),
                        '|',
                        storage.getResult()
                        )
                );
            } else if (i == stepRepresentations.size()) {
                stringList.add(String.format("%s%s",
                        repeatCharSomeTimes(' ', stepRepresentation.getPosition() + 1),
                        stepRepresentation.getNumber()
                        )
                );
            } else if (i % 2 != 0) {
                stringList.add(String.format("%s%s%s",
                        repeatCharSomeTimes(' ', stepRepresentation.getPosition()),
                        minus,
                        stepRepresentation.getNumber()
                        )
                );
            } else if (i % 2 == 0) {
                stringList.add(String.format("%s%s",
                        repeatCharSomeTimes(' ', stepRepresentation.getPosition() + 1),
                        stepRepresentation.getNumber()
                        )
                );
                stringList.add(String.format("%s%s",
                        repeatCharSomeTimes(' ', stepRepresentation.getPosition() + 1),
                        repeatCharSomeTimes('-', lengthInt(stepRepresentation.getNumber()))
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
