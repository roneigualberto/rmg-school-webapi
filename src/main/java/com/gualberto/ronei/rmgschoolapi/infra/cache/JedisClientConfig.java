package com.gualberto.ronei.rmgschoolapi.infra.cache;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class JedisClientConfig {

    @Bean
    public Jedis jedis(RedisConfig redisConfig) {
        return new Jedis(redisConfig.getHost(), redisConfig.getPort());
    }
}
