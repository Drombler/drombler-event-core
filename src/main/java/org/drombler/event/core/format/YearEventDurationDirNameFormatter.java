package org.drombler.event.core.format;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import org.drombler.event.core.YearEventDuration;
import org.softsmithy.lib.text.AbstractFormatter;
import org.softsmithy.lib.text.FormatException;
import org.softsmithy.lib.time.format.TemporalAccessorFormatter;

/**
 * Supports legacy dir names: yyyy-<event-dir-name>
 *
 * @author Florian
 */
@Deprecated
public class YearEventDurationDirNameFormatter extends AbstractFormatter<YearEventDuration> {

    public static final DateTimeFormatter YEAR_FORMATTER = DateTimeFormatter.ofPattern("yyyy'0000'");
    private static final TemporalAccessorFormatter TEMPORAL_ACCESSOR_FORMATTER = new TemporalAccessorFormatter(YEAR_FORMATTER);

    @Override
    public void format(YearEventDuration eventDuration, Appendable appendable) throws FormatException {
        try {
            appendable.append(TEMPORAL_ACCESSOR_FORMATTER.format(eventDuration.getYear()));
        } catch (IOException ex) {
            throw new FormatException(ex.getMessage(), ex);
        }
    }

}
