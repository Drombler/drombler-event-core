package org.drombler.media.image.core;

import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.HashSet;
import org.drombler.media.core.MediaCategory;
import org.drombler.media.core.MediaCategoryProvider;
import org.drombler.media.core.MediaCategoryVariant;

/**
 *
 * @author Florian
 */
public class ImageMediaCategoryProvider implements MediaCategoryProvider {

    private final MediaCategory imageMediaCategory = new MediaCategory("image", Arrays.asList(
            new MediaCategoryVariant(new HashSet<>(asList("image/jpeg")), new HashSet<>(Arrays.asList(".jpeg", ".jpg"))),
            new MediaCategoryVariant(new HashSet<>(asList("image/png")), new HashSet<>(Arrays.asList(".png"))),
            new MediaCategoryVariant(new HashSet<>(asList("image/gif")), new HashSet<>(Arrays.asList(".gif")))
    ));

    @Override
    public MediaCategory getMediaCategory() {
        return imageMediaCategory;
    }

}
