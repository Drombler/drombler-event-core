package org.drombler.media.management.photo;

import java.nio.file.Path;
import org.drombler.media.core.photo.PhotoSource;
import org.drombler.media.core.photo.PhotoStorage;
import org.drombler.media.management.AbstractMediaStorageManager;
import org.drombler.media.management.config.model.json.MediaStorageConfiguration;

/**
 *
 * @author Florian
 */
public class PhotoStorageManager extends AbstractMediaStorageManager<PhotoSource, PhotoStorage>{

    @Override
    protected PhotoStorage createMediaStorage(MediaStorageConfiguration configuration) {
        return new PhotoStorage(configuration.getName(), Path.of(configuration.getMediaRootDir()));
    }
    
}
