package com.gmail.jameshealey1994.simpletowns;

import com.gmail.jameshealey1994.simpletowns.commands.DefaultSTCommandEnvironment;
import com.gmail.jameshealey1994.simpletowns.commands.STCommandEnvironment;
import com.gmail.jameshealey1994.simpletowns.commands.STCommandExecutor;
import com.gmail.jameshealey1994.simpletowns.commands.command.HelpCommand;
import com.gmail.jameshealey1994.simpletowns.commands.command.STCommand;
import com.gmail.jameshealey1994.simpletowns.listeners.STListener;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisable;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.object.TownChunk;
import com.gmail.jameshealey1994.simpletowns.utils.TownUtils;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Chunk;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Simple, chunk-based protection plugin for Bukkit.
 * Further description at http://dev.bukkit.org/bukkit-plugins/simpletowns/
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class SimpleTowns extends JavaPlugin implements Localisable {

    /**
     * The current command environment for the plugin (subset of commands
     * accessible from the current state of the plugin).
     */
    private STCommandEnvironment commandEnvironment = new DefaultSTCommandEnvironment();

    /**
     * The current localisation for the plugin.
     */
    private Localisation localisation = new Localisation(this);
    
    /**
     * The current plugin.
     */
    private static SimpleTowns plugin;
    
    /**
     * Towns from config.
     */
    private Map<String, Town> towns = new HashMap<>();

    @Override
    public void onEnable() {
    	
    	// This is so we can access this from get() 
    	plugin = this;
    	
        // Save a copy of the default config.yml if one is not there
        saveDefaultConfig();

        // Load towns from config
        this.towns = TownUtils.getTownsFromConfig(this);

        // Register events
        getServer().getPluginManager().registerEvents(new STListener(this), this);

        // Set command executors and default command
        getCommand("towns").setExecutor(new STCommandExecutor(this, new HelpCommand()));
    }

    /**
     * Returns an array of commands belonging to the plugin.
     *
     * @return      commands belonging to the plugin
     */
    public STCommand[] getCommands() {
        return commandEnvironment.getCommands();
    }

    /**
     * Gets the current command environment for the plugin.
     *
     * @return      the current command environment for the plugin
     */
    public STCommandEnvironment getCommandEnvironment() {
        return commandEnvironment;
    }

    /**
     * Sets the current command environment for the plugin.
     *
     * @param commandEnvironment    the new command environment for the plugin
     */
    public void setCommandEnvironment(STCommandEnvironment commandEnvironment) {
        this.commandEnvironment = commandEnvironment;
    }

    @Override
    public Localisation getLocalisation() {
        return localisation;
    }

    @Override
    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    /**
     * Returns the Towns in the server.
     *
     * @return      the Towns in the server
     */
    public Map<String, Town> getTowns() {
        return towns;
    }

    /**
     * Sets the Towns in the server.
     *
     * @param towns     the new Towns in the server
     */
    public void setTowns(Map<String, Town> towns) {
        this.towns = towns;
    }

    /**
     * Returns Town with name equal to passed String.
     * If a town is not found, null is returned.
     *
     * @param townname      possible town name
     * @return              town with name equal to passed string, or null, if
     *                      no such town is found
     */
    public Town getTown(String townname) {
        return towns.get(townname.toLowerCase());
    }

    /**
     * Returns Town that owns the passed Chunk.
     * If a town is not found, null is returned.
     *
     * @param chunk     chunk possibly owned by a Town
     * @return          town that owns the passed Chunk, or null, if no such
     *                  town is found
     */
    public Town getTown(Chunk chunk) {
        for (Town t : towns.values()) {
            for (TownChunk tc : t.getTownChunks()) {
                if (tc.equalsChunk(chunk)) {
                    return t;
                }
            }
        }
        return null;
    }
    
    /**
     * Returns the current SimpleTowns class.
     * Useful for add-ons. 
     */
    public static SimpleTowns get() {
        return plugin; 
    }
}