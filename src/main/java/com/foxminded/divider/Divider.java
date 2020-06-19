package com.foxminded.divider;

import com.foxminded.storage.Storage;

public interface Divider<T> {
    Storage divide(T dividend, T divider);
}
