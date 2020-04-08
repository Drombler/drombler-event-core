package org.drombler.media.management;

import java.util.List;
import org.drombler.media.core.photo.PhotoStorage;

/**
 *
 * @author Florian
 */
public class PhotoStorageManager {

    private final List<PhotoStorage> photoStorages;

    public PhotoStorageManager(List<PhotoStorage> photoStorages){
        this.photoStorages = photoStorages;
    }

}
