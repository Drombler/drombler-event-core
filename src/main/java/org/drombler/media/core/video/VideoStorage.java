/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.media.core.video;

import java.nio.file.Path;
import org.drombler.media.core.AbstractMediaStorage;
import org.drombler.media.core.MediaSource;

/**
 *
 * @author Florian
 */
public class VideoStorage extends AbstractMediaStorage<VideoSource> {

    public VideoStorage(Path mediaParentDirPath) {
        super(mediaParentDirPath.resolve("video"));
    }

    @Override
    protected VideoSource createMediaSource(Path mediaFileName) {
        return new VideoSource(this, mediaFileName);
    }
}
