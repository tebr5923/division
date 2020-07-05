package com.foxminded.divider;

import com.foxminded.storage.NumberWithPosition;

public class IntegerDividerWithZeroStep extends IntegerDividerTemplate {
    @Override
    NumberWithPosition nextShortDividend(int mainDividend, NumberWithPosition reminderWithPosition, int positionInMainDividend, int divider) {
        return new NumberWithPosition(Integer.parseInt(reminderWithPosition.getNumber() +
                Integer.toString(mainDividend).
                        substring(positionInMainDividend, positionInMainDividend + 1)), 0);
    }
}
