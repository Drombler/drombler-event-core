package org.drombler.media.core;

import java.nio.file.Path;
import org.drombler.event.core.Event;
import org.drombler.identity.core.DromblerId;
import org.softsmithy.lib.text.FormatException;

/**
 *
 * @author Florian
 */
public class MediaSource {

    public Path getPath() throws FormatException { // TODO: avoid FormatException here?
        return getMediaStorage().resolveMediaEventDirPath(getEvent(), getCopyrightOwner(), false)
                .resolve(getFileName());
    }

    private final MediaStorage mediaStorage;
    private final Path fileName;

    private Event event;
    private DromblerId copyrightOwner;

    public MediaSource(MediaStorage mediaStorage, Path fileName) {
        this.mediaStorage = mediaStorage;
        this.fileName = fileName;
    }

    public Path getFileName() {
        return fileName;
    }

    public MediaStorage getMediaStorage() {
        return mediaStorage;
    }

    public DromblerId getCopyrightOwner() {
        return copyrightOwner;
    }

    public void setCopyrightOwner(DromblerId copyrightOwner) {
        this.copyrightOwner = copyrightOwner;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
