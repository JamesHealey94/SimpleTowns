package com.gmail.jameshealey1994.simpletowns.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.bukkit.plugin.Plugin;

/**
 * Logs to file.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class Logger {

    /**
     * Plugin with config and data folder.
     */
    private final Plugin plugin;

    /**
     * Constructor - Sets plugin.
     *
     * @param plugin    plugin with config and data folder
     */
    public Logger(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Logs message to file.
     *
     * @param message       message to be logged to file
     */
    public void log(String message) {
        if (LogUtils.isEnabled(plugin)) {
            final File dataFolder = plugin.getDataFolder();
            final String filename = LogUtils.getFilename(plugin);
            final File saveTo = new File(dataFolder, filename);
            final String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

            try (BufferedWriter output = new BufferedWriter(new FileWriter(saveTo, true))) {
                output.write(timeStamp + " " + message + "\n");
            } catch (IOException ex) {
                System.err.println("Problem when logging to the file");
            }
        }
    }
}