import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import services.DeleteFileService;
import services.FileSystemWatchService;
import services.MoveFileService;
import services.CreateFileService;

public class Main {
    public static void main(String args[]){
        FileSystemWatchService watcher = new FileSystemWatchService("C:/watcher");
        watcher.setWatchService();
        watcher.setWachable();
        watcher.setEventsListener();

        CreateFileService createFileService = new CreateFileService();
        createFileService.createFile("C:/watcher/filetest.txt");

		DeleteFileService deleteFileService = new DeleteFileService();
		deleteFileService.deleteFile("C:/watcher/del.txt");
		
		
		MoveFileService moveFileService = new MoveFileService();
		moveFileService.moveFile("C:/watcher/del.txt", "C:/watcher2/del.txt");

    }
}
