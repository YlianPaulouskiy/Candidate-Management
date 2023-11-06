package by.krainet.management.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
public class FileService {

    @Value("${app.file.bucket}")
    private String bucket;

    public void upload(String filePath, InputStream content) {
        Path fullPath = Path.of(bucket, filePath);
        try (content) {
            Files.createDirectories(fullPath.getParent());
            Files.write(fullPath, content.readAllBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void remove(String imagePath) {
        Path fullImagePath = Path.of(bucket, imagePath);
        try {
            Files.delete(fullImagePath);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
