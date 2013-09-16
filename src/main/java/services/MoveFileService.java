package services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;

public class MoveFileService {
    private static final Logger logger = Logger.getLogger(MoveFileService.class.getName());

    public void moveFile(String originalPath, String destinationPath) {
        Path original = Paths.get(originalPath);
        Path destination = Paths.get(destinationPath);
        try {
            Files.move(original, destination, StandardCopyOption.REPLACE_EXISTING);
            logger.info("File " + originalPath + "was moved.");
        } catch (IOException e) {
            logger.warning("File " + originalPath + "was not moved. Exception - " + e.getMessage());
        }
    }
}
