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
     * Returns towns from config.
     *
     * @param plugin        plugin with config
     * @return              towns from config
     */
    public static Set<Town> getTownsFromConfig(Plugin plugin) {
        final Set<Town> townsFromConfig = new HashSet();
        final Set<String> townKeys = new HashSet(plugin.getConfig().getConfigurationSection("Towns").getKeys(false));
        for (String key : townKeys) {
            try {
                plugin.getLogger().info("'" + key + "'");
                plugin.getLogger().info("'" + key + ".Leaders" + "'");
                for (Object s : plugin.getConfig().getStringList(key + ".Leaders")) {
                    plugin.getLogger().info("'" + s + "'");
                }
                final Set<String> leaders = new HashSet(plugin.getConfig().getStringList(key + ".Leaders"));
                final Set<String> citizens = new HashSet(plugin.getConfig().getStringList(key + ".Citizens"));
                final Set<String> chunkWorlds = new HashSet(plugin.getConfig().getStringList(key + ".Chunks"));
                plugin.getLogger().info("'" + key + ".Chunks" + "'");
                for (Object s : plugin.getConfig().getStringList(key + ".Chunks")) {
                    plugin.getLogger().info("'" + s + "'");
                } // TODO investigate - should it be VVVVVV ? doesnt seem like getStringList is returning anything
//                final Set<String> chunkWorlds = new HashSet(plugin.getConfig().getConfigurationSection(key + ".Chunks").getKeys(false));
                final Set<TownChunk> chunks = new HashSet();
                for (String world : chunkWorlds) {
                    final Set<String> chunkKeys = new HashSet(plugin.getConfig().getList(key + ".Chunks." + world));
                    for (String chunk : chunkKeys) {
                        final int chunkX = Integer.parseInt(chunk.substring(0, chunk.indexOf(",")));
                        final int chunkZ = Integer.parseInt(chunk.substring(chunk.indexOf(",")));
                        final TownChunk townchunk = new TownChunk(chunkX, chunkZ, world);
                        chunks.add(townchunk);
                    }
                }
                final Town town = new Town(key, leaders, citizens, chunks);
                townsFromConfig.add(town);
            } catch (NumberFormatException ex) { // TODO
                plugin.getLogger().log(Level.WARNING, "NumberFormatException getting towns from config: {0}", ex.getMessage());
//            } catch (NullPointerException ex) {
//                plugin.getLogger().log(Level.WARNING, "NullPointerException getting towns from config: {0}", ex.getMessage());
            }
        }
        return townsFromConfig;
    }
}