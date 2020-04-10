package org.drombler.media.core.photo;

import java.nio.file.Path;
import org.drombler.media.core.*;

/**
 *
 * @author Florian
 */
public class PhotoSource extends AbstractMediaSource<PhotoSource> {

    public PhotoSource(PhotoStorage photoStorage, Path fileName) {
        super(photoStorage, fileName);
    }

    @Override
    public String toString() {
        return "PhotoSource{"
                + "fileName=" + getFileName()
                + "event=" + getEvent()
                + "copyrightOwner=" + getCopyrightOwner()
                + "mediaStorage=" + getMediaStorage()
                + '}';
    }

}
