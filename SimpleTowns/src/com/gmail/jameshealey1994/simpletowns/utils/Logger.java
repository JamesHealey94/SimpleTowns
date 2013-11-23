package com.gmail.jameshealey1994.simpletowns.utils;

import org.bukkit.plugin.Plugin;

/**
 * Logs to file.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class Logger {

    /**
     * Logs message to file.
     *
     * @param message       message to be logged to file
     * @param plugin        plugin with config
     */
    public static void log(String message, Plugin plugin) {
        if (LogUtils.isEnabled(plugin)) {
            // TODO
            // current date and time + message
        }
    }
}