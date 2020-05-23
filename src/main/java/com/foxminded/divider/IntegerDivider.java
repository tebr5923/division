package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;

import java.util.ArrayList;
import java.util.List;

public class IntegerDivider implements Divider {
    public IntegerStorage divide(Integer dividend, Integer divider) {
        int mod = dividend % divider;
        int result = dividend / divider;

        List<Integer> columnInt = new ArrayList<>();

        return new IntegerStorage(dividend, divider, mod, result, columnInt);
    }

    private int findIntermediateDividend(int dividend, int divider) {
        StringBuilder sbDividend = new StringBuilder(Integer.toString(dividend));
        int intermediateDividend = Integer.parseInt(sbDividend.substring(0, 1));
        int i = 1;
        while (intermediateDividend <= divider) {
            i++;
            intermediateDividend = Integer.parseInt(sbDividend.substring(0, i));
        }
        return intermediateDividend;
    }

    // find intermediateDividend, dividend*divider(second line), next dividend (fourth line)
    public List<Integer> doOneStep(Integer dividend, Integer divider) {
        List<Integer> list = new ArrayList<>();
        StringBuilder sbDividend = new StringBuilder(dividend.toString());
        int intermediateDividend = Integer.parseInt(sbDividend.substring(0, 1));
        int i = 1;
        while (intermediateDividend <= divider) {
            i++;
            intermediateDividend = Integer.parseInt(sbDividend.substring(0, i));
        }

        int intermediateMod = intermediateDividend % divider;
        int intForSecondLine = intermediateDividend - intermediateMod;

        list.add(intermediateDividend);
        list.add(intForSecondLine);

        char[] arrayOfDividend = dividend.toString().toCharArray();
        String sbForFourthLine = String.valueOf(intermediateMod) +
                arrayOfDividend[i];
        list.add(Integer.parseInt(sbForFourthLine));


        return list;
    }

    //-----------------------------------------------------
    public List<String> generateColumn(Integer dividend, Integer divider) {
        int mod = dividend % divider;
        int result = dividend / divider;
        List<String> columnStr = new ArrayList<>();
        String minus = "_";
        String firstLine;

        firstLine = minus + dividend + "|" + divider;
        columnStr.add(firstLine);

        int intermediateDividend = findIntermediateDividend(dividend, divider);
        int intermediateMod = intermediateDividend % divider;
        int intForSecondLine = intermediateDividend - intermediateMod;
        int dividendLength = lengthInt(dividend);
        int timesForSpace = dividendLength - lengthInt(intForSecondLine);
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


        int intermediateDividendLength = lengthInt(intermediateDividend);
        int intermediateModLength = lengthInt(intermediateMod);
        int countForMod = intermediateDividendLength - intermediateModLength;
        if (intermediateDividendLength == dividendLength) {
            String fourthLine = printStringSomeTimes(" ", countForMod + 1) + mod;
            columnStr.add(fourthLine);
            return columnStr;
        }

// надо прибавить слева остаток от деления
        int nextDivided = concatIntegerAndString(intermediateMod,
                cutIntToIndex(dividend, intermediateDividendLength - 1));
        int nextIntermediateDividend =findIntermediateDividend(nextDivided, divider);

        char[] arrayOfDividend = dividend.toString().toCharArray();

        String stringNextDividend = String.valueOf(intermediateMod) +
                arrayOfDividend[intermediateDividendLength];
        int nextDividend = Integer.parseInt(stringNextDividend);
        if (nextDividend >= divider) {
            String fourthLine = printStringSomeTimes(" ", countForMod) + minus + nextDividend;
            columnStr.add(fourthLine);
        } else {
            String fourthLine = printStringSomeTimes(" ", intermediateDividendLength) + mod;
            columnStr.add(fourthLine);
            return columnStr;
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

    private String cutIntToIndex(int integer, int index) {
        StringBuilder sb = new StringBuilder(Integer.toString(integer));
        return sb.substring(index + 1);
    }

    private int concatIntegerAndString(int left, String right) {
        String result = left + right;
        return Integer.parseInt(result);
    }
}
