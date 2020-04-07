package org.drombler.media.core;

import java.nio.file.Path;
import org.drombler.event.core.Event;

/**
 *
 * @author Florian
 * @param <S>
 */
public abstract class AbstractMediaSource<S extends MediaStorage> implements MediaSource<S> {

    private final S mediaStorage;
    private final Path mediaPath;
    
    private Event event;

    public AbstractMediaSource(S mediaStorage, Path mediaPath) {
        this.mediaStorage = mediaStorage;
        this.mediaPath = mediaPath;
    }

    @Override
    public Path getMediaPath() {
        return mediaPath;
    }

    @Override
    public S getMediaStorage() {
        return mediaStorage;
    }

    @Override
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}
