package com.gmail.jameshealey1994.simpletowns.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Town Reload Completed Event.
 * Triggered after towns are reloaded.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class TownAfterReloadEvent extends Event {

    /**
     * List of event handlers.
     */
    private static final HandlerList handlers = new HandlerList();

    /**
     * Returns list of event handlers.
     *
     * @return  list of event handlers
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}