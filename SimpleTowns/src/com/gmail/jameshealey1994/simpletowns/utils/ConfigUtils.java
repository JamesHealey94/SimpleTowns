package com.gmail.jameshealey1994.simpletowns.utils;

import java.util.logging.Level;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class ConfigUtils {

    /**
     * Returns a configuration section from a passed plugin and path.
     * If no configuration section is found, an empty configuration section
     * is created, saved, and returned.
     *
     * @param plugin    plugin with a configuration
     * @param path      path to the configuration section to return
     * @return          configuration section in config of passed plugin
     *                  with passed path
     */
    public static ConfigurationSection getConfigSection(Plugin plugin, String path) {
        ConfigurationSection configSection = plugin.getConfig().getConfigurationSection(path);
        if (configSection == null) {
            configSection = createEmptyConfigSection(plugin, path);
        }
        return configSection;
    }

    /**
     * Creates an empty ConfigurationSection
     * at the passed path in the config of the passed plugin.
     * The config is then saved, and the empty ConfigurationSection returned.
     *
     * Any value that was previously set at this path will be overwritten.
     * If the previous value was itself a ConfigurationSection,
     * it will be orphaned.
     *
     * @param plugin    plugin with a configuration
     * @param path      path to the configuration section to create or overwrite
     * @return          an empty configuration section
     */
    public static ConfigurationSection createEmptyConfigSection(Plugin plugin, String path) {
        plugin.getLogger().log(Level.INFO, "Config section ''{0}'' not found. Creating...", path);
        final ConfigurationSection emptyConfigSection = plugin.getConfig().createSection(path);
        plugin.saveConfig();
        plugin.getLogger().log(Level.INFO, "Config section ''{0}'' created", path);
        return emptyConfigSection;
    }

    /**
     * Private constructor - Objects cannot be created.
     */
    private ConfigUtils() {
    }
}