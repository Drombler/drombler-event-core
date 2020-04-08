package org.drombler.media.management;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import org.drombler.media.core.photo.PhotoStorage;

/**
 *
 * @author Florian
 */
public class PhotoStorageManager {

    private final List<PhotoStorage> mediaStorages;

    public PhotoStorageManager(List<Path> paths) throws IOException {
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(PhotoStorageManager.class.getResourceAsStream("media-event-dir-paths.txt"), Charset.forName("UTF-8")))) {
//            br.lines()
//                    .map(Paths::get)
//                    .forEach(this::updateEventMap);
//        }
        this.mediaStorages = paths.stream()
                .map(PhotoStorage::new)
                .collect(Collectors.toList());

    }

}
