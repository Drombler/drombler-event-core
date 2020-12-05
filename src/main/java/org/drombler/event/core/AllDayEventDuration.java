/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.event.core;

import org.drombler.event.core.format.AllDayEventDurationDirNameFormatter;
import org.softsmithy.lib.text.FormatException;

import java.time.LocalDate;

/**
 * @author Florian
 */
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class AllDayEventDuration implements EventDuration {

//    private static final String MONTH_APPENDIX = "00";
//    private static final DateTimeFormatter SINGLE_DAY_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
//    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{8}");
//    private static final String DATE_DELIMITER = "-";
//    private static final TemporalAccessorParser DATE_PARSER = new TemporalAccessorParser(SINGLE_DAY_FORMATTER);
    private static final AllDayEventDurationDirNameFormatter DIR_NAME_FORMATTER = new AllDayEventDurationDirNameFormatter();

    private final LocalDate startDateInclusive;
    private final LocalDate endDateInclusive;

    @Override
    public Appendable formatDirName(Appendable appendable) throws FormatException {
        DIR_NAME_FORMATTER.format(this, appendable);
        return appendable;
    }

    public boolean isSingleDay() {
        return getStartDateInclusive().equals(getEndDateInclusive());
    }

}
