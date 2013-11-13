package com.gmail.jameshealey1994.simpletowns;

import java.util.HashSet;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

/**
 * Class representing a Town.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class Town {

    /**
     * The name of the Town.
     */
    private String name;

    /**
     * The leaders of the Town.
     * A Player should only be a citizen or a leader, not both.
     */
    private HashSet<Player> leaders = new HashSet<>();

    /**
     * The citizens of the Town.
     * A Player should only be a citizen or a leader, not both.
     */
    private HashSet<Player> citizens = new HashSet<>();

    /**
     * The chunks belonging to the Town.
     * A chunk should only belong to 1 Town at a time.
     */
    private HashSet<Chunk> chunks;

    /**
     * Constructor - Initialises name, adds creator to leaders, and chunk to
     * chunks.
     *
     * @param name          the name of the Town
     * @param creator       the creator of the Town, normally the player that
     *                      sent the command
     * @param chunk         the first chunk in the town, normally the chunk that
     *                      the creator is standing in when creating the town
     */
    public Town(String name, Player creator, Chunk chunk) {
        this.name = name;
        this.leaders.add(creator);
        this.chunks.add(chunk);
    }

    /**
     * Returns the name of the Town.
     *
     * @return      the name of the Town
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Town.
     *
     * @param name      the new the name of the Town
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the leaders of the Town.
     *
     * @return      the leaders of the Town
     */
    public HashSet<Player> getLeaders() {
        return leaders;
    }

    /**
     * Sets the leaders of the Town.
     *
     * @param leaders       the new leaders of the Town
     */
    public void setLeaders(HashSet<Player> leaders) {
        this.leaders = leaders;
    }

    /**
     * Returns the citizens of the Town.
     * Citizens are members of the Town but not leaders.
     *
     * @return      the citizens of the Town
     */
    public HashSet<Player> getCitizens() {
        return citizens;
    }

    /**
     * Sets the new citizens of the Town.
     *
     * @param citizens      the new citizens of the Town
     */
    public void setCitizens(HashSet<Player> citizens) {
        this.citizens = citizens;
    }

    /**
     * Returns the set of Chunks belonging to the Town.
     *
     * @return      the set of Chunks belonging to the Town
     */
    public HashSet<Chunk> getChunks() {
        return chunks;
    }

    /**
     * Sets the new set of Chunks belonging to the Town.
     *
     * @param chunks        the new set of Chunks belonging to the Town
     */
    public void setChunks(HashSet<Chunk> chunks) {
        this.chunks = chunks;
    }

}