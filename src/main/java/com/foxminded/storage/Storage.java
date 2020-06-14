package com.foxminded.storage;

import java.util.List;

public interface Storage {
    List<Representation> getRepresentations();

    int getDividend();

    int getDivider();

    int getMod();

    int getResult();
}
