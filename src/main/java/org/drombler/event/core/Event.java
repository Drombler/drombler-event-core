/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.event.core;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;
import org.drombler.identity.core.DromblerId;

import java.util.Set;
import java.util.UUID;

/**
 * @author Florian
 */
@JsonPropertyOrder({
        "id",
        "name",
        "duration",
        "preferredDirName",
        "owners",
        "organizers",
        "participants"

})
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Builder
@JsonDeserialize(builder = Event.EventBuilder.class)
public class Event {

    @ToString.Include
    private final UUID id;

    @EqualsAndHashCode.Include
    @ToString.Include
    private final String name;

    @EqualsAndHashCode.Include
    @ToString.Include
    private final EventDuration duration;

    private final String preferredDirName;

    @Singular
    private final Set<DromblerId> owners;

    @Singular
    private final Set<DromblerId> organizers;

    @Singular
    private final Set<DromblerId> participants;

    @JsonPOJOBuilder(withPrefix = "")
    public static class EventBuilder {
    }
}
