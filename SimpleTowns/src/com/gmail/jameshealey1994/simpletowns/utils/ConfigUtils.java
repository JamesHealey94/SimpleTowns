package com.gmail.jameshealey1994.simpletowns.utils;

import java.util.logging.Level;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

/**
 * Utility methods that interact with a configuration file for values.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class ConfigUtils {

    /**
     * Plugin with a configuration.
     */
    private final Plugin plugin;

    /**
     * Constructor - Sets plugin with config.
     *
     * @param plugin    plugin with a configuration
     */
    public ConfigUtils(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Returns a configuration section from plugin and a passed path.
     * If no configuration section is found, an empty configuration section
     * is created, saved, and returned.
     *
     * @param path      path to the configuration section to return
     * @return          configuration section in config of plugin
     *                  with passed path
     */
    public ConfigurationSection getConfigSection(String path) {
        ConfigurationSection configSection = plugin.getConfig().getConfigurationSection(path);
        if (configSection == null) {
            configSection = createEmptyConfigSection(path);
        }
        return configSection;
    }

    /**
     * Creates an empty ConfigurationSection
     * at the passed path in the config of the plugin.
     * The config is then saved, and the empty ConfigurationSection returned.
     *
     * Any value that was previously set at this path will be overwritten.
     * If the previous value was itself a ConfigurationSection,
     * it will be orphaned.
     *
     * @param path      path to the configuration section to create or overwrite
     * @return          an empty configuration section
     */
    public ConfigurationSection createEmptyConfigSection(String path) {
        plugin.getLogger().log(Level.INFO, "Config section ''{0}'' not found. Creating...", path);
        final ConfigurationSection emptyConfigSection = plugin.getConfig().createSection(path);
        plugin.saveConfig();
        plugin.getLogger().log(Level.INFO, "Config section ''{0}'' created", path);
        return emptyConfigSection;
    }
}