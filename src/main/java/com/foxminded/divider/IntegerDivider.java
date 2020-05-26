package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;

import java.util.ArrayList;
import java.util.List;

public class IntegerDivider implements Divider {
    public IntegerStorage divide(Integer divided, Integer divider) {
        int mod = divided % divider;
        int result = divided / divider;

        List<String> representation = generateColumn(divided, divider);
        return new IntegerStorage(divided, divider, mod, result, representation);
    }

    private List<String> generateColumn(Integer divided, Integer divider) {
        int maimMod = divided % divider;
        int mainResult = divided / divider;
        List<String> columnStr = new ArrayList<>();
        String minus = "_";

        String firstLine;
        firstLine = minus + divided + "|" + divider;
        columnStr.add(firstLine);

        //----------string 2-----------------
        int intermediateDivided = findIntermediateDivided(divided, divider);
        int intermediateMod = intermediateDivided % divider;

        int multiplication = getMultiplication(intermediateDivided, divider);

        int dividedLength = lengthInt(divided);
        int timesForSpace = dividedLength - lengthInt(multiplication);
        int timesForMinus;
        if (mainResult >= divider) {
            timesForMinus = lengthInt(mainResult);
        } else {
            timesForMinus = lengthInt(divider);
        }

        int count = 1;
        int kForMultLeftSpaces = 0;
        int kForMultRightSpaces = 0;
        if (lengthInt(intermediateDivided) > lengthInt(multiplication)) {
            kForMultLeftSpaces++;
            kForMultRightSpaces++;
        }
        String secondLine;
        secondLine = printStringSomeTimes(" ", count + kForMultLeftSpaces) +
                multiplication +
                printStringSomeTimes(" ", timesForSpace - kForMultRightSpaces) +
                "|" + printStringSomeTimes("-", timesForMinus);
        columnStr.add(secondLine);

        //----------string 3-----------------
        timesForMinus = lengthInt(multiplication);
        String thirdLine;
        thirdLine = printStringSomeTimes(" ", count + kForMultLeftSpaces) +
                printStringSomeTimes("-", timesForMinus) +
                printStringSomeTimes(" ", timesForSpace - kForMultRightSpaces) +
                "|" + mainResult;
        columnStr.add(thirdLine);

//if 4th string is last
        int intermediateDividedLength = lengthInt(intermediateDivided);
        //      count = intermediateDividedLength - lengthInt(multiplication);
        if (intermediateDividedLength == dividedLength) {
            String fourthLine =
                    printStringSomeTimes(" ",
                            count + kForMultLeftSpaces + timesForMinus - lengthInt(maimMod))
                            + maimMod;
            columnStr.add(fourthLine);
            return columnStr;
        }

        //main loop
        int nextMainDivided = nextMainDivided(divided, intermediateMod, intermediateDividedLength);
        //while (count < dividedLength-5) {
        while (true) {
            count = count + kForMultLeftSpaces;
            int nextIntermediateDivided = findIntermediateDivided(nextMainDivided, divider);
            // count = count + lengthInt(intermediateDivided) - lengthInt(intermediateMod);
            count = count + lengthInt(multiplication) - lengthInt(intermediateMod);
            if (intermediateMod == 0) {
                count++;
                //если списывается вниз 0 то добавляем 1 пробел
                char[] arrayOfDivided = divided.toString().toCharArray();
                //int i = intermediateDividedLength;
                int i = count - 1;
                while (arrayOfDivided[i] == '0') {
                    count++;
                    i++;
                    if (count > arrayOfDivided.length) {
                        count--;
                        break;
                    }
                }
            }
            if (nextIntermediateDivided >= divider) {
                String firstLineInLoop = printStringSomeTimes(" ", count - 1) +
                        minus +
                        nextIntermediateDivided;
                columnStr.add(firstLineInLoop);
            } else {
                String firstLineInLoop = printStringSomeTimes(" ", count) +
                        maimMod;
                columnStr.add(firstLineInLoop);
                return columnStr;
            }
//----------string 2 in loop-----------------
            String secondLineInLoop;
            int multiplicationInLoop = getMultiplication(nextIntermediateDivided, divider);
            int nextIntermediateMod = nextIntermediateDivided % divider; // need to delete
            kForMultLeftSpaces = 0;
            if (lengthInt(nextIntermediateDivided) > lengthInt(multiplicationInLoop)) {
                //if (lengthInt(nextIntermediateDivided) > lengthInt(multiplication)) {
                kForMultLeftSpaces++;
            }
            secondLineInLoop = printStringSomeTimes(" ", count + kForMultLeftSpaces) +
                    multiplicationInLoop;
            columnStr.add(secondLineInLoop);

//----------string 3 in loop-----------------
            String thirdLineInLoop;
            thirdLineInLoop = printStringSomeTimes(" ", count + kForMultLeftSpaces) +
                    printStringSomeTimes("-", lengthInt(multiplicationInLoop));

            columnStr.add(thirdLineInLoop);
//----------string 4 in loop-----------------
            //if 4th string is last
            //      count = intermediateDividedLength - lengthInt(multiplication);
            if ((count + lengthInt(nextIntermediateDivided)) > lengthInt(divided)) {
                String fourthLineInLoop =
                        printStringSomeTimes(" ",
                                count + kForMultLeftSpaces + lengthInt(multiplicationInLoop) - lengthInt(maimMod))
                                + maimMod;
                columnStr.add(fourthLineInLoop);
                return columnStr;
            }
            //инит для следующей петли

            nextMainDivided = nextMainDivided(nextMainDivided,
                    nextIntermediateMod,
                    lengthInt(nextIntermediateDivided));

            // nextMainDivided = nextMainDivided(divided, intermediateMod, intermediateDividedLength);

            intermediateDivided = nextIntermediateDivided;
            intermediateDividedLength = lengthInt(intermediateDivided);
            intermediateMod = nextIntermediateMod;
            multiplication = multiplicationInLoop;

            //count++;
        }
        //return columnStr;
    }

    private String printStringSomeTimes(String string, int times) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < times; i++) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    private int lengthInt(int integer) {
        return Integer.toString(integer).length();
    }

    private String cut(int integer, int toIndex) {
        StringBuilder sb = new StringBuilder(Integer.toString(integer));
        return sb.substring(toIndex + 1);
    }

    private int concatIntegerAndString(int left, String right) {
        String result = left + right;
        return Integer.parseInt(result);
    }

    private int nextMainDivided(int divided,
                                int intermediateMod,
                                int intermediateDividedLength) {
        return concatIntegerAndString(intermediateMod,
                cut(divided, intermediateDividedLength - 1));
    }

    private int findIntermediateDivided(int divided, int divider) {
        StringBuilder sbDivided = new StringBuilder(Integer.toString(divided));
        int intermediateDivided = Integer.parseInt(sbDivided.substring(0, 1));
        int i = 1;
        while (intermediateDivided < divider) {
            i++;
            if (i > sbDivided.length()) {
                return 0;
            }
            intermediateDivided = Integer.parseInt(sbDivided.substring(0, i));
        }
        return intermediateDivided;
    }

    private int getMultiplication(int intermediateDivided, int divider) {
        return intermediateDivided - intermediateDivided % divider;
    }
}
