package com.foxminded.divider;

import com.foxminded.storage.Storage;

public interface Divider<T> {
    Storage<T> divide(T dividend, T divider);
}
