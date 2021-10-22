package com.gualberto.ronei.rmgschoolapi.infra.storage.google;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.gualberto.ronei.rmgschoolapi.domain.shared.storage.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


@Service
@AllArgsConstructor
public class GoogleStorageService implements StorageService {


    public static final String BUCKET_NAME = "rmg_school_api";
    private final Storage storage;

    @Override
    public void store(String path, InputStream inputStream) {

        Bucket bucket = storage.create(BucketInfo.of(BUCKET_NAME));

        bucket.create(path, inputStream);

    }

    @Override
    public InputStream get(String path) {

        Bucket bucket = storage.create(BucketInfo.of(BUCKET_NAME));

        Blob blob = bucket.get(path);

        byte[] content = blob.getContent();

        return new ByteArrayInputStream(content);
    }
}
