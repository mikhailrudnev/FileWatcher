package services;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileSystemWatchService {

    private static final Logger logger = Logger.getLogger(FileSystemWatchService.class.getName());

    private FileSystem fileSystem = FileSystems.getDefault();
    private WatchService watchService;
    private Path path;

    public FileSystemWatchService(String pathToFolder) {
        path = Paths.get(pathToFolder);
        setLoggerHandler();
    }

    private void setLoggerHandler() {

        FileHandler handler = null;
        try{
            handler = new FileHandler("C:/test.log");
            logger.addHandler(handler);
            SimpleFormatter formatter = new SimpleFormatter();
            handler.setFormatter(formatter);
        }catch(Exception e){
            logger.warning(e.getMessage());
        }
    }

    public void setWatchService() {
        try {
            watchService = fileSystem.newWatchService();
        } catch (IOException e) {
            logger.warning("Watch service was not set. " + e.getMessage());
        }
    }

    public void setWachable() {
        try {
            path.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE);
        } catch(IOException e){
            logger.warning("Wachable was not set. " + e.getMessage());
        }
    }

    public void setEventsListener() {
        for (;;) {
            WatchKey key = null;
            try {
                key = watchService.take();
            } catch(Exception e){
                logger.warning(e.toString());
            }

            eventHandler(key);

            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }

    private void eventHandler(WatchKey key) {
        for (WatchEvent event : key.pollEvents()) {
            if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                logger.info("File was changed " + event.context().toString());
            }
            if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                logger.info("File was deleted " + event.context().toString());
            }
            if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                logger.info("New file was added " + event.context().toString());
            }
        }
    }

}
