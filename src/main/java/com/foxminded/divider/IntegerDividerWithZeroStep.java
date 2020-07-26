package com.foxminded.divider;

import com.foxminded.storage.NumberWithPosition;

public class IntegerDividerWithZeroStep extends IntegerDividerTemplate {
    @Override
    protected NumberWithPosition<Integer> nextShortDividend(int mainDividend,
                                                            NumberWithPosition<Integer> reminderWithPosition,
                                                            int positionInMainDividend,
                                                            int divider) {
        int nextDividendDigit = Integer.toString(mainDividend).charAt(positionInMainDividend) - '0';
        int nextShortDividend = reminderWithPosition.getValue() * 10 + nextDividendDigit;
        return new NumberWithPosition<>(nextShortDividend, 0);
    }
}
