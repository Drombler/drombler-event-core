package org.drombler.event.core.format;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import org.drombler.event.core.FullTimeEventDuration;
import org.softsmithy.lib.text.AbstractFormatter;
import org.softsmithy.lib.text.FormatException;
import org.softsmithy.lib.time.format.TemporalAccessorFormatter;

/**
 *
 * @author Florian
 */
public class FullTimeEventDurationDirNameFormatter extends AbstractFormatter<FullTimeEventDuration> {

    public static final DateTimeFormatter SINGLE_DAY_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final String DATE_DELIMITER = "-";
    private static final TemporalAccessorFormatter DATE_FORMATTER = new TemporalAccessorFormatter(SINGLE_DAY_FORMATTER);

    @Override
    public void format(FullTimeEventDuration fullTimeEventDuration, Appendable appendable) throws FormatException {
        try {
            if (fullTimeEventDuration.isSingleDay()) {
                formatSingleDayDirName(fullTimeEventDuration, appendable);
            } else {
                formatPeriodDirName(fullTimeEventDuration, appendable);
            }
        } catch (IOException | RuntimeException ex) {
            throw new FormatException(ex.getMessage(), ex);
        }
    }

    private void formatSingleDayDirName(FullTimeEventDuration fullTimeEventDuration, Appendable appendable) throws FormatException, IOException {
        appendable.append(DATE_FORMATTER.format(fullTimeEventDuration.getStartDateInclusive()));
    }

    private void formatPeriodDirName(FullTimeEventDuration fullTimeEventDuration, Appendable appendable) throws FormatException, IOException {
        appendable.append(DATE_FORMATTER.format(fullTimeEventDuration.getStartDateInclusive()))
                .append(DATE_DELIMITER)
                .append(DATE_FORMATTER.format(fullTimeEventDuration.getEndDateInclusive()));
    }
}
