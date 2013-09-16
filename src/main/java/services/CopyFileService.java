package services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class CopyFileService {

    private static Logger logger = Logger.getLogger(CopyFileService.class.getName());

    public void copyFile(String originalPath, String destinationPath) {
        logger.info("Parameters - originalPath and destinationPath passed");
        Path original = Paths.get(originalPath);
        Path destination = Paths.get(destinationPath);
        try {
            Files.copy(original, destination, LinkOption.NOFOLLOW_LINKS);
            logger.finer("Copy file operation was performed. File copied from" + originalPath +
                    " to " + destinationPath );
        } catch (IOException x) {
            logger.warning("Copy File operation was failed. File " + originalPath +
                    " was not copied to" + destinationPath);
        }
    }

}
