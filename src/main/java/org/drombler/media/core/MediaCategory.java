package org.drombler.media.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Florian
 */
// not used yet
public final class MediaCategory {

    private final String id;

    private final List<MediaCategoryVariant> variants;

    public MediaCategory(String id, List<MediaCategoryVariant> variants) {
        this.id = id;
        this.variants = Collections.unmodifiableList(new ArrayList<>(variants));
    }

    public String getId() {
        return id;
    }

    public List<MediaCategoryVariant> getVariants() {
        return variants;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaCategory)) {
            return false;
        }
        MediaCategory other = (MediaCategory) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "MediaCategory{" + "id=" + id + '}';
    }
}
