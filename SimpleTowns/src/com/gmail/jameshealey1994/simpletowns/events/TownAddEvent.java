package com.gmail.jameshealey1994.simpletowns.events;

import com.gmail.jameshealey1994.simpletowns.object.Town;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Town Add Event.
 * Triggered before a town adds a player.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class TownAddEvent extends Event implements Cancellable {

    /**
     * List of event handlers.
     */
    private static final HandlerList handlers = new HandlerList();

    /**
     * The town the player is about to be added to.
     */
    private final Town town;

    /**
     * The sender of the command.
     * May be an admin or the console.
     */
    private final CommandSender sender;

    /**
     * The name of the player about to be added to the town.
     */
    private final String addedName;

    /**
     * If the event is cancelled.
     */
    private boolean cancelled = false;

    /**
     * Constructor - Sets the member variables.
     *
     * @param town          town the player is about to be added to
     * @param sender        sender of the command to add the player
     * @param addedName     name of the player about to be added to the town
     */
    public TownAddEvent(Town town, CommandSender sender, String addedName) {
        this.town = town;
        this.sender = sender;
        this.addedName = addedName;
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
     * Returns the town the player is about to be added to.
     *
     * @return  the town the player is about to be added to
     */
    public Town getTown() {
        return town;
    }

    /**
     * Returns the sender of the command to add the player.
     * May be an admin or the console.
     *
     * @return  the sender of the command to add the player
     */
    public CommandSender getSender() {
        return sender;
    }

    /**
     * Returns the name of the player about to be added to the town.
     *
     * @return  the name of the player about to be added to the town
     */
    public String getAddedName() {
        return addedName;
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