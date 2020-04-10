package org.drombler.event.core.format;

import java.text.ParseException;
import java.time.LocalDate;
import org.drombler.event.core.FullTimeEventDuration;
import static org.drombler.event.core.format.FullTimeEventDurationDirNameFormatter.SINGLE_DAY_FORMATTER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softsmithy.lib.time.format.TemporalAccessorParser;

/**
 *
 * @author Florian
 */
public class FullTimeEventDurationDirNameParser extends AbstractrEventDurationDirNameParser<FullTimeEventDuration> {

    private static final Logger LOG = LoggerFactory.getLogger(FullTimeEventDurationDirNameParser.class);

    private static final TemporalAccessorParser DATE_PARSER = new TemporalAccessorParser(SINGLE_DAY_FORMATTER);

    /**
     * Parses:
     * 
     * yyyyMMdd-<event-dir-name>
     * and
     * yyyyMMdd-yyyyMMdd-<event-dir-name>
     * 
     * @param dirNameParts
     * @return 
     * @throws java.text.ParseException
     */
    @Override
    protected FullTimeEventDuration parseDirName(String[] dirNameParts) throws ParseException{
            try {
                LocalDate startDateInclusive = DATE_PARSER.parseString(dirNameParts[0])
                        .query(LocalDate::from);
                if (dirNameParts.length > 1) {
                    try {
                        LocalDate endDateInclusive = DATE_PARSER.parseString(dirNameParts[1])
                                .query(LocalDate::from);
                        return new FullTimeEventDuration(startDateInclusive, endDateInclusive);
                    } catch (ParseException | RuntimeException ex) {
                        return new FullTimeEventDuration(startDateInclusive, startDateInclusive);
                    }
                }
                return new FullTimeEventDuration(startDateInclusive, startDateInclusive);
            } catch (RuntimeException ex) {
                LOG.debug("Could not parse duration for: " + dirNameParts[0]);
                throw new ParseException("Could not parse duration for: " + dirNameParts[0], 0);
            }
    }

}
