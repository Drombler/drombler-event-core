package org.drombler.event.core.format;

import java.text.ParseException;
import java.time.LocalDate;
import org.drombler.event.core.AllDayEventDuration;
import static org.drombler.event.core.format.AllDayEventDurationDirNameFormatter.SINGLE_DAY_FORMATTER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softsmithy.lib.time.format.TemporalAccessorParser;

/**
 *
 * @author Florian
 */
public class AllDayEventDurationDirNameParser extends AbstractrEventDurationDirNameParser<AllDayEventDuration> {

    private static final Logger LOG = LoggerFactory.getLogger(AllDayEventDurationDirNameParser.class);

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
    protected AllDayEventDuration parseDirName(String[] dirNameParts) throws ParseException{
            try {
                LocalDate startDateInclusive = DATE_PARSER.parseString(dirNameParts[0])
                        .query(LocalDate::from);
                if (dirNameParts.length > 1) {
                    try {
                        LocalDate endDateInclusive = DATE_PARSER.parseString(dirNameParts[1])
                                .query(LocalDate::from);
                        return new AllDayEventDuration(startDateInclusive, endDateInclusive);
                    } catch (ParseException | RuntimeException ex) {
                        return new AllDayEventDuration(startDateInclusive, startDateInclusive);
                    }
                }
                return new AllDayEventDuration(startDateInclusive, startDateInclusive);
            } catch (RuntimeException ex) {
                LOG.debug("Could not parse duration for: " + dirNameParts[0]);
                throw new ParseException("Could not parse duration for: " + dirNameParts[0], 0);
            }
    }

}
