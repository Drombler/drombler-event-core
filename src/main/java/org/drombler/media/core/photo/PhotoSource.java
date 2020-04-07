package org.drombler.media.core.photo;

import java.nio.file.Path;
import org.drombler.media.core.*;

/**
 *
 * @author Florian
 */
public class PhotoSource extends AbstractMediaSource<PhotoStorage>{
    
    public PhotoSource(PhotoStorage photoStorage, Path mediaPath) {
        super(photoStorage, mediaPath);
    }
    
}
