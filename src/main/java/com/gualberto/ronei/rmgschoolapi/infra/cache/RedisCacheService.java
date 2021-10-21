package com.gualberto.ronei.rmgschoolapi.infra.cache;

import com.gualberto.ronei.rmgschoolapi.domain.shared.cache.CacheService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
@AllArgsConstructor
public class RedisCacheService implements CacheService {

    private final Jedis jedis;


    @Override
    public String get(String key) {
        return jedis.get(key);
    }

    @Override
    public void put(String key, String value) {
        jedis.set(key, value);
    }

    @Override
    public void delete(String key) {
        jedis.del(key);
    }
}
