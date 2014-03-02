package com.gmail.jameshealey1994.simpletowns.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Town Delete Event.
 * Triggered after a town is deleted.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class TownDeleteEvent extends Event {

    /**
     * List of event handlers.
     */
    private static final HandlerList handlers = new HandlerList();

    /**
     * Name of the deleted town.
     */
    private final String townName;

    /**
     * Constructor - Sets the name of the deleted town.
     *
     * @param townName name of the deleted town
     */
    public TownDeleteEvent(String townName) {
        this.townName = townName;
    }

    /**
     * Returns list of event handlers.
     *
     * @return  list of event handlers
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Returns the name of the deleted town.
     *
     * @return  name of the deleted town
     */
    public String getTownName() {
        return townName;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}