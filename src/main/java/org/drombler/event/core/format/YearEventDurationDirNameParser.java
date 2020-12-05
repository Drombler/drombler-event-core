package org.drombler.event.core.format;

import org.drombler.event.core.YearEventDuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softsmithy.lib.time.format.TemporalAccessorParser;

import java.text.ParseException;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Supports legacy dir names: yyyy-<event-dir-name>
 *
 * @author Florian
 */
public class YearEventDurationDirNameParser extends AbstractEventDurationDirNameParser<YearEventDuration> {

    private static final Logger LOG = LoggerFactory.getLogger(YearEventDurationDirNameParser.class);

    // see bug: https://stackoverflow.com/questions/39150071/datetimeformatter-pattern-with-liternal-and-no-separator-does-not-work
    // TODO: remove regex Pattern/ Matcher once migrated to Java 11
    // private static final TemporalAccessorParser YEAR_PARSER = new TemporalAccessorParser(YEAR_FORMATTER);
    private static final TemporalAccessorParser YEAR_PARSER = new TemporalAccessorParser(DateTimeFormatter.ofPattern("yyyy"));
    private static final Pattern DATE_PATTERN = Pattern.compile("(\\d{4})0000");

    @Override
    protected YearEventDuration parseDirName(String[] dirNameParts) throws ParseException {
        try {
            Matcher matcher = DATE_PATTERN.matcher(dirNameParts[0]);
            if (matcher.find()) {
                Year year = YEAR_PARSER.parse(matcher.group(1))
                        .query(Year::from);
                return new YearEventDuration(year);
            } else {
                throw new ParseException("Could not parse duration for: " + dirNameParts[0], 0);
            }
        } catch (RuntimeException ex) {
            LOG.debug("Could not parse duration for: " + dirNameParts[0]);
            throw new ParseException(dirNameParts[0], 0);
        }
    }
}
