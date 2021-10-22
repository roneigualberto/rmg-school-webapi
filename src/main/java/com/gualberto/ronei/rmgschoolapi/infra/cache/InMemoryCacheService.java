package com.gualberto.ronei.rmgschoolapi.infra.cache;

import com.gualberto.ronei.rmgschoolapi.domain.shared.cache.CacheService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service()
public class InMemoryCacheService implements CacheService {

    private static final Map<String, String> caches = new HashMap<>();

    @Override
    public String get(String key) {
        return caches.get(key);
    }

    @Override
    public void put(String key, String value) {
        caches.put(key, value);
    }

    @Override
    public void delete(String key) {
        caches.remove(key);
    }
}
