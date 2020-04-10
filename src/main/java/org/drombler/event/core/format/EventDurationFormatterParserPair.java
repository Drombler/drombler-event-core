package org.drombler.event.core.format;

import org.drombler.event.core.EventDuration;
import org.softsmithy.lib.text.Formatter;
import org.softsmithy.lib.text.Parser;

/**
 *
 * @author Florian
 * @param <E>
 */
public class EventDurationFormatterParserPair<E extends EventDuration> {

    private final Formatter<E> formatter;
    private final Parser<E> parser;

    public EventDurationFormatterParserPair(Formatter<E> formatter, Parser<E> parser) {
        this.formatter = formatter;
        this.parser = parser;

    }

    public Formatter<E> getFormatter() {
        return formatter;
    }

    public Parser<E> getParser() {
        return parser;
    }

}
