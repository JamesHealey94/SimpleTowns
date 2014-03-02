package com.gmail.jameshealey1994.simpletowns.utils;

import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.object.TownChunk;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.World;
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
     * @param plugin        plugin with config
     * @return              towns from config
     */
    public static Map<String, Town> getTownsFromConfig(Plugin plugin) {
        final Map<String, Town> townsFromConfig = new HashMap<>();

        ConfigurationSection townConfigSection = plugin.getConfig().getConfigurationSection(PATH);
        if (townConfigSection == null) {
            plugin.getLogger().log(Level.INFO, "Config section ''{0}'' not found. Creating...", PATH);
            townConfigSection = plugin.getConfig().createSection(PATH);
            plugin.saveConfig();
            plugin.getLogger().log(Level.INFO, "Config section ''{0}'' created", PATH);
        }

        final Set<String> townKeys = new HashSet<>(townConfigSection.getKeys(false));
        for (String townname : townKeys) {
            try {
            	
            	// Fetch home information from the database. It is formatted like this: world x y z
            	String[] homeInformation = plugin.getConfig().getString(PATH + "." + townname + ".Home").split(" ");
            	
            	Location townHome = null;
            	            	
            	// Check if we where able to split the string into 3 (0,1,2,3) sections
            	if(homeInformation.length > 2) {
            		
	            	// Sort this information into world_name, x, y, and z
	            	String world_name = homeInformation[0];
	            	
	            	Double x = Double.parseDouble(homeInformation[1]);
	            	Double y = Double.parseDouble(homeInformation[2]);
	            	Double z = Double.parseDouble(homeInformation[3]);
	            	
	            	// Fetch the world
	            	// TODO: Test if world is loaded
	                World townWorld = plugin.getServer().getWorld(world_name);
	            	
	            	// Results in the home location
	            	townHome = new Location (townWorld, x, y, z);
            	}
            	
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
                final Town town = new Town(townname, leaders, citizens, chunks, townHome);
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