package com.gmail.jameshealey1994.simpletowns.utils;

import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.object.TownChunk;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.regex.Pattern;
import org.bukkit.plugin.Plugin;

/**
 * Utility methods that interact with a configuration file for town values.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class TownUtils {

    /**
     * The path to the values in the config this class is interacting with.
     */
    public static final String CONFIG_STRING = "Towns";

    /**
     * Returns towns from config.
     *
     * @param plugin        plugin with config
     * @return              towns from config
     */
    public static Map<String, Town> getTownsFromConfig(Plugin plugin) {
        final Map<String, Town> townsFromConfig = new HashMap<>();
        final Set<String> townKeys = new HashSet<>(plugin.getConfig().getConfigurationSection(CONFIG_STRING).getKeys(false));
        for (String townname : townKeys) {
            try {
                final Set<String> leaders = new HashSet<>(plugin.getConfig().getStringList(CONFIG_STRING + "." + townname + ".Leaders"));
                final Set<String> citizens = new HashSet<>(plugin.getConfig().getStringList(CONFIG_STRING + "." + townname + ".Citizens"));
                final Set<String> chunkWorlds = new HashSet<>(plugin.getConfig().getConfigurationSection(CONFIG_STRING + "." + townname + ".Chunks").getKeys(false));
                final Set<TownChunk> chunks = new HashSet<>();
                for (String world : chunkWorlds) {
                    final Set<String> chunkKeys = new HashSet<>(plugin.getConfig().getStringList(CONFIG_STRING + "." + townname + ".Chunks." + world));
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
     * Returns if a string is a valid town name.
     * Valid town names are between 1 and 16 characters long, and contain
     * only letters, numbers, and underscores.
     *
     * @param possibleName      possible town name to be checked for validity
     * @return                  if the town name is valid
     */
    public static boolean isValidName(String possibleName) {
        if (1 <= possibleName.length() && possibleName.length() <= 16) {
            // http://stackoverflow.com/a/8248352/1735524
            final Pattern p = Pattern.compile("[^a-zA-Z0-9_]");
            final boolean hasSpecialChar = p.matcher(possibleName).find();
            return !hasSpecialChar;
        }
        return false;
    }

    /**
     * Returns the Y value of the mine roof.
     *
     * @param plugin        plugin with config
     * @return      Y value of the mine roof,
     *              or -1 if no value is specified in the config
     */
    public static int getMineRoofY(Plugin plugin) {
        return plugin.getConfig().getInt("Mine Roof Y Value", -1);
    }
}