package com.gmail.jameshealey1994.simpletowns.utils;

import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.object.TownChunk;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import org.bukkit.plugin.Plugin;

/**
 * Utility methods that interact with a configuration file for town values.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class TownConfigUtils {

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
    public static Set<Town> getTownsFromConfig(Plugin plugin) {
        final Set<Town> townsFromConfig = new HashSet();
        final Set<String> townKeys = new HashSet(plugin.getConfig().getConfigurationSection(CONFIG_STRING).getKeys(false));
        for (String townname : townKeys) {
            try {
                final Set<String> leaders = new HashSet(plugin.getConfig().getStringList(CONFIG_STRING + townname + ".Leaders"));
                final Set<String> citizens = new HashSet(plugin.getConfig().getStringList(CONFIG_STRING + townname + ".Citizens"));
                final Set<String> chunkWorlds = new HashSet(plugin.getConfig().getStringList(CONFIG_STRING + townname + ".Chunks"));
                final Set<TownChunk> chunks = new HashSet();
                for (String world : chunkWorlds) {
                    final Set<String> chunkKeys = new HashSet(plugin.getConfig().getList(townname + ".Chunks." + world));
                    for (String chunk : chunkKeys) {
                        final int chunkX = Integer.parseInt(chunk.substring(0, chunk.indexOf(",")));
                        final int chunkZ = Integer.parseInt(chunk.substring(chunk.indexOf(",")));
                        final TownChunk townchunk = new TownChunk(chunkX, chunkZ, world);
                        chunks.add(townchunk);
                    }
                }
                final Town town = new Town(townname, leaders, citizens, chunks);
                townsFromConfig.add(town);
            } catch (NumberFormatException ex) { // TODO expand
                plugin.getLogger().log(Level.WARNING, "NumberFormatException getting towns from config: {0}", ex.getMessage());
            }
        }
        return townsFromConfig;
    }
}