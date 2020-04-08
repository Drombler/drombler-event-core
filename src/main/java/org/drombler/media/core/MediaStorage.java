package org.drombler.media.core;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.drombler.event.core.Event;
import org.drombler.identity.core.DromblerId;
import org.drombler.identity.core.DromblerIdentityProviderManager;

public interface MediaStorage<M extends MediaSource<M>> {

    Path getMediaRootDir();
    
    String getName();
    
    Path getMediaEventDirPath(Event event, DromblerId dromblerId, boolean uncategorized);

    List<M> readMediaSources(DromblerIdentityProviderManager dromblerIdentityProviderManager) throws IOException;

    Path getUncategorizedMediaRootDir();

    boolean isSupportedByFileExtension(String fileName);
} 
