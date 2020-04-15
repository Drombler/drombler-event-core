package org.drombler.media.management.video;

import java.nio.file.Path;
import org.drombler.media.core.video.VideoSource;
import org.drombler.media.core.video.VideoStorage;
import org.drombler.media.management.AbstractMediaStorageManager;
import org.drombler.media.management.config.model.json.MediaStorageConfiguration;

/**
 *
 * @author Florian
 */
public class VideoStorageManager extends AbstractMediaStorageManager<VideoSource, VideoStorage>{

    @Override
    protected VideoStorage createMediaStorage(MediaStorageConfiguration configuration) {
        return new VideoStorage(configuration.getName(), Path.of(configuration.getMediaRootDir()));
    }
    
}
