package com.foxminded.divider;

import com.foxminded.storage.NumberWithPosition;

public class IntegerDividerWithZeroStep extends IntegerDividerTemplate {
    @Override
    NumberWithPosition nextSmallDividend(int bigDividend, NumberWithPosition modWithPosition, int positionInBigDividend, int divider) {
        return new NumberWithPosition(Integer.parseInt(modWithPosition.getNumber() +
                Integer.toString(bigDividend).
                        substring(positionInBigDividend, positionInBigDividend + 1)), 0);
    }
}
