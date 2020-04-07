package org.drombler.media.core.video;

import java.nio.file.Path;
import org.drombler.media.core.*;

/**
 *
 * @author Florian
 */
public class VideoSource extends AbstractMediaSource<VideoStorage>{
    
    public VideoSource(VideoStorage videoStorage, Path mediaPath) {
        super(videoStorage, mediaPath);
    }
    
}
