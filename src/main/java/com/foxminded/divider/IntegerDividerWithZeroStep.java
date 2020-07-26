package com.foxminded.divider;

import com.foxminded.storage.NumberWithPosition;

public class IntegerDividerWithZeroStep extends IntegerDividerTemplate {
    @Override
    protected NumberWithPosition<Integer> nextShortDividend(int mainDividend,
                                                   NumberWithPosition<Integer> reminderWithPosition,
                                                   int positionInMainDividend,
                                                   int divider) {
        String nextDigitFromMainDividend = Integer.toString(mainDividend).
                substring(positionInMainDividend, positionInMainDividend + 1);
        String nextShortDividend = reminderWithPosition.getValue() + nextDigitFromMainDividend;
        return new NumberWithPosition<>(Integer.parseInt(nextShortDividend), 0);
    }
}
