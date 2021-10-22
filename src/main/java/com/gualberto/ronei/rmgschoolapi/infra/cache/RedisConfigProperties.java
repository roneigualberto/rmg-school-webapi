package com.gualberto.ronei.rmgschoolapi.infra.cache;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisConfigProperties {


    private String host;

    private int port;

}
