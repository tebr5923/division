package com.foxminded.divider;

import com.foxminded.storage.NumberWithPosition;

public class IntegerDividerWithZeroStep extends IntegerDividerTemplate {
    @Override
    protected NumberWithPosition nextShortDividend(int mainDividend,
                                                   NumberWithPosition reminderWithPosition,
                                                   int positionInMainDividend,
                                                   int divider) {
        String nextDigitFromMainDividend = Integer.toString(mainDividend).
                substring(positionInMainDividend, positionInMainDividend + 1);
        String nextShortDividend = reminderWithPosition.getNumber() + nextDigitFromMainDividend;
        return new NumberWithPosition(Integer.parseInt(nextShortDividend), 0);
    }
}
