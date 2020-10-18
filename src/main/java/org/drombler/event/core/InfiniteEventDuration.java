package org.drombler.event.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.softsmithy.lib.text.FormatException;

import java.io.IOException;

/**
 *
 * @author Florian
 */

// not used yet
@JsonPropertyOrder({
        "type"
})
public class InfiniteEventDuration extends AbstractEventDuration{
    private static final InfiniteEventDuration INSTANCE = new InfiniteEventDuration();
    
    private InfiniteEventDuration(){
        super(EventDurationType.INFINITE);
    }

    @JsonCreator
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
