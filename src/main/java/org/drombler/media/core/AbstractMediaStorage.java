/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.media.core;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.drombler.event.core.Event;
import org.drombler.event.core.format.EventDirNameFormatter;
import org.drombler.event.core.format.EventDirNameParser;
import org.drombler.identity.core.DromblerId;
import org.drombler.identity.core.DromblerIdentityProviderManager;
import org.drombler.identity.core.DromblerUserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softsmithy.lib.text.FormatException;

/**
 *
 * @author Florian
 * @param <M>
 */
public abstract class AbstractMediaStorage<M extends MediaSource<M>> implements MediaStorage<M> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMediaStorage.class);

    private static final String UNCATEGORIZED_DIR_NAME = "uncategorized"; // used before imports (only?), e.g. D:/hd-writer-ae-tmp/video/uncategorized

    private final Path mediaRootDir;
    private final Set<String> supportedExtensions;
    private final String name;

    protected AbstractMediaStorage(String name, Path mediaRootDir, String... supportedExtensions) {
        this.name = name;
        this.mediaRootDir = mediaRootDir;
        this.supportedExtensions = new HashSet<>(Arrays.asList(supportedExtensions));
        if (!Files.exists(mediaRootDir) || !Files.isDirectory(mediaRootDir)) {
            throw new IllegalArgumentException("Not a valid directory: " + mediaRootDir);
        }
    }

    @Override
    public Path resolveMediaEventDirPath(Event event, DromblerId dromblerId, boolean uncategorized) throws FormatException {
        Path mediaRootDirPath = getMediaRootDirPath(uncategorized);
        return resolveMediaEventDirPath(mediaRootDirPath, event, dromblerId);
    }

    private Path resolveMediaEventDirPath(Path mediaRootDirPath, Event event, DromblerId dromblerId) throws FormatException {
        EventDirNameFormatter formatter = new EventDirNameFormatter();
        return mediaRootDirPath.resolve(formatter.format(event)).resolve(dromblerId.getDromblerIdFormatted());
    }

    private Path getMediaRootDirPath(boolean uncategorized) {
        if (!uncategorized) {
            return getMediaRootDir();
        } else {
            return getUncategorizedMediaRootDir();
        }
    }

    @Override
    public boolean isSupportedByFileExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index >= 0) {
            String fileExtension = fileName.substring(index).toLowerCase();
            return supportedExtensions.contains(fileExtension);
        } else {
            return false;
        }

    }

    /**
     * @return the mediaRootDir
     */
    @Override
    public Path getMediaRootDir() {
        return mediaRootDir;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Path getUncategorizedMediaRootDir() {
        return getMediaRootDir().resolve(UNCATEGORIZED_DIR_NAME);
    }

    @Override
    public List<M> readMediaSources(DromblerIdentityProviderManager dromblerIdentityProviderManager) throws IOException {
        List<M> mediaSources = new ArrayList<>();
        try ( DirectoryStream<Path> eventDirPathStream = Files.newDirectoryStream(getMediaRootDir())) {
            for (Path eventDirPath : eventDirPathStream) {
                addMediaSources(mediaSources, eventDirPath, dromblerIdentityProviderManager);
            }
        }
        return mediaSources;
    }

    private void addMediaSources(List<M> mediaSources, Path eventDirPath, DromblerIdentityProviderManager dromblerIdentityProviderManager) throws IOException {
        if (Files.isDirectory(eventDirPath)) {
            try {
                Event event = parseEvent(eventDirPath.getFileName().toString());
                try ( DirectoryStream<Path> eventCopyrightOwnerDirPathStream = Files.newDirectoryStream(eventDirPath)) {
                    boolean noCopyrightOwnerWarningLogged = false;
                    for (Path eventCopyrightOwnerDirPath : eventCopyrightOwnerDirPathStream) {
                        if (Files.isDirectory(eventCopyrightOwnerDirPath)) {
                            addMediaSources(mediaSources, event, eventCopyrightOwnerDirPath, dromblerIdentityProviderManager);
                        } else {
                            if (!noCopyrightOwnerWarningLogged) {
                                LOGGER.warn("Some media source have no assigned copyright owner in eventDirPath: " + eventDirPath);
                                // log only once
                                noCopyrightOwnerWarningLogged = true;
                            }
                            Path mediaSourcePath = eventCopyrightOwnerDirPath;
                            addMediaSource(mediaSources, event, null, mediaSourcePath);
                        }
                    }
                }
            } catch (ParseException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        } else {
            LOGGER.warn("Event directory expected: " + eventDirPath);
        }
    }

    private void addMediaSources(List<M> mediaSources, Event event, Path eventCopyrightOwnerDirPath, DromblerIdentityProviderManager dromblerIdentityProviderManager) throws IOException {
        DromblerUserId copyrightOwner = parseCopyrightOwner(eventCopyrightOwnerDirPath.getFileName().toString(), dromblerIdentityProviderManager);
        try ( DirectoryStream<Path> mediaSourcePathStream = Files.newDirectoryStream(eventCopyrightOwnerDirPath)) {
            for (Path mediaSourcePath : mediaSourcePathStream) {
                addMediaSource(mediaSources, event, copyrightOwner, mediaSourcePath);
            }
        }
    }

    private void addMediaSource(List<M> mediaSources, Event event, DromblerId copyrightOwner, Path mediaSourcePath) {
        final Path fileName = mediaSourcePath.getFileName();
        M mediaSource = createMediaSource(event, copyrightOwner, fileName);
        if (mediaSource != null) {
            mediaSources.add(mediaSource);
        }
    }

    private M createMediaSource(Event event, DromblerId copyrightOwner, final Path mediaFileName) {
        try {
            M mediaSource = createMediaSource(mediaFileName);
            mediaSource.setEvent(event);
            mediaSource.setCopyrightOwner(copyrightOwner);
            return mediaSource;
        } catch (RuntimeException ex) {
            LOGGER.error("Unexpected error for event={}, copyrightOwner={}, photoFileName={}: {}", event, copyrightOwner, mediaFileName, ex.getMessage());
            return null;
        }
    }

    private DromblerUserId parseCopyrightOwner(final String copyrightOwnerDirName, DromblerIdentityProviderManager dromblerIdentityProviderManager) {
        return copyrightOwnerDirName != null
                ? DromblerUserId.parseDromblerUserId(copyrightOwnerDirName, dromblerIdentityProviderManager)
                : null;
    }

    private Event parseEvent(final String eventDirName) throws ParseException {
        EventDirNameParser parser = new EventDirNameParser();
        return parser.parse(eventDirName);
    }

    protected abstract M createMediaSource(final Path mediaFileName);

}
