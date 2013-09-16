package services;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class DeleteFileService {
    private static final Logger logger = Logger.getLogger(DeleteFileService.class.getName());

    public void deleteFile(String pathToFile) {
        Path path = Paths.get(pathToFile);
        try {
            Files.delete(path);
            logger.info("File " + pathToFile + "was deleted");
        } catch (NoSuchFileException e) {
            logger.warning("File " + pathToFile + "was not deleted. Exception - " + e.getMessage());
        } catch (DirectoryNotEmptyException e) {
            logger.warning("File " + pathToFile + "was not deleted. Exception - " + e.getMessage());
        } catch (IOException e) {
            logger.warning("File " + pathToFile + "was not deleted. Exception - " + e.getMessage());
        } catch (Exception e) {
            logger.warning("File " + pathToFile + "was not deleted. Exception - " + e.getMessage());
        }
    }

}
