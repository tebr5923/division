package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;
import com.foxminded.storage.NumberWithPosition;

import java.util.ArrayList;
import java.util.List;

public class IntegerDivider implements Divider<Integer> {
    @Override
    public IntegerStorage divide(Integer bigDividend, Integer divider) {
        if (divider == 0) {
            throw new IllegalArgumentException("division by zero");
        }
        if (divider < 0) {
            throw new IllegalArgumentException("divider must be positive");
        }
        List<NumberWithPosition> representations = new ArrayList<>();
        representations.add(new NumberWithPosition(bigDividend, 0));
        if (bigDividend < divider) {
            representations.add(new NumberWithPosition(0, lengthInt(bigDividend) - 1));
            representations.add(new NumberWithPosition(bigDividend, 0));
        } else {
            int currentPosition = 0;
            int intermediateDividend = findFirstSmallDividend(bigDividend, divider);
            int positionInBigDividend = lengthInt(intermediateDividend);
            int countTo = lengthInt(bigDividend) - positionInBigDividend;
            for (int count = 0; count < countTo; count++) {
                StepResultStorage stepResult = doOneStep(intermediateDividend, divider);
                representations.add(stepResult.getMultiplication().addOffSet(currentPosition));
                if (intermediateDividend == 0) {
                    currentPosition++;
                }
                NumberWithPosition nextSmallDividendWithPosition = nextSmallDividendWithPosition(bigDividend,
                        stepResult.getMod(), positionInBigDividend);
                representations.add(nextSmallDividendWithPosition.addOffSet(currentPosition));

                currentPosition = stepResult.getNextPosition(currentPosition);
                intermediateDividend = nextSmallDividendWithPosition.getNumber();
                positionInBigDividend++;
            }
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

    private int lengthInt(int integer) {
        return Integer.toString(integer).length();
    }

    private NumberWithPosition nextSmallDividendWithPosition(int bigDividend,
                                                             NumberWithPosition modWithPosition,
                                                             int positionInBigDividend) {
        return new NumberWithPosition(Integer.parseInt(modWithPosition.getNumber() +
                Integer.toString(bigDividend).
                        substring(positionInBigDividend, positionInBigDividend + 1)), modWithPosition.getPosition());
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
}
