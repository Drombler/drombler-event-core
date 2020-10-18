package org.drombler.event.core;

import lombok.*;
import org.drombler.event.core.format.YearEventDurationDirNameFormatter;
import org.softsmithy.lib.text.FormatException;

import java.time.Year;

/**
 *
 * @author Florian
 */
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class YearEventDuration implements EventDuration {

    private static final YearEventDurationDirNameFormatter DIR_NAME_FORMATTER = new YearEventDurationDirNameFormatter();

    private final Year year;

    @Override
    public Appendable formatDirName(Appendable appendable) throws FormatException {
        DIR_NAME_FORMATTER.format(this, appendable);
        return appendable;
    }

}
