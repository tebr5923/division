package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;

import java.util.ArrayList;
import java.util.List;

public class IntegerDivider implements Divider {
    public IntegerStorage divide(Integer dividend, Integer divider) {
        int mod = dividend % divider;
        int result = dividend / divider;
        List<Integer> columnInt = generateColumnInt(dividend, divider);

        List<String> columnStr = generateColumnStr(dividend, divider);

        return new IntegerStorage(dividend, divider, mod, result, columnInt);
    }

    private List<Integer> generateColumnInt(Integer dividend, Integer divider) {
        List<Integer> columnInt = new ArrayList<>();

        int intermediateDividend = findIntermediateDividend(dividend, divider);

        int mod = intermediateDividend % divider;
        int lineInt = intermediateDividend - mod;
        System.out.println("lineInt=" + lineInt);
        columnInt.add(lineInt);

        return columnInt;
    }

    private List<String> generateColumnStr(Integer dividend, Integer divider) {
        List<String> columnStr = new ArrayList<>();

        int intermediateDividend = findIntermediateDividend(dividend, divider);

        int mod = intermediateDividend % divider;
        int lineInt = intermediateDividend - mod;

        String lineStr = Integer.toString(lineInt);
        System.out.println("lineStr=" + lineStr);
        columnStr.add(lineStr);

        return columnStr;
    }

    private int findIntermediateDividend(Integer dividend, Integer divider) {
        StringBuilder sbDividend = new StringBuilder(dividend.toString());
        int intermediateDividend = Integer.parseInt(sbDividend.substring(0, 1));
        int i = 1;
        while (intermediateDividend <= divider) {
            i++;
            intermediateDividend = Integer.parseInt(sbDividend.substring(0, i));
        }
        return intermediateDividend;
    }

    // find intermediateDividend, dividend*divider(second line),
// concat intermediate mod with next digit from dividend drop intermediateDividend (fourth line)
//
    public List<Integer> findNextLines(Integer dividend, Integer divider) {
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

/*        StringBuilder sbForFourthLine= new StringBuilder();
        sbForFourthLine.append(intermediateMod);
        sbForFourthLine.append(arrayOfDividend[i]);
        list.add(Integer.parseInt(sbForFourthLine.toString()));*/






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
        //int intForSecondLine =  (intermediateDividend/divider)*divider;
        int intForSecondLine = intermediateDividend - intermediateMod;
        int timesForSpace = dividend.toString().length() - Integer.toString(intForSecondLine).length();
        int timesForMinus;
        if (result >= divider) {
            timesForMinus = Integer.toString(result).length();
        } else {
            timesForMinus = Integer.toString(divider).length();
        }
        String secondLine;
        secondLine = " " + intForSecondLine +
                printStringSomeTimes(" ", timesForSpace) +
                "|" + printStringSomeTimes("-", timesForMinus);
        columnStr.add(secondLine);

        timesForMinus = Integer.toString(intForSecondLine).length();
        String thirdLine;
        thirdLine = " " + printStringSomeTimes("-", timesForMinus) +
                printStringSomeTimes(" ", timesForSpace) +
                "|" + result;
        columnStr.add(thirdLine);


/*        StringBuilder sbDividend = new StringBuilder(dividend.toString());
        StringBuilder sb = new StringBuilder(intermediateMod);
        sb.append();
        while () {

        }*/


        columnStr.forEach(System.out::println);


        return columnStr;
    }

    private String printStringSomeTimes(String string, int times) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < times; i++) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }
    //-----------------------------------------------------

    private int findNextLine(Integer dividend, Integer divider) {
        StringBuilder sbDividend = new StringBuilder(dividend.toString());
        int intermediateDividend = Integer.parseInt(sbDividend.substring(0, 1));
        int i = 1;
        while (intermediateDividend <= divider) {
            i++;
            intermediateDividend = Integer.parseInt(sbDividend.substring(0, i));
        }
        return intermediateDividend;
    }

}
