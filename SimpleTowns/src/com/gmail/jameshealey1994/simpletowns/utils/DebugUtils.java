package com.gmail.jameshealey1994.simpletowns.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

/**
 * Utility methods that interact with a configuration file for debug values.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class DebugUtils {

    /**
     * The path to the Debug value in the config.
     */
    public static final String PATH_DEBUG = "Debug";

    /**
     * The default value, used if no other values are found.
     */
    public static final boolean DEFAULT = false;

    /**
     * Returns if debug mode is enabled, by checking the configuration file.
     *
     * @param plugin    plugin with associated configuration file
     * @return          if debug mode is enabled
     */
    public static boolean isEnabled(Plugin plugin) {
        return plugin.getConfig().getBoolean(PATH_DEBUG, DEFAULT);
    }

    /**
     * Sets the debug mode for the plugin, then saves the config file.
     *
     * @param sender        sender of the status
     * @param status        new status
     * @param plugin        plugin with associated config file
     */
    public static void setEnabled(CommandSender sender, boolean status, Plugin plugin) {
        plugin.getConfig().set(PATH_DEBUG, status);
        plugin.saveConfig();
    }
}