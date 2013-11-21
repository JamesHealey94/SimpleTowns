package com.gmail.jameshealey1994.simpletowns.object;

import org.bukkit.Chunk;

/**
 * Class representing a Chunk belonging to a Town.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class TownChunk {

    /**
     * The x coordinate of the TownChunk.
     * Chunk coordinate, not actual coordinate.
     */
    private final int x;

    /**
     * The z coordinate of the TownChunk.
     * Chunk coordinate, not actual coordinate.
     */
    private final int z;

    /**
     * The name of the world of the TownChunk.
     */
    private final String worldname;

    /**
     * Constructor - Initialises x, z, and worldname.
     *
     * @param x             The x coordinate of the TownChunk
     * @param z             The z coordinate of the TownChunk
     * @param worldname     The name of the world of the TownChunk
     */
    public TownChunk(int x, int z, String worldname) {
        this.x = x;
        this.z = z;
        this.worldname = worldname;
    }

    /**
     * Get the value of worldname.
     *
     * @return the value of worldname
     */
    public String getWorldname() {
        return worldname;
    }

    /**
     * Get the value of x.
     *
     * @return      the value of x
     */
    public int getX() {
        return x;
    }

    /**
     * Get the value of z.
     *
     * @return      the value of z
     */
    public int getZ() {
        return z;
    }

    /**
     * Compares TownChunk to a passed Chunk.
     * If the passed object is a Chunk, the worldname, X, and Z of the chunk are
     * all compared.
     *
     * @param c     Chunk being compared
     * @return      If the passed chunk is equal to the current TownChunk object
     */
    public boolean equalsChunk(Chunk c) {
        return (c.getWorld().getName().equals(getWorldname()) && c.getX() == getX() && c.getZ() == getZ());
    }
}