package org.drombler.event.core;

import org.softsmithy.lib.text.FormatException;

/**
 *
 * @author Florian
 */
public interface EventDuration {

    Appendable formatDirName(Appendable appendable) throws FormatException;
    
}
