/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.media.core;

import org.drombler.event.core.Event;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.drombler.identity.core.DromblerId;

/**
 *
 * @author Florian
 */
public class MediaStorage {

    private static final String UNCATEGORIZED_DIR_NAME = "uncategorized";

    private final Path mediaRootDir;
    private final Set<String> supportedExtensions;

    protected MediaStorage(Path mediaDir, String... supportedExtensions) {
        this.mediaRootDir = mediaDir;
        this.supportedExtensions = new HashSet<>(Arrays.asList(supportedExtensions));
    }

    public Path getMediaEventDirPath(Event event, DromblerId dromblerId, boolean uncategorized) {
        return resolveMediaEventDirPath(event, uncategorized).resolve(dromblerId.getDromblerIdFormatted());
    }

    private Path resolveMediaEventDirPath(Event event, boolean uncategorized) {
        if (!uncategorized) {
            return getMediaRootDir().resolve(event.getDirName());
        } else {
            return getUncategorizedMediaRootDir().resolve(event.getDirName());
        }
    }

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
    public Path getMediaRootDir() {
        return mediaRootDir;
    }

    public Path getUncategorizedMediaRootDir() {
        return getMediaRootDir().resolve(UNCATEGORIZED_DIR_NAME);
    }
