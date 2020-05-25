package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;

import java.util.ArrayList;
import java.util.List;

public class IntegerDivider implements Divider {
    public IntegerStorage divide(Integer divided, Integer divider) {
        int mod = divided % divider;
        int result = divided / divider;

        List<Integer> columnInt = new ArrayList<>();

        return new IntegerStorage(divided, divider, mod, result, columnInt);
    }

    private int findIntermediateDivided(int divided, int divider) {
        StringBuilder sbDivided = new StringBuilder(Integer.toString(divided));
        int intermediateDivided = Integer.parseInt(sbDivided.substring(0, 1));
        int i = 1;
        while (intermediateDivided < divider) {
            i++;
            intermediateDivided = Integer.parseInt(sbDivided.substring(0, i));
        }
        return intermediateDivided;
    }

    //-----------------------------------------------------
    public List<String> generateColumn(Integer divided, Integer divider) {
        int mod = divided % divider;
        int result = divided / divider;
        List<String> columnStr = new ArrayList<>();
        String minus = "_";
        String firstLine;

        firstLine = minus + divided + "|" + divider;
        columnStr.add(firstLine);

        int intermediateDivided = findIntermediateDivided(divided, divider);
        int intermediateMod = intermediateDivided % divider;
        int intForSecondLine = intermediateDivided - intermediateMod;
        int dividedLength = lengthInt(divided);
        int timesForSpace = dividedLength - lengthInt(intForSecondLine);
        int timesForMinus;
        if (result >= divider) {
            timesForMinus = lengthInt(result);
        } else {
            timesForMinus = lengthInt(divider);
        }

        String secondLine;
        secondLine = " " + intForSecondLine +
                printStringSomeTimes(" ", timesForSpace) +
                "|" + printStringSomeTimes("-", timesForMinus);
        columnStr.add(secondLine);

        timesForMinus = lengthInt(intForSecondLine);
        String thirdLine;
        thirdLine = " " + printStringSomeTimes("-", timesForMinus) +
                printStringSomeTimes(" ", timesForSpace) +
                "|" + result;
        columnStr.add(thirdLine);

//если 4я строка последняя
        int intermediateDividedLength = lengthInt(intermediateDivided);
        int intermediateModLength = lengthInt(intermediateMod);
        int countForMod = intermediateDividedLength - intermediateModLength;
        if (intermediateDividedLength == dividedLength) {
            String fourthLine = printStringSomeTimes(" ", countForMod + 1) + mod;
            columnStr.add(fourthLine);
            return columnStr;
        }


        //main loop
        int nextMainDivided = nextMainDivided(divided, intermediateMod, intermediateDividedLength);
        while (countForMod < dividedLength) {
            //надо прибавить слева остаток от деления
            //основной divided теперь nextMainDivided

            //int nextMainDivided = nextMainDivided(dividend, intermediateMod, intermediateDividendLength);

            if (intermediateMod == 0) {
                countForMod++;
            }
            int nextIntermediateDivided = findIntermediateDivided(nextMainDivided, divider);

//если списывается вниз 0 то добавляем 1 пробел
            char[] arrayOfDivided = divided.toString().toCharArray();
            int i = intermediateDividedLength;
            while (arrayOfDivided[i] == '0') {
                countForMod++;
                i++;
            }

            if (nextIntermediateDivided >= divider) {
                String firstLineInLoop = printStringSomeTimes(" ", countForMod) +
                        minus +
                        nextIntermediateDivided;
                columnStr.add(firstLineInLoop);
            } else {
                String firstLineInLoop = printStringSomeTimes(" ", intermediateDividedLength) + mod;
                columnStr.add(firstLineInLoop);
                return columnStr;
            }


            String secondLineInLoop;
            int nextIntermediateMod = nextIntermediateDivided % divider;
            int intForSecondLineInLoop = nextIntermediateDivided - nextIntermediateMod;
            secondLineInLoop = printStringSomeTimes(" ", countForMod + 1) +
                    intForSecondLineInLoop;
            columnStr.add(secondLineInLoop);


            String thirdLineInLoop;
            thirdLineInLoop = printStringSomeTimes(" ", countForMod + 1) +
                    printStringSomeTimes("-", lengthInt(intForSecondLineInLoop));

            columnStr.add(thirdLineInLoop);

            //если 4я строка последняя

            // int nextIntermediateDividedLength = lengthInt(nextIntermediateDivided);
            int nextMainDividendLength = lengthInt(nextMainDivided);
            // int nextIntermediateModLength = lengthInt(nextIntermediateMod);
            /*countForMod = nextIntermediateDividedLength
                    - nextIntermediateModLength
                    + intermediateDividedLength;*/

            countForMod = countForMod + intermediateDividedLength;
            if (intermediateDividedLength == nextMainDividendLength) {
                String fourthLineInLoop = printStringSomeTimes(" ", countForMod) + mod;
                columnStr.add(fourthLineInLoop);
                return columnStr;
            }
            //инит для следующей петли

            int nextIntermediateDividedLength = lengthInt(nextIntermediateDivided);
            nextMainDivided = nextMainDivided(nextMainDivided,
                    nextIntermediateMod,
                    nextIntermediateDividedLength);
            intermediateDivided = nextIntermediateDivided;
            intermediateDividedLength = lengthInt(intermediateDivided);
            intermediateMod=nextIntermediateMod;
            intermediateModLength=lengthInt(intermediateMod);
        }

        return columnStr;
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
}
