package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;
import com.foxminded.storage.NumberWithPosition;

import java.util.ArrayList;
import java.util.List;

public abstract class IntegerDividerTemplate implements Divider<Integer> {
    @Override
    public IntegerStorage divide(Integer mainDividend, Integer divider) {
        checkArguments(mainDividend, divider);
        List<NumberWithPosition<Integer>> representations = new ArrayList<>();
        representations.add(new NumberWithPosition<>(mainDividend, 0));
        if (mainDividend < divider) {
            representations.add(new NumberWithPosition<>(0, lengthInt(mainDividend) - 1));
            representations.add(new NumberWithPosition<>(mainDividend, 0));
            return new IntegerStorage(representations,
                    mainDividend,
                    divider);
        }
        int currentPosition = 0;
        int intermediateDividend = findFirstShortDividend(mainDividend, divider);
        int positionInMainDividend = lengthInt(intermediateDividend);
        while (lengthInt(mainDividend) - positionInMainDividend > 0) {
            StepResultStorage<Integer> stepResult = doOneStep(intermediateDividend, divider);
            representations.add(stepResult.getMultiplicationResult().addOffset(currentPosition));
            if (intermediateDividend == 0) {
                currentPosition++;
            }
            NumberWithPosition<Integer> nextShortDividendWithPosition = nextShortDividend(mainDividend,
                    stepResult.getRemainder(), positionInMainDividend, divider);

            positionInMainDividend = positionInMainDividend + nextShortDividendWithPosition.getPosition();
            nextShortDividendWithPosition = nextShortDividendWithPosition.addOffset(stepResult.getRemainderPosition());

            representations.add(nextShortDividendWithPosition.addOffset(currentPosition));
            currentPosition = currentPosition + (stepResult.getMultiplicationResultNumber() == 0
                    ? stepResult.getRemainderPosition()
                    : Math.max(stepResult.getMultiplicationResultPosition(), nextShortDividendWithPosition.getPosition()));

            intermediateDividend = nextShortDividendWithPosition.getValue();
            positionInMainDividend = positionInMainDividend +
                    lengthInt(nextShortDividendWithPosition.getValue()) -
                    lengthInt(stepResult.getRemainderNumber());
            if (stepResult.getRemainderNumber() == 0) {
                positionInMainDividend++;
            }
        }
        if (intermediateDividend != 0 && positionInMainDividend <= lengthInt(mainDividend)) {
            int multiplicationResult = computeMultiplicationResult(intermediateDividend, divider);
            NumberWithPosition<Integer> multiplicationResultWithPosition =
                    new NumberWithPosition<>(multiplicationResult, currentPosition);
            representations.add(multiplicationResultWithPosition
                    .addOffset(lengthInt(intermediateDividend)
                            - lengthInt(multiplicationResult)));
            int reminder = mainDividend % divider;
            NumberWithPosition<Integer> reminderWithPosition = new NumberWithPosition<>(reminder, currentPosition);
            representations.add(reminderWithPosition
                    .addOffset(lengthInt(intermediateDividend)
                            - lengthInt(reminder)));
        }

        return new IntegerStorage(representations,
                mainDividend,
                divider);
    }

    private StepResultStorage<Integer> doOneStep(int shortDividend, int divider) {
        int position = 0;
        NumberWithPosition<Integer> multiplicationResultWithPosition;
        NumberWithPosition<Integer> reminderWithPosition;

        if (shortDividend < divider) {
            multiplicationResultWithPosition = new NumberWithPosition<>(0, lengthInt(shortDividend) - 1);
            reminderWithPosition = new NumberWithPosition<>(shortDividend, position);
            return new StepResultStorage<>(multiplicationResultWithPosition, reminderWithPosition);
        }
        int multiplicationResult = computeMultiplicationResult(shortDividend, divider);
        if (lengthInt(shortDividend) > lengthInt(multiplicationResult)) {
            position++;
        }
        multiplicationResultWithPosition = new NumberWithPosition<>(multiplicationResult, position);
        int reminder = shortDividend - multiplicationResult;
        position = position + lengthInt(multiplicationResult) - lengthInt(reminder);
        if (reminder == 0) {
            position++;
        }
        reminderWithPosition = new NumberWithPosition<>(reminder, position);
        return new StepResultStorage<>(multiplicationResultWithPosition, reminderWithPosition);
    }

    protected int lengthInt(int integer) {
        return Integer.toString(integer).length();
    }

    private int findFirstShortDividend(int dividend, int divider) {
        String strDividend = Integer.toString(dividend);
        int firstShortDividend = Integer.parseInt(strDividend.substring(0, lengthInt(divider)));
        if (firstShortDividend < divider) {
            firstShortDividend = Integer.parseInt(strDividend.substring(0, lengthInt(divider) + 1));
        }
        return firstShortDividend;
    }

    private int computeMultiplicationResult(int intermediateDividend, int divider) {
        return intermediateDividend - intermediateDividend % divider;
    }

    private void checkArguments(int dividend, int divider) {
        if (divider <= 0) {
            throw new IllegalArgumentException("divider must be > 0");
        }
        if (dividend < 0) {
            throw new IllegalArgumentException("dividend must be non-negative");
        }
    }

    protected abstract NumberWithPosition<Integer> nextShortDividend(int mainDividend,
                                                                     NumberWithPosition<Integer> reminderWithPosition,
                                                                     int positionInMainDividend,
                                                                     int divider);
}
