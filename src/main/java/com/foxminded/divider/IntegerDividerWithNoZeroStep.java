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
        //int lengthBD = lengthInt(bigDividend);
        while (nextSmallDividend < divider) {
            //while (nextSmallDividend < divider && positionInBigDividend + i != lengthBD) {
            i++;
            nextSmallDividend = Integer.parseInt(modWithPosition.getNumber() +
                    Integer.toString(bigDividend).
                            substring(positionInBigDividend, positionInBigDividend + i));
            if (positionInBigDividend + i == lengthInt(bigDividend)) {
                break;
            }
            if (nextSmallDividend == 0) {
                positionShift++;
                this.positionInBigDividend++;
                count++;
            }
        }
        return new NumberWithPosition(nextSmallDividend, modWithPosition.getPosition() + positionShift);
    }
}
