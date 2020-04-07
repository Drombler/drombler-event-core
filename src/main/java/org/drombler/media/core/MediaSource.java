package org.drombler.media.core;

import java.nio.file.Path;
import org.drombler.event.core.Event;

/**
 *
 * @author Florian
 */
public interface MediaSource<S extends MediaStorage>{
    
    S getMediaStorage();
    
    Path getMediaPath();
    
    Event getEvent();
}
