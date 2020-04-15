package org.drombler.event.core;

import java.time.YearMonth;
import java.util.Objects;
import org.drombler.event.core.format.MonthEventDurationDirNameFormatter;
import org.softsmithy.lib.text.FormatException;

/**
 *
 * @author Florian
 */
public class MonthEventDuration implements EventDuration {

//    private static final String MONTH_APPENDIX = "00";
//    private static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM00");
//    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{6}00");
    private static final MonthEventDurationDirNameFormatter DIR_NAME_FORMATTER = new MonthEventDurationDirNameFormatter();

    private final YearMonth yearMonth;

    public MonthEventDuration(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

//    @Override
//    public String getDirName() {
//        return MONTH_FORMATTER.format(yearMonth);
//    }
    @Override
    public Appendable formatDirName(Appendable appendable) throws FormatException {
        DIR_NAME_FORMATTER.format(this, appendable);
        return appendable;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.yearMonth);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MonthEventDuration)) {
            return false;
        }
        final MonthEventDuration other = (MonthEventDuration) obj;
        return Objects.equals(this.yearMonth, other.yearMonth);
    }

    @Override
    public String toString() {
        return "MonthEventDuration{" + "yearMonth=" + yearMonth + '}';
    }

}
