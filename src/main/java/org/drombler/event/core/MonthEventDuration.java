package org.drombler.event.core;

import org.drombler.event.core.format.MonthEventDurationDirNameFormatter;
import org.softsmithy.lib.text.FormatException;

import java.time.YearMonth;

/**
 * @author Florian
 */
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class MonthEventDuration implements EventDuration {

//    private static final String MONTH_APPENDIX = "00";
//    private static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM00");
//    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{6}00");
    private static final MonthEventDurationDirNameFormatter DIR_NAME_FORMATTER = new MonthEventDurationDirNameFormatter();

    private final YearMonth yearMonth;

//    @Override
//    public String getDirName() {
//        return MONTH_FORMATTER.format(yearMonth);
//    }
    @Override
    public Appendable formatDirName(Appendable appendable) throws FormatException {
        DIR_NAME_FORMATTER.format(this, appendable);
        return appendable;
    }

}
