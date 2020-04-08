package org.drombler.media.core;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.drombler.event.core.Event;
import org.drombler.identity.core.DromblerId;
import org.drombler.identity.core.DromblerIdentityProviderManager;

public interface MediaStorage<M extends MediaSource<M>> {

    Path getMediaEventDirPath(Event event, DromblerId dromblerId, boolean uncategorized);

    /**
     * @return the mediaRootDir
     */
    Path getMediaRootDir();

    List<M> getMediaSources(DromblerIdentityProviderManager dromblerIdentityProviderManager) throws IOException;

    Path getUncategorizedMediaRootDir();

    boolean isSupportedByFileExtension(String fileName);
} 
