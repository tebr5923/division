package com.foxminded.divider;

import com.foxminded.storage.IntegerStorage;
import com.foxminded.storage.StepRepresentation;
import com.foxminded.utils.StepResultStorage;

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
        List<StepRepresentation> stepRepresentations = new ArrayList<>();
        int currentPosition = 0;
        stepRepresentations.add(new StepRepresentation(bigDividend, currentPosition));
        int intermediateDividend = findFirstSmallDividend(bigDividend, divider);
        int positionInBigDivider = lengthInt(intermediateDividend);
        int countTo = lengthInt(bigDividend) - positionInBigDivider;
        for (int count = 0; count < countTo; count++) {
            StepResultStorage result = doOneStep(intermediateDividend, divider);
            stepRepresentations.add(
                    new StepRepresentation(result.getMultiplication().getNumber(),
                            currentPosition + result.getMultiplication().getPosition()));
            if (intermediateDividend == 0) {
                currentPosition++;
            }
            int nextSmallDividend = nextSmallDividend(bigDividend,
                    result.getMod().getNumber(), positionInBigDivider);
            stepRepresentations.add(new StepRepresentation(nextSmallDividend,
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
        stepRepresentations.add(
                new StepRepresentation(
                        computeMultiplication(intermediateDividend, divider),
                        currentPosition
                                + lengthInt(intermediateDividend)
                                - lengthInt(computeMultiplication(intermediateDividend, divider)))
        );
        stepRepresentations.add(
                new StepRepresentation(bigDividend % divider,
                        currentPosition +
                                lengthInt(intermediateDividend) -
                                lengthInt(bigDividend % divider))
        );
        return new IntegerStorage(stepRepresentations,
                bigDividend,
                divider,
                bigDividend % divider,
                bigDividend / divider);
    }

    private StepResultStorage doOneStep(int smallDividend, int divider) {
        int position = 0;
        StepRepresentation multRepresentation = null;
        StepRepresentation modRepresentation = null;

        if (smallDividend < divider) {
            multRepresentation = new StepRepresentation(0, lengthInt(smallDividend) - 1);
            modRepresentation = new StepRepresentation(smallDividend, position);
        } else {
            int multiplication = computeMultiplication(smallDividend, divider);
            if (lengthInt(smallDividend) > lengthInt(multiplication)) {
                position++;
            }
            multRepresentation = new StepRepresentation(multiplication, position);
            int mod = smallDividend - multiplication;
            position = position + lengthInt(multiplication) - lengthInt(mod);
            if (mod == 0) {
                position++;
            }
            modRepresentation = new StepRepresentation(mod, position);
        }
        return new StepResultStorage(multRepresentation, modRepresentation);
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
