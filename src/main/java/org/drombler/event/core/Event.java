/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.event.core;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Florian
 */
public class Event {

    private static final Logger LOG = LoggerFactory.getLogger(Event.class);

    private static final Pattern DIR_PATTERN = Pattern.compile("^(\\d{8})-(?:(\\d{8})-)?(.*)");
    private final UUID id;
    private final String name;
    private final EventDuration duration;

    public Event(UUID id, String name, EventDuration duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public String getDirName() {
        return getDuration().getDirName() + "-" + getName().replaceAll("\\W", "-");
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
        Matcher matcher = DIR_PATTERN.matcher(dirName);
        if (matcher.find()) {
            Optional<FullTimeEventDuration> duration
                    = (matcher.group(2) == null)
                    ? FullTimeEventDuration.singleDay(matcher.group(1))
                    : FullTimeEventDuration.period(matcher.group(1), matcher.group(2));
            if (duration.isPresent()) {
                return Optional.of(new Event(UUID.randomUUID(), matcher.group(3).replaceAll("-", " "), duration.get()));
            } else {
                LOG.warn("Could not parse duration for event dir: " + dirName);
                return Optional.empty();
            }
        } else {
            LOG.warn("Could not parse event dir: " + dirName);
            return Optional.empty();
        }
    }

    public UUID getId() {
        return id;
    }
}
