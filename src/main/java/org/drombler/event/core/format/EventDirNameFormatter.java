package org.drombler.event.core.format;

import java.io.IOException;
import org.drombler.event.core.Event;
import org.softsmithy.lib.text.AbstractFormatter;
import org.softsmithy.lib.text.FormatException;

/**
 *
 * @author Florian
 */
public class EventDirNameFormatter extends AbstractFormatter<Event> {

    public static final String EVENT_DURATION_DELIMITER = "-";
    public static final String NON_WORD_CHARACTER_REPLACEMENT_CHARACTER = "-";

    @Override
    public void format(Event event, Appendable appendable) throws FormatException {
        try {
            event.getDuration().formatDirName(appendable)
                    .append(EVENT_DURATION_DELIMITER)
                    .append(event.getName().replaceAll("\\W", NON_WORD_CHARACTER_REPLACEMENT_CHARACTER)); // TODO: support characters of other languages as much as possible
        } catch (IOException | RuntimeException ex) {
            throw new FormatException(ex.getMessage(), ex);
        }
    }

}
