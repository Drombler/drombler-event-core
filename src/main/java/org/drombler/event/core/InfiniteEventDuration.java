package org.drombler.event.core;

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
    public String getDirName() {
        return "infinite";
    }
    
}
