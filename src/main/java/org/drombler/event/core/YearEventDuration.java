package org.drombler.event.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.drombler.event.core.format.YearEventDurationDirNameFormatter;
import org.softsmithy.lib.text.FormatException;

import java.time.Year;

import static org.drombler.event.core.EventDurationType.YEAR;

/**
 *
 * @author Florian
 */
@JsonPropertyOrder({
        "type",
        "year"
})
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class YearEventDuration extends AbstractEventDuration {

    private static final YearEventDurationDirNameFormatter DIR_NAME_FORMATTER = new YearEventDurationDirNameFormatter();

    private final Year year;

    @JsonCreator
    public YearEventDuration(@JsonProperty("year")Year year) {
        super(YEAR);
        this.year = year;
    }

    @Override
    public Appendable formatDirName(Appendable appendable) throws FormatException {
        DIR_NAME_FORMATTER.format(this, appendable);
        return appendable;
    }

}
