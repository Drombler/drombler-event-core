/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.event.core;

import java.time.LocalDate;
import java.util.Objects;
import org.drombler.event.core.format.AllDayEventDurationDirNameFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softsmithy.lib.text.FormatException;

/**
 *
 * @author Florian
 */
public class AllDayEventDuration implements EventDuration {

    private static final Logger LOG = LoggerFactory.getLogger(AllDayEventDuration.class);

//    private static final String MONTH_APPENDIX = "00";
//    private static final DateTimeFormatter SINGLE_DAY_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
//    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{8}");
//    private static final String DATE_DELIMITER = "-";
//    private static final TemporalAccessorParser DATE_PARSER = new TemporalAccessorParser(SINGLE_DAY_FORMATTER);
    private static final AllDayEventDurationDirNameFormatter DIR_NAME_FORMATTER = new AllDayEventDurationDirNameFormatter();

    private final LocalDate startDateInclusive;
    private final LocalDate endDateInclusive;

    public AllDayEventDuration(LocalDate startDateInclusive, LocalDate endDateInclusive) {
        this.startDateInclusive = startDateInclusive;
        this.endDateInclusive = endDateInclusive;
    }

    @Override
    public Appendable formatDirName(Appendable appendable) throws FormatException {
        DIR_NAME_FORMATTER.format(this, appendable);
        return appendable;
    }

    public boolean isSingleDay() {
        return getStartDateInclusive().equals(getEndDateInclusive());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.startDateInclusive);
        hash = 53 * hash + Objects.hashCode(this.endDateInclusive);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AllDayEventDuration)) {
            return false;
        }
        final AllDayEventDuration other = (AllDayEventDuration) obj;
        return Objects.equals(this.startDateInclusive, other.startDateInclusive)
                && Objects.equals(this.endDateInclusive, other.endDateInclusive);
    }

    @Override
    public String toString() {
        return "AllDayEventDuration{" + "startDateInclusive=" + startDateInclusive + ", endDateInclusive=" + endDateInclusive + '}';
    }

    /**
     * @return the startDateInclusive
     */
    public LocalDate getStartDateInclusive() {
        return startDateInclusive;
    }

    /**
     * @return the endDateInclusive
     */
    public LocalDate getEndDateInclusive() {
        return endDateInclusive;
    }
}
