/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.event.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.drombler.event.core.format.AllDayEventDurationDirNameFormatter;
import org.softsmithy.lib.text.FormatException;

import java.time.LocalDate;

import static org.drombler.event.core.EventDurationType.ALL_DAY;

/**
 * @author Florian
 */
@JsonPropertyOrder({
        "type",
        "startDateInclusive",
        "endDateInclusive"
})
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AllDayEventDuration extends AbstractEventDuration {

//    private static final String MONTH_APPENDIX = "00";
//    private static final DateTimeFormatter SINGLE_DAY_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
//    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{8}");
//    private static final String DATE_DELIMITER = "-";
//    private static final TemporalAccessorParser DATE_PARSER = new TemporalAccessorParser(SINGLE_DAY_FORMATTER);
    private static final AllDayEventDurationDirNameFormatter DIR_NAME_FORMATTER = new AllDayEventDurationDirNameFormatter();

    private final LocalDate startDateInclusive;
    private final LocalDate endDateInclusive;

    @JsonCreator
    public AllDayEventDuration(@JsonProperty("startDateInclusive") LocalDate startDateInclusive, @JsonProperty("endDateInclusive")  LocalDate endDateInclusive) {
        super(ALL_DAY);
        this.startDateInclusive = startDateInclusive;
        this.endDateInclusive = endDateInclusive;
    }

    @Override
    public Appendable formatDirName(Appendable appendable) throws FormatException {
        DIR_NAME_FORMATTER.format(this, appendable);
        return appendable;
    }

    @JsonIgnore
    public boolean isSingleDay() {
        return getStartDateInclusive().equals(getEndDateInclusive());
    }

}
