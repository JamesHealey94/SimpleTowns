package com.gmail.jameshealey1994.simpletowns.object;

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
     * The y coordinate of the TownChunk.
     * Chunk coordinate, not actual coordinate.
     */
    private final int y;

    /**
     * The name of the world of the TownChunk.
     */
    private final String worldname;

    /**
     * Constructor - Initialises x, y, and worldname.
     *
     * @param x             The x coordinate of the TownChunk
     * @param y             The y coordinate of the TownChunk
     * @param worldname     The name of the world of the TownChunk
     */
    public TownChunk(int x, int y, String worldname) {
        this.x = x;
        this.y = y;
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
     * Get the value of y.
     *
     * @return      the value of y
     */
    public int getY() {
        return y;
    }
}