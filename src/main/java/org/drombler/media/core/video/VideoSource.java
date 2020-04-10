package org.drombler.media.core.video;

import java.nio.file.Path;
import org.drombler.media.core.*;

/**
 *
 * @author Florian
 */
public class VideoSource extends AbstractMediaSource<VideoSource> {

    public VideoSource(VideoStorage videoStorage, Path fileName) {
        super(videoStorage, fileName);
    }

    @Override
    public String toString() {
        return "VideoSource{"
                + "fileName=" + getFileName()
                + "event=" + getEvent()
                + "copyrightOwner=" + getCopyrightOwner()
                + "mediaStorage=" + getMediaStorage()
                + '}';
    }
}
