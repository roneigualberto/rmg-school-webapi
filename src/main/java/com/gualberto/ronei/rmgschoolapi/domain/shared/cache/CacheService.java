package com.gualberto.ronei.rmgschoolapi.domain.shared.cache;

public interface CacheService {


    String get(String key);

    void put(String key, String value);

    void delete(String key);
}
