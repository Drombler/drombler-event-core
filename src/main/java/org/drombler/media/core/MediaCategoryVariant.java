package org.drombler.media.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Florian
 */


public final class MediaCategoryVariant {

    private final Set<String> mimeTypes;
    private final Set<String> fileExtensions;
    private final List<MediaCategoryVariant> supplementVariants;

    public MediaCategoryVariant(Set<String> mimeTypes, Set<String> fileExtensions) {
        this(mimeTypes, fileExtensions, Collections.emptyList());
    }
    public MediaCategoryVariant(Set<String> mimeTypes, Set<String> fileExtensions, List<MediaCategoryVariant> supplementVariants) {
        this.mimeTypes = Collections.unmodifiableSet(new HashSet<>(mimeTypes));
        this.fileExtensions = Collections.unmodifiableSet(new HashSet<>(fileExtensions));
        this.supplementVariants = Collections.unmodifiableList(new ArrayList<>(supplementVariants));
    }

    public Set<String> getMimeTypes() {
        return mimeTypes;
    }

    public Set<String> getFileExtensions() {
        return fileExtensions;
    }

    public List<MediaCategoryVariant> getSupplementVariants() {
        return supplementVariants;
    }
    
    
    
}
