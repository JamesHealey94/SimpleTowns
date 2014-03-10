package com.gmail.jameshealey1994.simpletowns.events;

import com.gmail.jameshealey1994.simpletowns.object.Town;
import org.bukkit.Chunk;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Town Unclaim Event.
 * Triggered after a town removes a claim to a chunk.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class TownUnclaimEvent extends Event {

    /**
     * List of event handlers.
     */
    private static final HandlerList handlers = new HandlerList();

    /**
     * The town that just removed a claim to a chunk.
     */
    private final Town town;

    /**
     * The chunk the town previously claimed.
     */
    private final Chunk chunk;

    /**
     * Constructor - Sets the member variables.
     *
     * @param town          town that just removed a claim to a chunk
     * @param chunk         chunk the town previously claimed
     */
    public TownUnclaimEvent(Town town, Chunk chunk) {
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
     * Returns town that just removed a claim to a chunk.
     *
     * @return  the town that just removed a claim to a chunk
     */
    public Town getTown() {
        return town;
    }

    /**
     * Returns the chunk the town previously claimed.
     *
     * @return  chunk the town previously claimed
     */
    public Chunk getChunk() {
        return chunk;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}