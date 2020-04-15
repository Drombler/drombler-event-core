package org.drombler.media.core;

import java.nio.file.Path;
import org.drombler.event.core.Event;
import org.drombler.identity.core.DromblerId;
import org.softsmithy.lib.text.FormatException;

/**
 *
 * @author Florian
 * @param <M>
 */
public interface MediaSource<M extends MediaSource<M>> {

    MediaStorage<M> getMediaStorage();

    Path getFileName();

    default Path getPath() throws FormatException { // TODO: avoid FormatException here?
        return getMediaStorage().resolveMediaEventDirPath(getEvent(), getCopyrightOwner(), false)
                .resolve(getFileName());
    }

    Event getEvent();

    void setEvent(Event event);

    DromblerId getCopyrightOwner();

    void setCopyrightOwner(DromblerId copyrightOwner);
}
