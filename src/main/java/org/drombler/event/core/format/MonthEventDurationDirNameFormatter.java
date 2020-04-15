package org.drombler.event.core.format;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import org.drombler.event.core.MonthEventDuration;
import org.softsmithy.lib.text.AbstractFormatter;
import org.softsmithy.lib.text.FormatException;
import org.softsmithy.lib.time.format.TemporalAccessorFormatter;

/**
 * Supports legacy dir names: yyyyMM00-<event-dir-name>
 *
 * @author Florian
 */
public class MonthEventDurationDirNameFormatter extends AbstractFormatter<MonthEventDuration> {

    public static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM'00'");
    private static final TemporalAccessorFormatter TEMPORAL_ACCESSOR_FORMATTER = new TemporalAccessorFormatter(MONTH_FORMATTER);

    @Override
    public void format(MonthEventDuration eventDuration, Appendable appendable) throws FormatException {
        try {
            appendable.append(TEMPORAL_ACCESSOR_FORMATTER.format(eventDuration.getYearMonth()));
        } catch (IOException ex) {
            throw new FormatException(ex.getMessage(), ex);
        }
    }

}
