/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.event.core;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import org.drombler.identity.core.DromblerId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Florian
 */
public class Event {

    private static final Logger LOG = LoggerFactory.getLogger(Event.class);

    private final UUID id;
    private final String name;
    private final EventDuration duration;
    private final Set<DromblerId> participants = new HashSet<>();
    private final Set<DromblerId> unmodifiableParticipants = Collections.unmodifiableSet(participants);

    public Event(UUID id, String name, EventDuration duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the duration
     */
    public EventDuration getDuration() {
        return duration;
    }

    public boolean addParticipant(DromblerId participant) {
        return participants.add(participant);
    }

    public boolean removeParticipant(DromblerId participant) {
        return participants.remove(participant);
    }

    public Set<DromblerId> getParticipants() {
        return unmodifiableParticipants;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.duration);
        return hash;
    }

    @Override
    public String toString() {
        return "Event{" + "name=" + name + ", duration=" + duration + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }

        final Event other = (Event) obj;
        return Objects.equals(this.name, other.name)
                && Objects.equals(this.duration, other.duration);
    }

    public UUID getId() {
        return id;
    }
}
