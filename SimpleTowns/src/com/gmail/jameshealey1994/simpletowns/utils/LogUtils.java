package com.gmail.jameshealey1994.simpletowns.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

/**
 * Utility methods that interact with a configuration file for logging values.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class LogUtils {

    /**
     * The string for the value in the config this class is interacting with.
     */
    public static final String CONFIG_STRING = "Logging";

    /**
     * The default enabled value, used if no other values are found.
     */
    public static final boolean DEFAULT_ENABLED = false;

    /**
     * The default log filename value, used if no other values are found.
     */
    public static final String DEFAULT_FILENAME = "log.txt";

    /**
     * Returns if the config has specified that logging is enabled.
     * Currently the default is false.
     *
     * @param plugin        plugin with config which stores data
     * @return              if the config has specified that logging is enabled
     */
    public static boolean isEnabled(Plugin plugin) {
        return  plugin.getConfig().getBoolean(CONFIG_STRING + ".Enabled",
                DEFAULT_ENABLED);
    }

    /**
     * Sets the logging mode for the plugin, then saves the config file.
     *
     * @param sender        sender of the status
     * @param status        new status
     * @param plugin        plugin with associated config file
     */
    public static void setEnabled(CommandSender sender, boolean status, Plugin plugin) {
        plugin.getConfig().set(CONFIG_STRING + ".Enabled", status);
        plugin.saveConfig();
    }

    /**
     * Return the log filename value from the server config.
     * Currently the default is an empty string.
     *
     * @param plugin    plugin with config which stores data
     * @return          the default prefix value of the server
     */
    public static String getFilename(Plugin plugin) {
        return  plugin.getConfig().getString(CONFIG_STRING + ".Filename",
                DEFAULT_FILENAME);
    }
}