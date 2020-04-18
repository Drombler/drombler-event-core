package org.drombler.event.core;

import java.io.IOException;
import org.softsmithy.lib.text.FormatException;

/**
 *
 * @author Florian
 */

// not used yet
public class InfiniteEventDuration implements EventDuration{
    private static final InfiniteEventDuration INSTANCE = new InfiniteEventDuration();
    
    private InfiniteEventDuration(){
    }

    private static final InfiniteEventDuration getInstance(){
        return INSTANCE;
    }
    

    @Override
    public Appendable formatDirName(Appendable appendable) throws FormatException {
        try {
            return appendable.append("infinite");
        } catch (IOException ex) {
           throw new FormatException(ex.getMessage(), ex);
        }
    }
    
}
