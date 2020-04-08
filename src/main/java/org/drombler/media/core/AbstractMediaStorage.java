/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.media.core;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import org.drombler.event.core.Event;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.drombler.identity.core.DromblerId;
import org.drombler.identity.core.DromblerIdentityProviderManager;
import org.drombler.identity.core.DromblerUserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    protected AbstractMediaStorage(Path mediaDir, String... supportedExtensions) {
        this.mediaRootDir = mediaDir;
        this.supportedExtensions = new HashSet<>(Arrays.asList(supportedExtensions));
    }

    @Override
    public Path getMediaEventDirPath(Event event, DromblerId dromblerId, boolean uncategorized) {
        Path mediaRootDirPath = getMediaRootDirPath(uncategorized);
        return resolveMediaEventDirPath(mediaRootDirPath, event, dromblerId);
    }

    private Path resolveMediaEventDirPath(Path mediaRootDirPath, Event event, DromblerId dromblerId) {
        return mediaRootDirPath.resolve(event.getDirName()).resolve(dromblerId.getDromblerIdFormatted());
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
    public Path getUncategorizedMediaRootDir() {
        return getMediaRootDir().resolve(UNCATEGORIZED_DIR_NAME);
    }

    @Override
    public List<M> getMediaSources(DromblerIdentityProviderManager dromblerIdentityProviderManager) throws IOException {
        List<M> mediaSources = new ArrayList<>();
        try (DirectoryStream<Path> eventDirPathStream = Files.newDirectoryStream(getMediaRootDir())) {
            for (Path eventDirPath : eventDirPathStream) {
                addMediaSources(mediaSources, eventDirPath, dromblerIdentityProviderManager);
            }
        }
        return mediaSources;
    }

    private void addMediaSources(List<M> mediaSources, Path eventDirPath, DromblerIdentityProviderManager dromblerIdentityProviderManager) throws IOException {
        try (DirectoryStream<Path> eventCopyrightOwnerDirPathStream = Files.newDirectoryStream(eventDirPath)) {
            for (Path eventCopyrightOwnerDirPath : eventCopyrightOwnerDirPathStream) {
                addMediaSources(mediaSources, eventDirPath, eventCopyrightOwnerDirPath, dromblerIdentityProviderManager);
            }
        }
    }

    private void addMediaSources(List<M> mediaSources, Path eventDirPath, Path eventCopyrightOwnerDirPath, DromblerIdentityProviderManager dromblerIdentityProviderManager) throws IOException {
        try (DirectoryStream<Path> mediaSourcePathStream = Files.newDirectoryStream(eventCopyrightOwnerDirPath)) {
            for (Path mediaSourcePath : mediaSourcePathStream) {
                addMediaSource(mediaSources, eventDirPath, eventCopyrightOwnerDirPath, mediaSourcePath, dromblerIdentityProviderManager);
            }
        }
    }

    private void addMediaSource(List<M> mediaSources, Path eventDirPath, Path eventCopyrightOwnerDirPath, Path mediaSourcePath, DromblerIdentityProviderManager dromblerIdentityProviderManager) {
        final Path fileName = mediaSourcePath.getFileName();
        final Path eventDirName = eventDirPath.getFileName();
        final Path copyrightOwnerDirName = eventCopyrightOwnerDirPath.getFileName();
        M photoSource = createMediaSource(eventDirName, copyrightOwnerDirName, fileName, dromblerIdentityProviderManager);
        mediaSources.add(photoSource);
    }

    private M createMediaSource(final Path eventDirName, final Path copyrightOwnerDirName, final Path mediaFileName, DromblerIdentityProviderManager dromblerIdentityProviderManager) {
        try {
            final Event event = Event.fullTimeDayEvent(eventDirName.toString()).orElse(null);
            final DromblerUserId copyrightOwner = DromblerUserId.parseDromblerUserId(copyrightOwnerDirName.toString(), dromblerIdentityProviderManager);
            M mediaSource = createMediaSource(mediaFileName);
            mediaSource.setEvent(event);
            mediaSource.setCopyrightOwner(copyrightOwner);
            return mediaSource;
        } catch (RuntimeException ex) {
            LOGGER.error("Unexpected error for eventDirName={}, copyrightOwnerDirName={}, photoFileName={}: {}", eventDirName, copyrightOwnerDirName, mediaFileName, ex.getMessage());
            return null;
        }
    }

    protected abstract M createMediaSource(final Path mediaFileName);

}
