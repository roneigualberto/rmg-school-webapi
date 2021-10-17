package com.gualberto.ronei.rmgschoolapi.domain.shared.storage;

import java.io.InputStream;

public interface StorageService {
    void store(String path, InputStream inputStream);

    InputStream get(String path);

}
