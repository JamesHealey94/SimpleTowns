package com.gmail.jameshealey1994.simpletowns.events;

import com.gmail.jameshealey1994.simpletowns.object.Town;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Town Create Event.
 * Triggered after a town is created.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class TownCreateEvent extends Event implements Cancellable {

    /**
     * List of event handlers.
     */
    private static final HandlerList handlers = new HandlerList();

    /**
     * The newly created town.
     */
    private final Town town;

    /**
     * The sender of the command.
     * May be an admin or the console.
     */
    private final CommandSender sender;

    /**
     * If the event is cancelled.
     */
    private boolean cancelled = false;

    /**
     * Constructor - Sets the newly created town.
     *
     * @param town  the newly created town
     */
    public TownCreateEvent(Town town, CommandSender sender) {
        this.town = town;
        this.sender = sender;
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
     * Returns the newly created town.
     *
     * @return  the newly created town
     */
    public Town getTown() {
        return town;
    }

    /**
     * Returns the sender of the command.
     *
     * @return  sender of the command
     */
    public CommandSender getSender() {
        return sender;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}