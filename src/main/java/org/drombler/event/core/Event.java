/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.event.core;

/**
 *
 * @author Florian
 */
public class Event {
    private final String name;
    private final EventDuration duration;

    public Event(String name, EventDuration duration) {
        this.name = name;
        this.duration = duration;
    }
    
    public String getDirName(){
        return duration.getDirName() + "-" + name;
    }
}
