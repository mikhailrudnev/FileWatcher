package services;

import java.io.File;
import java.util.logging.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFileService {
    private static Logger logger = Logger.getLogger(CopyFileService.class.getName());

    public void createFile(String pathFile) {
        File file = new File(pathFile);
        try{
            file.createNewFile();
            logger.info("File - " + pathFile + " was succesfully created.");
        } catch(IOException e) {
            logger.warning(e.getMessage());
        }
    }
}
