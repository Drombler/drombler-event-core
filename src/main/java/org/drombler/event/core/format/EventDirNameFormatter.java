package org.drombler.event.core.format;

import org.apache.commons.lang3.StringUtils;
import org.drombler.event.core.Event;
import org.softsmithy.lib.text.AbstractFormatter;
import org.softsmithy.lib.text.FormatException;

import java.io.IOException;

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
                    .append(getEventDirName(event));
        } catch (IOException | RuntimeException ex) {
            throw new FormatException(ex.getMessage(), ex);
        }
    }

    private String getEventDirName(Event event) {
        if (StringUtils.isNotBlank(event.getPreferredDirName())) {
            return event.getPreferredDirName();
        } else {
            return event.getName().replaceAll("\\W", NON_WORD_CHARACTER_REPLACEMENT_CHARACTER);  // TODO: support characters of other languages as much as possible
        }
    }

}
