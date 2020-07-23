package com.foxminded.storage;

import java.util.List;

public interface Storage<T> {
    List<NumberWithPosition<T>> getRepresentations();

    T getDividend();

    T getDivider();

    T getRemainder();

    T getResult();
}
