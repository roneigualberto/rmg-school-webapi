package com.gualberto.ronei.rmgschoolapi.infra.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Configuration
@ConfigurationProperties("storage")
@Getter
@Setter
public class FileSystemStorageProperties {
    private String location = "target/files";

    public Path getLocationPath() {
        return Paths.get(location);
    }

}
