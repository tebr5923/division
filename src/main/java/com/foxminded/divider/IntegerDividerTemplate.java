package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;
import com.foxminded.storage.NumberWithPosition;

import java.util.ArrayList;
import java.util.List;

public abstract class IntegerDividerTemplate implements Divider<Integer> {
    @Override
    public IntegerStorage divide(Integer bigDividend, Integer divider) {
        checkDivider(divider);
        List<NumberWithPosition> representations = new ArrayList<>();
        representations.add(new NumberWithPosition(bigDividend, 0));
        if (bigDividend < divider) {
            representations.add(new NumberWithPosition(0, lengthInt(bigDividend) - 1));
            representations.add(new NumberWithPosition(bigDividend, 0));
        } else {
            int currentPosition = 0;
            int intermediateDividend = findFirstSmallDividend(bigDividend, divider);
            int positionInBigDividend = lengthInt(intermediateDividend);
            int count = 0;
            int countTo = lengthInt(bigDividend) - positionInBigDividend;

            while (count < countTo) {
                StepResultStorage stepResult = doOneStep(intermediateDividend, divider);
                representations.add(stepResult.getMultiplication().addOffSet(currentPosition));
                if (intermediateDividend == 0) {
                    currentPosition++;
                }
                NumberWithPosition nextSmallDividendWithPosition = nextSmallDividendWithPosition(bigDividend,
                        stepResult.getMod(), positionInBigDividend, divider);

                positionInBigDividend = positionInBigDividend + nextSmallDividendWithPosition.getPosition();
                count = count + nextSmallDividendWithPosition.getPosition();
                nextSmallDividendWithPosition = nextSmallDividendWithPosition.addOffSet(stepResult.getModPosition());

                representations.add(nextSmallDividendWithPosition.addOffSet(currentPosition));
                currentPosition = currentPosition + (stepResult.getMultNumber() == 0
                        ? stepResult.getModPosition()
                        : Math.max(stepResult.getMultPosition(), nextSmallDividendWithPosition.getPosition()));

                intermediateDividend = nextSmallDividendWithPosition.getNumber();
                positionInBigDividend = positionInBigDividend +
                        lengthInt(nextSmallDividendWithPosition.getNumber()) -
                        lengthInt(stepResult.getModNumber());
                count = count +
                        lengthInt(nextSmallDividendWithPosition.getNumber()) -
                        lengthInt(stepResult.getModNumber());
                if (stepResult.getModNumber() == 0) {
                    positionInBigDividend++;
                    count++;
                }
            }
            if (intermediateDividend != 0 && positionInBigDividend <= lengthInt(bigDividend)) {
                int multiplication = computeMultiplication(intermediateDividend, divider);
                representations.add(
                        new NumberWithPosition(
                                multiplication,
                                currentPosition
                                        + lengthInt(intermediateDividend)
                                        - lengthInt(multiplication))
                );
                representations.add(
                        new NumberWithPosition(bigDividend % divider,
                                currentPosition +
                                        lengthInt(intermediateDividend) -
                                        lengthInt(bigDividend % divider))
                );
            }
        }
        return new IntegerStorage(representations,
                bigDividend,
                divider);
    }

    private StepResultStorage doOneStep(int smallDividend, int divider) {
        int position = 0;
        NumberWithPosition multRepresentation;
        NumberWithPosition modRepresentation;

        if (smallDividend < divider) {
            multRepresentation = new NumberWithPosition(0, lengthInt(smallDividend) - 1);
            modRepresentation = new NumberWithPosition(smallDividend, position);
        } else {
            int multiplication = computeMultiplication(smallDividend, divider);
            if (lengthInt(smallDividend) > lengthInt(multiplication)) {
                position++;
            }
            multRepresentation = new NumberWithPosition(multiplication, position);
            int mod = smallDividend - multiplication;
            position = position + lengthInt(multiplication) - lengthInt(mod);
            if (mod == 0) {
                position++;
            }
            modRepresentation = new NumberWithPosition(mod, position);
        }
        return new StepResultStorage(multRepresentation, modRepresentation);
    }

    protected int lengthInt(int integer) {
        return Integer.toString(integer).length();
    }

    private int findFirstSmallDividend(int dividend, int divider) {
        String strDividend = Integer.toString(dividend);
        int firstSmallDividend = Integer.parseInt(strDividend.substring(0, lengthInt(divider)));
        if (firstSmallDividend < divider) {
            firstSmallDividend = Integer.parseInt(strDividend.substring(0, lengthInt(divider) + 1));
        }
        return firstSmallDividend;
    }

    private int computeMultiplication(int intermediateDividend, int divider) {
        return intermediateDividend - intermediateDividend % divider;
    }

    private void checkDivider(int divider) {
        if (divider == 0) {
            throw new IllegalArgumentException("division by zero");
        }
        if (divider < 0) {
            throw new IllegalArgumentException("divider must be positive");
        }
    }

    abstract NumberWithPosition nextSmallDividendWithPosition(int bigDividend,
                                                              NumberWithPosition modWithPosition,
                                                              int positionInBigDividend,
                                                              int divider);
}
