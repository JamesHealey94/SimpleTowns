package com.gmail.jameshealey1994.simpletowns.events;

import com.gmail.jameshealey1994.simpletowns.object.Town;
import org.bukkit.Chunk;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Town Claim Event.
 * Triggered before a town claims a chunk.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class TownClaimEvent extends Event implements Cancellable {

    /**
     * List of event handlers.
     */
    private static final HandlerList handlers = new HandlerList();

    /**
     * The town that is about to claim a chunk.
     */
    private final Town town;

    /**
     * The chunk the town is about to claim.
     */
    private final Chunk chunk;

    /**
     * If the event is cancelled.
     */
    private boolean cancelled;

    /**
     * Constructor - Sets the member variables.
     *
     * @param town          town that is about to claim a chunk
     * @param chunk         chunk the town is about to claim
     */
    public TownClaimEvent(Town town, Chunk chunk) {
        this.town = town;
        this.chunk = chunk;
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
     * Returns the town that is about to claim a chunk.
     *
     * @return  the town that is about to claim a chunk
     */
    public Town getTown() {
        return town;
    }

    /**
     * Returns chunk the town is about to claim.
     *
     * @return  chunk the town is about to claim
     */
    public Chunk getChunk() {
        return chunk;
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