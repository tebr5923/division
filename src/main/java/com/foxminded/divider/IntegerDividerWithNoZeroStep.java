package com.foxminded.divider;

import com.foxminded.storage.NumberWithPosition;

public class IntegerDividerWithNoZeroStep extends IntegerDividerTemplate {
    @Override
    NumberWithPosition nextSmallDividendWithPosition(int bigDividend,
                                                     NumberWithPosition modWithPosition,
                                                     int positionInBigDividend,
                                                     int divider) {
        int nextSmallDividend = modWithPosition.getNumber();
        int i = 0;
        int positionShift = 0;
        while (nextSmallDividend < divider) {
            i++;
            nextSmallDividend = Integer.parseInt(modWithPosition.getNumber() +
                    Integer.toString(bigDividend).
                            substring(positionInBigDividend, positionInBigDividend + i));

            if (positionInBigDividend + i == lengthInt(bigDividend)) {
                break;
            }
            if (nextSmallDividend == 0) {
                positionShift++;
            }
        }
        return new NumberWithPosition(nextSmallDividend, positionShift);
    }
}
