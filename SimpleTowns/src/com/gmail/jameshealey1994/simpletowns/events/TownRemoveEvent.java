package com.gmail.jameshealey1994.simpletowns.events;

import com.gmail.jameshealey1994.simpletowns.object.Town;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Town Remove Event.
 * Triggered before a town removes a player.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class TownRemoveEvent extends Event implements Cancellable {

    /**
     * List of event handlers.
     */
    private static final HandlerList handlers = new HandlerList();

    /**
     * The town the player is about to be removed from.
     */
    private final Town town;

    /**
     * The sender of the command to remove the player.
     */
    private final CommandSender remover;

    /**
     * The name of the player about to be removed from the town.
     */
    private final String removedName;

    /**
     * If the event is cancelled.
     */
    private boolean cancelled = false;

    /**
     * Constructor - Sets the member variables.
     *
     * @param town          town the player is about to be removed from
     * @param remover       sender of the command to remove the player
     * @param removedName   name of the player about to be removed from the town
     */
    public TownRemoveEvent(Town town, CommandSender remover, String removedName) {
        this.town = town;
        this.remover = remover;
        this.removedName = removedName;
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
     * Returns the town the player is about to be removed from.
     *
     * @return  the town the player is about to be removed from
     */
    public Town getTown() {
        return town;
    }

    /**
     * Returns the sender of the command to remove the player.
     *
     * @return  the sender of the command to remove the player
     */
    public CommandSender getRemover() {
        return remover;
    }

    /**
     * Returns the name of the player about to be removed from the town.
     *
     * @return  the name of the player about to be removed from the town
     */
    public String getRemovedName() {
        return removedName;
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