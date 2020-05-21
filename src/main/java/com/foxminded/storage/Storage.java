package com.foxminded.storage;

import java.util.List;

public interface Storage {
    void generateRepresentation();
    List<String> getRepresentation();
}
