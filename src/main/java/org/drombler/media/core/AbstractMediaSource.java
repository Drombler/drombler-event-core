package org.drombler.media.core;

import java.nio.file.Path;
import org.drombler.event.core.Event;
import org.drombler.identity.core.DromblerId;

/**
 *
 * @author Florian
 * @param <M>
 */
public abstract class AbstractMediaSource<M extends MediaSource<M>> implements MediaSource<M> {

    private final MediaStorage<M> mediaStorage;
    private final Path fileName;
    
    private Event event;
    private DromblerId copyrightOwner;

    public AbstractMediaSource(MediaStorage<M> mediaStorage, Path fileName) {
        this.mediaStorage = mediaStorage;
        this.fileName = fileName;
    }

    @Override
    public Path getFileName() {
        return fileName;
    }

    @Override
    public MediaStorage<M> getMediaStorage() {
        return mediaStorage;
    }

    @Override
    public DromblerId getCopyrightOwner() {
        return copyrightOwner;
    }

    public void setCopyrightOwner(DromblerId copyrightOwner) {
        this.copyrightOwner = copyrightOwner;
    }

    @Override
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}
