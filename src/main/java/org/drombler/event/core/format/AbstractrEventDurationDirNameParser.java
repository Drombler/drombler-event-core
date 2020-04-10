package org.drombler.event.core.format;

import java.text.ParseException;
import org.drombler.event.core.EventDuration;
import static org.drombler.event.core.format.EventDirNameFormatter.EVENT_DURATION_DELIMITER;
import org.softsmithy.lib.text.AbstractParser;

public abstract class AbstractrEventDurationDirNameParser<E extends EventDuration> extends AbstractParser<E> {

    @Override
    protected E parseString(String dirName) throws ParseException {
        String[] dirNameParts = dirName.split(EVENT_DURATION_DELIMITER);
        if (dirNameParts.length > 0) {
            return parseDirName(dirNameParts);
        } else {
            throw new ParseException(dirName, 0);
        }
    }

    protected abstract E parseDirName(String[] dirNameParts) throws ParseException;
}
