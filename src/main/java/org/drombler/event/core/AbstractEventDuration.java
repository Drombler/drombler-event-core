package org.drombler.event.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AbstractEventDuration implements EventDuration {
    private final EventDurationType type;
}
