/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.event.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Florian
 */
public class FullTimeEventDuration implements EventDuration {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");
    
    private final LocalDate startDateInclusive;
    private final LocalDate endDateInclusive;

    public FullTimeEventDuration(LocalDate startDateInclusive, LocalDate endDateInclusive) {
        this.startDateInclusive = startDateInclusive;
        this.endDateInclusive = endDateInclusive;
    }

    @Override
    public String getDirName() {
        return startDateInclusive.equals(endDateInclusive) ? getStartDateDirName() : getStartMonthDirName();
    }

    private String getStartDateDirName() {
        return DATE_FORMATTER.format(startDateInclusive);
    }

    private String getStartMonthDirName() {
        return MONTH_FORMATTER.format(startDateInclusive) + "00";
    }
}
