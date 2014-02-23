package com.gmail.jameshealey1994.simpletowns.utils;

import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.object.TownChunk;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

/**
 * Utility methods that interact with a configuration file for town values.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public final class TownUtils {

    /**
     * The path to the values in the config this class is interacting with.
     */
    public static final String PATH = "Towns";

    /**
     * Returns towns from config.
     *
     * @param plugin        plugin with config and logger
     * @return              towns from config
     */
    public static Map<String, Town> getTownsFromConfig(Plugin plugin) {
        final ConfigurationSection townConfigSection = getConfigSection(plugin, PATH);
        final Map<String, Town> townsFromConfig = new HashMap<>();
        final Set<String> townKeys = new HashSet<>(townConfigSection.getKeys(false));

        for (String townname : townKeys) {
            try {
                final Set<String> leaders = new HashSet<>(plugin.getConfig().getStringList(PATH + "." + townname + ".Leaders"));
                final Set<String> citizens = new HashSet<>(plugin.getConfig().getStringList(PATH + "." + townname + ".Citizens"));
                final Set<String> chunkWorlds = new HashSet<>(plugin.getConfig().getConfigurationSection(PATH + "." + townname + ".Chunks").getKeys(false));
                final Set<TownChunk> chunks = new HashSet<>();
                for (String world : chunkWorlds) {
                    final Set<String> chunkKeys = new HashSet<>(plugin.getConfig().getStringList(PATH + "." + townname + ".Chunks." + world));
                    for (String chunk : chunkKeys) {
                        final int chunkX = Integer.parseInt(chunk.substring(0, chunk.indexOf(',')));
                        final int chunkZ = Integer.parseInt(chunk.substring(chunk.indexOf(',') + 1));
                        final TownChunk townchunk = new TownChunk(chunkX, chunkZ, world);
                        chunks.add(townchunk);
                    }
                }
                final Town town = new Town(townname, leaders, citizens, chunks);
                townsFromConfig.put(townname.toLowerCase(), town);
            } catch (NumberFormatException | NullPointerException ex) {
                plugin.getLogger().log(Level.WARNING, "{0} getting towns from config: {1}", new Object[] {ex.getClass().getName(), ex.getMessage()});
            }
        }
        return townsFromConfig;
    }

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
    private static ConfigurationSection getConfigSection(Plugin plugin, String path) {
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
    private static ConfigurationSection createEmptyConfigSection(Plugin plugin, String path) {
        plugin.getLogger().log(Level.INFO, "Config section ''{0}'' not found. Creating...", path);
        final ConfigurationSection emptyConfigSection = plugin.getConfig().createSection(path);
        plugin.saveConfig();
        plugin.getLogger().log(Level.INFO, "Config section ''{0}'' created", path);
        return emptyConfigSection;
    }

    /**
     * Returns if a string is a valid town name.
     * Valid town names are between 1 and 16 characters long, and contain
     * only letters, numbers, and underscores.
     *
     * @param possibleName      possible town name to be checked for validity
     * @return                  if the town name is valid
     */
    public static boolean isValidName(String possibleName) {
        return new NameValidityChecker(possibleName).isValidName();
    }

    /**
     * Returns the Y value of the mine roof.
     *
     * @param plugin        plugin with config
     * @return              Y value of the mine roof,
     *                      or -1 if no value is specified in the config
     */
    public static int getMineRoofY(Plugin plugin) {
        return plugin.getConfig().getInt("Mine Roof Y Value", -1);
    }

    /**
     * Private constructor - Objects cannot be created.
     */
    private TownUtils() {
    }
}