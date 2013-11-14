package com.gmail.jameshealey1994.simpletowns;

import com.gmail.jameshealey1994.simpletowns.commands.DefaultSimpleTownsCommandEnvironment;
import com.gmail.jameshealey1994.simpletowns.commands.SimpleTownsCommand;
import com.gmail.jameshealey1994.simpletowns.commands.SimpleTownsCommandEnvironment;
import com.gmail.jameshealey1994.simpletowns.commands.SimpleTownsCommandExecutor;
import com.gmail.jameshealey1994.simpletowns.listeners.SimpleTownsListener;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisable;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import java.util.HashSet;
import java.util.Set;
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
    private SimpleTownsCommandEnvironment commandEnvironment = new DefaultSimpleTownsCommandEnvironment();
    
    /**
     * The current localisation for the plugin.
     */
    private Localisation localisation = new Localisation(this);
    
    /**
     * Towns from config TODO.
     */
    private Set<Town> towns = new HashSet<>();

    @Override
    public void onEnable() {

        // Save a copy of the default config.yml if one is not there
        saveDefaultConfig();

        // Register events
        getServer().getPluginManager().registerEvents(new SimpleTownsListener(this), this);

        // Set command executors and default command
        // Set command executors and default command
        getCommand("spt").setExecutor(new SimpleTownsCommandExecutor(this, null));
    }

    /**
     * Returns an array of commands belonging to the plugin.
     *
     * @return      commands belonging to the plugin
     */
    public SimpleTownsCommand[] getCommands() {
        return commandEnvironment.getCommands();
    }

    /**
     * Gets the current command environment for the plugin.
     *
     * @return      the current command environment for the plugin
     */
    public SimpleTownsCommandEnvironment getCommandEnvironment() {
        return commandEnvironment;
    }

    /**
     * Sets the current command environment for the plugin.
     *
     * @param commandEnvironment    the new command environment for the plugin
     */
    public void setCommandEnvironment(SimpleTownsCommandEnvironment commandEnvironment) {
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

    public Set<Town> getTowns() {
        return towns;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }
}