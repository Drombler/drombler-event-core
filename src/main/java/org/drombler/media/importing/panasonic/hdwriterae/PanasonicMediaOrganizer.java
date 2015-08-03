/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.media.importing.panasonic.hdwriterae;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.drombler.event.core.Event;
import org.drombler.event.core.FullTimeEventDuration;
import org.drombler.identity.core.User;
import org.drombler.media.core.photo.PhotoStorage;
import org.drombler.media.core.video.VideoStorage;

/**
 * Organizes Files from Panasonic import for Synology import.
 * 
 * @author Florian
 */
public class PanasonicMediaOrganizer {

    public static void main(String[] args) throws IOException {
        Path baseDirPath = Paths.get("D:\\hd-writer-ae-tmp");
        User user = new User("puce");

        PanasonicMediaOrganizer organizer = new PanasonicMediaOrganizer();
        organizer.organize(baseDirPath, user);
    }

    private static final Pattern RAW_DATE_PATTERN = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
    private static final DateTimeFormatter RAW_DATE_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public void organize(Path baseDirPath, User defaultUser) throws IOException {
        PhotoStorage photoStorage = new PhotoStorage(baseDirPath);
        VideoStorage videoStorage = new VideoStorage(baseDirPath);
        
        try (Stream<Path> paths = Files.list(baseDirPath)) {
            paths.filter(path -> RAW_DATE_PATTERN.matcher(getPathName(path)).matches())
                    .forEach(path -> {
                        try {
                            moveFiles(path, defaultUser, photoStorage, videoStorage);
                            deleteEmptySrcDir(path);
                        } catch (IOException ex) {
                            Logger.getLogger(PanasonicMediaOrganizer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
        }
    }

    private void deleteEmptySrcDir(Path path) throws IOException {
        if (isDirEmpty(path)) {
            Files.delete(path);
        }
    }

    private boolean isDirEmpty(Path path) throws IOException {
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(path)) {
            return !dirStream.iterator().hasNext();
        }
    }

    private void moveFiles(Path path, User user, PhotoStorage photoStorage, VideoStorage videoStorage) throws IOException {
        Event event = getEvent(path);
        
        try (Stream<Path> filePaths = Files.list(path)) {
            Path photoDir = photoStorage.getMediaDirPath(event, user);
            System.out.println(photoDir);
            Path videoDir = videoStorage.getMediaDirPath(event, user);
            System.out.println(videoDir);
            filePaths.forEach(filePath -> {
                try {
                    if (photoStorage.isSupportedByFileExtension(filePath.getFileName().toString())) {
                        moveFile(filePath, photoDir);
                    } else {
                        moveFile(filePath, videoDir);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(PanasonicMediaOrganizer.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        }
    }

    private Event getEvent(Path path) {
        LocalDate date = RAW_DATE_FORMATTER.parse(getPathName(path), LocalDate::from);
        return new Event("", new FullTimeEventDuration(date, date));
    }

    private void moveFile(Path filePath, Path targetDirPath) throws IOException {
        if (!Files.exists(targetDirPath)) {
            Files.createDirectories(targetDirPath);
        }
        Files.move(filePath, targetDirPath.resolve(filePath.getFileName()));
    }

    private static String getPathName(Path filePath) {
        return filePath.getFileName().toString();
    }
}
