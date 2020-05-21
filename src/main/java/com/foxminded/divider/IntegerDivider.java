package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;

import java.util.ArrayList;
import java.util.List;

public class IntegerDivider implements Divider{
    public IntegerStorage divide(int dividend, int divider) {
        int mod = dividend % divider;
        int result = dividend / divider;
        List<Integer> column = new ArrayList<>();
        System.out.println("mod = " + mod);
        System.out.println("res = " + result);

        return new IntegerStorage(dividend, divider, mod, result, column);
    }
}
