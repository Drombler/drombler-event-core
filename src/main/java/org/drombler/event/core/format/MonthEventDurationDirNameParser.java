package org.drombler.event.core.format;

import java.text.ParseException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.drombler.event.core.MonthEventDuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softsmithy.lib.time.format.TemporalAccessorParser;

/**
 * Supports legacy dir names: yyyyMM00-<event-dir-name>
 *
 * @author Florian
 */
public class MonthEventDurationDirNameParser extends AbstractrEventDurationDirNameParser<MonthEventDuration> {

    private static final Logger LOG = LoggerFactory.getLogger(MonthEventDurationDirNameParser.class);

    // see bug: https://stackoverflow.com/questions/39150071/datetimeformatter-pattern-with-liternal-and-no-separator-does-not-work
    // TODO: remove regex Pattern/ Matcher once migrated to Java 11
    // private static final TemporalAccessorParser YEAR_MONTH_PARSER = new TemporalAccessorParser(MONTH_FORMATTER);
    private static final TemporalAccessorParser YEAR_MONTH_PARSER = new TemporalAccessorParser(DateTimeFormatter.ofPattern("yyyyMM"));
    private static final Pattern DATE_PATTERN = Pattern.compile("(\\d{6})00");

    @Override
    protected MonthEventDuration parseDirName(String[] dirNameParts) throws ParseException {
        try {
            Matcher matcher = DATE_PATTERN.matcher(dirNameParts[0]);
            if (matcher.find()) {
                YearMonth yearMonth = YEAR_MONTH_PARSER.parse(matcher.group(1))
                        .query(YearMonth::from);
                return new MonthEventDuration(yearMonth);
            } else {
                throw new ParseException("Could not parse duration for: " + dirNameParts[0], 0);
            }
        } catch (RuntimeException ex) {
            LOG.debug("Could not parse duration for: " + dirNameParts[0]);
            throw new ParseException("Could not parse duration for: " + dirNameParts[0], 0);
        }
    }

}
