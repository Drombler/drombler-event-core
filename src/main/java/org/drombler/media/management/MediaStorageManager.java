package org.drombler.media.management;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.drombler.media.core.MediaStorage;

/**
 *
 * @author Florian
 */
public class MediaStorageManager {

    private final List<MediaStorage> mediaStorages = new ArrayList<>();

    public MediaStorageManager() throws IOException {
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(MediaStorageManager.class.getResourceAsStream("media-event-dir-paths.txt"), Charset.forName("UTF-8")))) {
//            br.lines()
//                    .map(Paths::get)
//                    .forEach(this::updateEventMap);
//        }
    }

}
