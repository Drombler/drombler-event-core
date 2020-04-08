package org.drombler.media.core.video;

import java.nio.file.Path;
import org.drombler.media.core.*;

/**
 *
 * @author Florian
 */
public class VideoSource extends AbstractMediaSource<VideoSource>{
    
    public VideoSource(VideoStorage videoStorage, Path mediaPath) {
        super(videoStorage, mediaPath);
    }
    
}
