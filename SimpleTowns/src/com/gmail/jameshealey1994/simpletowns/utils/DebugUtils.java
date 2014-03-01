package com.gmail.jameshealey1994.simpletowns.utils;

import org.bukkit.plugin.Plugin;

/**
 * Utility methods that interact with a configuration file for debug values.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class DebugUtils {

    /**
     * The path to the Debug value in the config.
     */
    public static final String PATH_DEBUG = "Debug";

    /**
     * The default value, used if no other values are found.
     */
    public static final boolean DEFAULT = false;

    /**
     * Plugin with associated config file.
     */
    private final Plugin plugin;

    /**
     * Constructor - Sets plugin.
     *
     * @param plugin    plugin with associated config file
     */
    public DebugUtils(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Returns if debug mode is enabled, by checking the configuration file.
     *
     * @return  if debug mode is enabled
     */
    public boolean isEnabled() {
        return plugin.getConfig().getBoolean(PATH_DEBUG, DEFAULT);
    }

    /**
     * Sets the debug mode for the plugin, then saves the config file.
     *
     * @param status    new status
     */
    public void setEnabled(boolean status) {
        plugin.getConfig().set(PATH_DEBUG, status);
        plugin.saveConfig();
    }
}