package com.foxminded.storage;

import java.util.List;

public interface Storage {
    List<StepRepresentation> getStepRepresentations();

    int getDividend();

    int getDivider();

    int getMod();

    int getResult();
}
