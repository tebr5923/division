package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;
import com.foxminded.storage.NumberWithPosition;

import java.util.ArrayList;
import java.util.List;

public abstract class IntegerDividerTemplate implements Divider<Integer> {
    @Override
    public IntegerStorage divide(Integer mainDividend, Integer divider) {
        checkDivider(divider);
        List<NumberWithPosition> representations = new ArrayList<>();
        representations.add(new NumberWithPosition(mainDividend, 0));
        if (mainDividend < divider) {
            representations.add(new NumberWithPosition(0, lengthInt(mainDividend) - 1));
            representations.add(new NumberWithPosition(mainDividend, 0));
        } else {
            int currentPosition = 0;
            int intermediateDividend = findFirstShortDividend(mainDividend, divider);
            int positionInMainDividend = lengthInt(intermediateDividend);
            while (lengthInt(mainDividend) - positionInMainDividend > 0){
                StepResultStorage stepResult = doOneStep(intermediateDividend, divider);
                representations.add(stepResult.getMultiplication().addOffSet(currentPosition));
                if (intermediateDividend == 0) {
                    currentPosition++;
                }
                NumberWithPosition nextShortDividendWithPosition = nextShortDividend(mainDividend,
                        stepResult.getMod(), positionInMainDividend, divider);

                positionInMainDividend = positionInMainDividend + nextShortDividendWithPosition.getPosition();
                nextShortDividendWithPosition = nextShortDividendWithPosition.addOffSet(stepResult.getModPosition());

                representations.add(nextShortDividendWithPosition.addOffSet(currentPosition));
                currentPosition = currentPosition + (stepResult.getMultNumber() == 0
                        ? stepResult.getModPosition()
                        : Math.max(stepResult.getMultPosition(), nextShortDividendWithPosition.getPosition()));

                intermediateDividend = nextShortDividendWithPosition.getNumber();
                positionInMainDividend = positionInMainDividend +
                        lengthInt(nextShortDividendWithPosition.getNumber()) -
                        lengthInt(stepResult.getModNumber());
                if (stepResult.getModNumber() == 0) {
                    positionInMainDividend++;
                }
            }
            if (intermediateDividend != 0 && positionInMainDividend <= lengthInt(mainDividend)) {
                int multiplication = computeMultiplication(intermediateDividend, divider);
                representations.add(
                        new NumberWithPosition(
                                multiplication,
                                currentPosition
                                        + lengthInt(intermediateDividend)
                                        - lengthInt(multiplication))
                );
                representations.add(
                        new NumberWithPosition(mainDividend % divider,
                                currentPosition +
                                        lengthInt(intermediateDividend) -
                                        lengthInt(mainDividend % divider))
                );
            }
        }
        return new IntegerStorage(representations,
                mainDividend,
                divider);
    }

    private StepResultStorage doOneStep(int shortDividend, int divider) {
        int position = 0;
        NumberWithPosition multRepresentation;
        NumberWithPosition modRepresentation;

        if (shortDividend < divider) {
            multRepresentation = new NumberWithPosition(0, lengthInt(shortDividend) - 1);
            modRepresentation = new NumberWithPosition(shortDividend, position);
        } else {
            int multiplication = computeMultiplication(shortDividend, divider);
            if (lengthInt(shortDividend) > lengthInt(multiplication)) {
                position++;
            }
            multRepresentation = new NumberWithPosition(multiplication, position);
            int mod = shortDividend - multiplication;
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

    private int findFirstShortDividend(int dividend, int divider) {
        String strDividend = Integer.toString(dividend);
        int firstShortDividend = Integer.parseInt(strDividend.substring(0, lengthInt(divider)));
        if (firstShortDividend < divider) {
            firstShortDividend = Integer.parseInt(strDividend.substring(0, lengthInt(divider) + 1));
        }
        return firstShortDividend;
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

    abstract NumberWithPosition nextShortDividend(int mainDividend,
                                                  NumberWithPosition modWithPosition,
                                                  int positionInMainDividend,
                                                  int divider);
}
