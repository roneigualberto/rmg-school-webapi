package com.gualberto.ronei.rmgschoolapi.infra.storage;

import com.gualberto.ronei.rmgschoolapi.infra.storage.filesystem.FileSystemStorageProperties;
import com.gualberto.ronei.rmgschoolapi.infra.storage.filesystem.FileSystemStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class FileSystemStorageServiceTest {


    public static final String FILE_NAME = "some-file.txt";
    private final FileSystemStorageProperties fileSystemStorageProp = new FileSystemStorageProperties();
    private FileSystemStorageService fileSystemStorageService;


    @BeforeEach
    void setUp() {
        fileSystemStorageProp.setLocation("target/files/" + UUID.randomUUID());
        fileSystemStorageService = new FileSystemStorageService(fileSystemStorageProp);
    }


    @Test
    void shouldSaveAndLoad() {

        String contentFile = "Content File";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(contentFile.getBytes(StandardCharsets.UTF_8));

        fileSystemStorageService.store(FILE_NAME, inputStream);


        assertThat(fileSystemStorageProp.getLocationPath().resolve(FILE_NAME)).exists();

    }

}