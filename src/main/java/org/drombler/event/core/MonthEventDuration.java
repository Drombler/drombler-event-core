package org.drombler.event.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.drombler.event.core.format.MonthEventDurationDirNameFormatter;
import org.softsmithy.lib.text.FormatException;

import java.time.YearMonth;

import static org.drombler.event.core.EventDurationType.MONTH;

/**
 *
 * @author Florian
 */
@JsonPropertyOrder({
        "type",
        "yearMonth"
})
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MonthEventDuration extends AbstractEventDuration {

//    private static final String MONTH_APPENDIX = "00";
//    private static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM00");
//    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{6}00");
    private static final MonthEventDurationDirNameFormatter DIR_NAME_FORMATTER = new MonthEventDurationDirNameFormatter();

    private final YearMonth yearMonth;

    @JsonCreator
    public MonthEventDuration(@JsonProperty("yearMonth")YearMonth yearMonth) {
        super(MONTH);
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

}
