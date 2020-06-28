package com.foxminded.divider;

import com.foxminded.storage.NumberWithPosition;

public class IntegerDividerWithNoZeroStep extends IntegerDividerTemplate{
    @Override
    NumberWithPosition nextSmallDividendWithPosition(int bigDividend,
                                                     NumberWithPosition modWithPosition,
                                                     int positionInBigDividend,
                                                     int divider) {
        int nextSmallDividend = modWithPosition.getNumber();
        int i = 0;
        int positionShift = 0;
       /* NumberWithPosition nextSmallDividendWithPosition =
                new NumberWithPosition(Integer.parseInt(modWithPosition.getNumber() +
                        Integer.toString(bigDividend).
                                substring(positionInBigDividend, positionInBigDividend + 1)),
                        modWithPosition.getPosition());*/

        while (nextSmallDividend < divider) {
            i++;
            nextSmallDividend = Integer.parseInt(modWithPosition.getNumber() +
                    Integer.toString(bigDividend).
                            substring(positionInBigDividend, positionInBigDividend + i));
            if (nextSmallDividend == 0) {
                positionShift++;
                positionInBigDividendGlobal++;
                countGlobal++;
            }
        }
        return new NumberWithPosition(nextSmallDividend, modWithPosition.getPosition() + positionShift);
        //return new NumberWithPosition(nextSmallDividend, modWithPosition.getPosition());
    }
}
