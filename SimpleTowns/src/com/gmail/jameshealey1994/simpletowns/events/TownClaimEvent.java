package com.gmail.jameshealey1994.simpletowns.events;

import com.gmail.jameshealey1994.simpletowns.object.Town;
import org.bukkit.Chunk;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Town Claim Event.
 * Triggered after a town claims a chunk.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class TownClaimEvent extends Event {

    /**
     * List of event handlers.
     */
    private static final HandlerList handlers = new HandlerList();

    /**
     * The town that just claimed a chunk.
     */
    private final Town town;

    /**
     * The chunk the town just claimed.
     */
    private final Chunk chunk;

    /**
     * Constructor - Sets the member variables.
     *
     * @param town          the town that claimed a chunk
     * @param chunk         the chunk the town just claimed
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
     * Returns the town that just claimed a chunk.
     *
     * @return  the town that just claimed a chunk
     */
    public Town getTown() {
        return town;
    }

    /**
     * Returns the chunk the town just claimed.
     *
     * @return  chunk the town just claimed
     */
    public Chunk getChunk() {
        return chunk;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}