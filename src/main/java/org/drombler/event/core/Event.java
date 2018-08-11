/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.event.core;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author Florian
 */
public class Event {

    private final UUID id;
    private final String name;
    private final EventDuration duration;

    public Event(UUID id, String name, EventDuration duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public String getDirName() {
        return getDuration().getDirName() + "-" + getName();
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

    public static Optional<Event> fullTimeDayEvent(String dirName) {
        String[] parts = dirName.split("-", 3);
        if (parts.length > 1) {
            Optional<FullTimeEventDuration> duration
                    = (parts.length == 2)
                            ? FullTimeEventDuration.singleDay(parts[0])
                            : FullTimeEventDuration.period(parts[0], parts[1]);
            if (duration.isPresent()) {
                return Optional.of(new Event(UUID.randomUUID(), (parts.length == 2) ? parts[1] : parts[2], duration.get()));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    public UUID getId() {
       return id;
    }
}
