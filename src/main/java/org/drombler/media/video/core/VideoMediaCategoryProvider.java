package org.drombler.media.video.core;

import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.Collections;
import java.util.HashSet;
import org.drombler.media.core.MediaCategory;
import org.drombler.media.core.MediaCategoryProvider;
import org.drombler.media.core.MediaCategoryVariant;

/**
 *
 * @author Florian
 */
public class VideoMediaCategoryProvider implements MediaCategoryProvider {

    private final MediaCategory videoMediaCategory = new MediaCategory("video", Arrays.asList(
            new MediaCategoryVariant(new HashSet<>(asList("video/quicktime")), new HashSet<>(Arrays.asList(".qt", ".mov"))),
            new MediaCategoryVariant(new HashSet<>(asList("video/mp4")), new HashSet<>(Arrays.asList(".mp4"))),
            new MediaCategoryVariant(new HashSet<>(asList("video/MP2T")), new HashSet<>(Arrays.asList(".m2ts", ".mts")),
                    Arrays.asList(
                            new MediaCategoryVariant(Collections.emptySet(), new HashSet<>(Arrays.asList(".cont"))),
                            new MediaCategoryVariant(Collections.emptySet(), new HashSet<>(Arrays.asList(".pmpd"))),
                            new MediaCategoryVariant(Collections.emptySet(), new HashSet<>(Arrays.asList(".tmb")))
                    ))
    ));

    @Override
    public MediaCategory getMediaCategory() {
        return videoMediaCategory;
    }

}
