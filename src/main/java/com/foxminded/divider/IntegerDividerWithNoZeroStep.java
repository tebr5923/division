package com.foxminded.divider;

import com.foxminded.storage.NumberWithPosition;

public class IntegerDividerWithNoZeroStep extends IntegerDividerTemplate {
    @Override
    NumberWithPosition nextShortDividend(int mainDividend,
                                         NumberWithPosition modWithPosition,
                                         int positionInMainDividend,
                                         int divider) {
        int nextShortDividend = modWithPosition.getNumber();
        int i = 0;
        int positionShift = 0;
        while (nextShortDividend < divider) {
            i++;
            nextShortDividend = Integer.parseInt(modWithPosition.getNumber() +
                    Integer.toString(mainDividend).
                            substring(positionInMainDividend, positionInMainDividend + i));

            if (positionInMainDividend + i == lengthInt(mainDividend)) {
                break;
            }
            if (nextShortDividend == 0) {
                positionShift++;
            }
        }
        return new NumberWithPosition(nextShortDividend, positionShift);
    }
}
