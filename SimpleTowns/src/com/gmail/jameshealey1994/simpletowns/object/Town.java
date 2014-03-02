package com.gmail.jameshealey1994.simpletowns.object;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.bukkit.Location;

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
     * The usernames of the leaders of the Town.
     * A Player should only be a citizen or a leader, not both.
     */
    private Set<String> leaders = new HashSet<>();

    /**
     * The usernames of the citizens of the Town.
     * A Player should only be a citizen or a leader, not both.
     */
    private Set<String> citizens = new HashSet<>();
    
    /**
     * The home location of the Town.
     */
    private Location home;
    
    /**
     * The TownChunks belonging to the Town.
     * A chunk should only belong to 1 Town at a time.
     */
    private Set<TownChunk> chunks = new HashSet<>();

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
    public Town(String name, String creator, TownChunk chunk) {
        this.name = name;
        this.leaders.add(creator);
        this.chunks.add(chunk);
    }

    /**
     * Constructor - Initialises all fields.
     *
     * @param name          the name of the Town
     * @param leaders       the leaders of the Town
     * @param citizens      the citizens of the Town
     * @param chunks        the TownChunks of the Town
     */
    public Town(String name, Set<String> leaders, Set<String> citizens, Set<TownChunk> chunks, Location home) {
        this.name = name;
        this.leaders = leaders;
        this.citizens = citizens;
        this.chunks = chunks;
        this.home = home;
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
     * Returns the Home location of the town
     */
    public Location getHome() {
    	return home;
    }
    
    /**
     * Sets the home location of the Town
     */
    public void setHome(Location home) {
    	this.home = home;
    }

    /**
     * Returns the usernames of the leaders of the Town.
     *
     * @return      the usernames of the leaders of the Town
     */
    public Set<String> getLeaders() {
        return leaders;
    }

    /**
     * Sets the usernames of the leaders of the Town.
     *
     * @param leaders       the new usernames of the leaders of the Town
     */
    public void setLeaders(Set<String> leaders) {
        this.leaders = leaders;
    }

    /**
     * Returns the usernames of the citizens of the Town.
     * Citizens are members of the Town but not leaders.
     *
     * @return      the usernames of the citizens of the Town
     */
    public Set<String> getCitizens() {
        return citizens;
    }

    /**
     * Sets the new usernames of the citizens of the Town.
     *
     * @param citizens      the new usernames of the citizens of the Town
     */
    public void setCitizens(Set<String> citizens) {
        this.citizens = citizens;
    }

    /**
     * Returns the set of TownChunks belonging to the Town.
     *
     * @return      the set of TownChunks belonging to the Town
     */
    public Set<TownChunk> getTownChunks() {
        return chunks;
    }

    /**
     * Sets the new set of TownChunks belonging to the Town.
     *
     * @param chunks        the new set of TownChunks belonging to the Town
     */
    public void setTownChunks(Set<TownChunk> chunks) {
        this.chunks = chunks;
    }

    /**
     * Returns if the passed Player is a member of the Town.
     * Members can be citizens or leaders
     *
     * @param playername        name of player to be checked
     * @return                  if the passed Player is a member of the Town
     */
    public boolean hasMember(String playername) {
        return (getCitizens().contains(playername) || getLeaders().contains(playername));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.leaders);
        hash = 71 * hash + Objects.hashCode(this.citizens);
        hash = 71 * hash + Objects.hashCode(this.chunks);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Town other = (Town) obj;
        if (!this.name.equalsIgnoreCase(other.name)) {
            return false;
        }
        if (!Objects.equals(this.leaders, other.leaders)) {
            return false;
        }
        if (!Objects.equals(this.citizens, other.citizens)) {
            return false;
        }
        if (!Objects.equals(this.chunks, other.chunks)) {
            return false;
        }
        return true;
    }
}