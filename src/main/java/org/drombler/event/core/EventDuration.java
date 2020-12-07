package org.drombler.event.core;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.softsmithy.lib.text.FormatException;

/**
 *
 * @author Florian
 */


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AllDayEventDuration.class, name = "ALL_DAY"),
        @JsonSubTypes.Type(value = MonthEventDuration.class, name = "MONTH"),
        @JsonSubTypes.Type(value = YearEventDuration.class, name = "YEAR"),
        @JsonSubTypes.Type(value = InfiniteEventDuration.class, name = "INFINITE")
})
public interface EventDuration {

    EventDurationType getType();

    Appendable formatDirName(Appendable appendable) throws FormatException;
    
}
