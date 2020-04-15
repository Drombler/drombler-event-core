package org.drombler.media.core;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.drombler.media.image.core.ImageMediaCategoryProvider;
import org.drombler.media.photo.core.PhotoMediaCategoryProvider;
import org.drombler.media.video.core.VideoMediaCategoryProvider;

/**
 *
 * @author Florian
 */
public class MediaCategoryManager {

    private final Map<String, MediaCategory> mediaCategoryMap;

    public MediaCategoryManager() {
        this.mediaCategoryMap = Stream.of(new PhotoMediaCategoryProvider(), new VideoMediaCategoryProvider(), new ImageMediaCategoryProvider())
                .map(MediaCategoryProvider::getMediaCategory)
                .collect(Collectors.toMap(MediaCategory::getId, Function.identity()));
    }

    public boolean containsMediaCategory(String mediaCategoryId) {
        return mediaCategoryMap.containsKey(mediaCategoryId);
    }

    public MediaCategory getMediaCategory(String mediaCategoryId) {
        return mediaCategoryMap.get(mediaCategoryId);
    }
}
