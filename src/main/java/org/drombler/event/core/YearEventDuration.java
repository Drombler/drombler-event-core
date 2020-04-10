package org.drombler.event.core;

import java.time.Year;
import java.util.Objects;
import org.drombler.event.core.format.YearEventDurationDirNameFormatter;
import org.softsmithy.lib.text.FormatException;

/**
 *
 * @author Florian
 */
@Deprecated
public class YearEventDuration implements EventDuration {

    private static final YearEventDurationDirNameFormatter DIR_NAME_FORMATTER = new YearEventDurationDirNameFormatter();

    private final Year year;

    public YearEventDuration(Year year) {
        this.year = year;
    }

    @Override
    public Appendable formatDirName(Appendable appendable) throws FormatException {
        DIR_NAME_FORMATTER.format(this, appendable);
        return appendable;
    }

    public Year getYear() {
        return year;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.year);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof YearEventDuration)) {
            return false;
        }
        final YearEventDuration other = (YearEventDuration) obj;
        return Objects.equals(this.year, other.year);
    }

    @Override
    public String toString() {
        return "YearEventDuration{" + "year=" + year + '}';
    }

}
