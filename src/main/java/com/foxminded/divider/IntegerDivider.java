package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;

import java.util.ArrayList;
import java.util.List;

public class IntegerDivider implements Divider {
    public IntegerStorage divide(Integer dividend, Integer divider) {
        int mod = dividend % divider;
        int result = dividend / divider;
        List<Integer> column = generateColumn(dividend, divider);

        return new IntegerStorage(dividend, divider, mod, result, column);
    }

    private List<Integer> generateColumn(Integer dividend, Integer divider) {
        List<Integer> column = new ArrayList<>();

        int intermediateDividend = findIntermediateDividend(dividend, divider);

        int string = intermediateDividend - (intermediateDividend % divider);
        System.out.println("string=" + string);
        column.add(string);
        return column;
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

}
