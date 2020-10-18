package org.drombler.event.core.format;

import org.drombler.event.core.Event;
import org.drombler.event.core.EventDuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softsmithy.lib.text.AbstractParser;
import org.softsmithy.lib.text.FormatException;
import org.softsmithy.lib.text.Formatter;
import org.softsmithy.lib.text.Parser;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.drombler.event.core.format.EventDirNameFormatter.EVENT_DURATION_DELIMITER;
import static org.drombler.event.core.format.EventDirNameFormatter.NON_WORD_CHARACTER_REPLACEMENT_CHARACTER;

/**
 *
 * @author Florian
 */
public class EventDirNameParser extends AbstractParser<Event> {

    private static final Logger LOG = LoggerFactory.getLogger(EventDirNameParser.class);

    private static final List<EventDurationFormatterParserPair<?>> KNOWN_EVENT_DURATION_DIR_NAME_FORMATTER_PARSERS = Arrays.asList(
            new EventDurationFormatterParserPair<>(new AllDayEventDurationDirNameFormatter(), new AllDayEventDurationDirNameParser()),
            new EventDurationFormatterParserPair<>(new MonthEventDurationDirNameFormatter(), new MonthEventDurationDirNameParser()),
            new EventDurationFormatterParserPair<>(new YearEventDurationDirNameFormatter(), new YearEventDurationDirNameParser())
    );

    @Override
    protected Event parseString(String dirName) throws ParseException {
        for (EventDurationFormatterParserPair<?> pair : KNOWN_EVENT_DURATION_DIR_NAME_FORMATTER_PARSERS) {
            try {
                return parseDirName(pair, dirName);
            } catch (ParseException ex) {
                LOG.debug("EventDuration could not be parsed by: " + pair.getParser().getClass().getSimpleName());
                // ignore, try next pair
            }
        }
        throw new ParseException("Could not parse event dir: " + dirName, 0);
    }

    private <E extends EventDuration> Event parseDirName(EventDurationFormatterParserPair<E> pair, String dirName) throws ParseException {
        try {
            Parser<E> parser = pair.getParser();
            E eventDuration = parser.parse(dirName);

            Formatter<E> formatter = pair.getFormatter();
            StringBuilder sb = new StringBuilder();
            formatter.format(eventDuration, sb);
            String prefix = sb.append(EVENT_DURATION_DELIMITER)
                    .toString();

            String eventNameDirName = dirName.length() > prefix.length() ? dirName.substring(prefix.length()) : "";
            String eventName = eventNameDirName.replaceAll(NON_WORD_CHARACTER_REPLACEMENT_CHARACTER, " ");
            return Event.builder()
                    .name(eventName)
                    .duration(eventDuration)
                    .preferredDirName(eventNameDirName)
                    .build();
        } catch (FormatException | RuntimeException ex) {
            LOG.error("EventDuration formatter did not match parser for dirName: " + dirName, ex);
            throw new ParseException(dirName, 0);
        }
    }
}
