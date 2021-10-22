package com.gualberto.ronei.rmgschoolapi.infra.storage.aws;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWS3Config {

    public AmazonS3 s3() {
        return AmazonS3ClientBuilder.defaultClient();
    }
}
