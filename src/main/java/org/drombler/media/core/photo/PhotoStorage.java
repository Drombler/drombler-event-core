/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.media.core.photo;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.drombler.event.core.Event;
import org.drombler.identity.core.DromblerIdentityProviderManager;
import org.drombler.identity.core.DromblerUserId;
import org.drombler.media.core.AbstractMediaOrganizer;
import org.drombler.media.core.AbstractMediaStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
