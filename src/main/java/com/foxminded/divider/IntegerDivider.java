package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;
import com.foxminded.storage.Representation;
import com.foxminded.utils.OneStepResultStorage;

import java.util.ArrayList;
import java.util.List;

public class IntegerDivider implements Divider {
    @Override
    public IntegerStorage divide(int bigDividend, int divider) {
        if (divider == 0) {
            throw new NullPointerException("division by zero");
        }
        if (divider < 0) {
            throw new IllegalArgumentException("divider must be positive");
        }
        if (bigDividend < divider) {
            throw new IllegalArgumentException("divider must be more then dividend");
        }
        List<Representation> representations = new ArrayList<>();
        int currentPosition = 0;
        representations.add(new Representation(bigDividend, currentPosition));
        int intermediateDividend = findFirstSmallDividend(bigDividend, divider);
        int positionInBigDivider = lengthInt(intermediateDividend);
        int countTo = lengthInt(bigDividend) - positionInBigDivider;
        for (int count = 0; count < countTo; count++) {
            OneStepResultStorage result = doOneStep(intermediateDividend, divider);
            representations.add(
                    new Representation(result.getMultiplication().getNumber(),
                            currentPosition + result.getMultiplication().getPosition()));
            if (intermediateDividend == 0) {
                currentPosition++;
            }
            int nextSmallDividend = nextSmallDividend(bigDividend,
                    result.getMod().getNumber(), positionInBigDivider);
            representations.add(new Representation(nextSmallDividend,
                    currentPosition + result.getMod().getPosition()));
            if (result.getMultiplication().getNumber() == 0) {
                currentPosition = currentPosition + result.getMod().getPosition();
            } else {
                currentPosition = currentPosition +
                        Math.max(result.getMultiplication().getPosition(), result.getMod().getPosition());
            }
            intermediateDividend = nextSmallDividend;
            positionInBigDivider++;
        }
        representations.add(
                new Representation(
                        computeMultiplication(intermediateDividend, divider),
                        currentPosition
                                + lengthInt(intermediateDividend)
                                - lengthInt(computeMultiplication(intermediateDividend, divider)))
        );
        representations.add(
                new Representation(bigDividend % divider,
                        currentPosition +
                                lengthInt(intermediateDividend) -
                                lengthInt(bigDividend % divider))
        );
        return new IntegerStorage(representations,
                bigDividend,
                divider,
                bigDividend % divider,
                bigDividend / divider);
    }

    private OneStepResultStorage doOneStep(int smallDividend, int divider) {
        int position = 0;
        Representation multRepresentation = null;
        Representation modRepresentation = null;

        if (smallDividend < divider) {
            multRepresentation = new Representation(0, lengthInt(smallDividend) - 1);
            modRepresentation = new Representation(smallDividend, position);
        } else {
            int multiplication = computeMultiplication(smallDividend, divider);
            if (lengthInt(smallDividend) > lengthInt(multiplication)) {
                position++;
            }
            multRepresentation = new Representation(multiplication, position);
            int mod = smallDividend - multiplication;
            position = position + lengthInt(multiplication) - lengthInt(mod);
            if (mod == 0) {
                position++;
            }
            modRepresentation = new Representation(mod, position);
        }
        return new OneStepResultStorage(multRepresentation, modRepresentation);
    }

    private int lengthInt(int integer) {
        return Integer.toString(integer).length();
    }

    private int nextSmallDividend(int bigDividend,
                                  int intermediateMod,
                                  int positionInBigDividend) {
        StringBuilder sb = new StringBuilder(Integer.toString(bigDividend));
        return Integer.parseInt(intermediateMod +
                sb.substring(positionInBigDividend, positionInBigDividend + 1));
    }

    private int findFirstSmallDividend(int dividend, int divider) {
        StringBuilder sbDividend = new StringBuilder(Integer.toString(dividend));
        int firstSmallDividend = Integer.parseInt(sbDividend.substring(0, 1));
        int i = 1;
        while (firstSmallDividend < divider) {
            i++;
            firstSmallDividend = Integer.parseInt(sbDividend.substring(0, i));
        }
        return firstSmallDividend;
    }

    private int computeMultiplication(int intermediateDividend, int divider) {
        return intermediateDividend - intermediateDividend % divider;
    }
}
