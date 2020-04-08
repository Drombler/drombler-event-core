/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.media.core.photo;

import java.nio.file.Path;
import org.drombler.media.core.AbstractMediaStorage;

/**
 *
 * @author Florian
 */
public class PhotoStorage extends AbstractMediaStorage<PhotoSource> {

    public PhotoStorage(Path mediaParentDirPath) {
        super(mediaParentDirPath.resolve("photo"), ".jpg", ".png");
    }

    @Override
    protected PhotoSource createMediaSource(Path mediaFileName) {
        return new PhotoSource(this, mediaFileName);
    }

}
