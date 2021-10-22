package com.gualberto.ronei.rmgschoolapi.infra.storage.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.gualberto.ronei.rmgschoolapi.domain.shared.storage.StorageService;
import lombok.AllArgsConstructor;

import java.io.InputStream;

@AllArgsConstructor
public class AWSService implements StorageService {

    private final AmazonS3 amazonS3;

    @Override
    public void store(String path, InputStream inputStream) {
        amazonS3.putObject("good_shoop_api", path, inputStream, null);

    }

    @Override
    public InputStream get(String path) {
        S3Object s3Obj = amazonS3.getObject("good_shoop_api", path);
        return s3Obj.getObjectContent();
    }
}
