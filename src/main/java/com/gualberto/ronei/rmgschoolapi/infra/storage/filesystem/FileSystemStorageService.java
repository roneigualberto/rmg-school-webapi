package com.gualberto.ronei.rmgschoolapi.infra.storage.filesystem;

import com.gualberto.ronei.rmgschoolapi.domain.shared.storage.StorageException;
import com.gualberto.ronei.rmgschoolapi.domain.shared.storage.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@AllArgsConstructor
@Primary
public class FileSystemStorageService implements StorageService {

    private final FileSystemStorageProperties storageProp;


    @Override
    public void store(String path, InputStream inputStream) {

        String crossPlatformPath = path.replace("/", File.separator);

        Objects.requireNonNull(inputStream, "required");

        Path locationPath = storageProp.getLocationPath();

        Path destinationPath = locationPath.resolve(crossPlatformPath).normalize().toAbsolutePath();

        try (InputStream in = inputStream) {
            Files.createDirectories(destinationPath.getParent());
            Files.copy(in, destinationPath, REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new StorageException("Failed to storage file");
        }
    }

    @Override
    public InputStream get(String path) {
        try {
            Path file = load(path);
            return Files.newInputStream(file.toAbsolutePath());
        } catch (IOException e) {
            throw new StorageException("Could not read file: " + path);
        }
    }


    private Path load(String path) {
        Path locationPath = storageProp.getLocationPath();
        return locationPath.resolve(path);
    }

}
