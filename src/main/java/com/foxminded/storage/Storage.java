package com.foxminded.storage;

import java.util.List;

public interface Storage<T> {
    List<Representation> getRepresentations();

    T getDividend();

    T getDivider();

    T getMod();

    T getResult();
}
