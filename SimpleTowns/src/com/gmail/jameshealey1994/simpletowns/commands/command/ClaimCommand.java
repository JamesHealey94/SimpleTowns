package com.gmail.jameshealey1994.simpletowns.commands.command;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.events.TownClaimEvent;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.object.TownChunk;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import com.gmail.jameshealey1994.simpletowns.utils.Logger;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class representing a Claim command.
 *
 * /... claim <town>        Claims current chunk for <town>
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class ClaimCommand extends STCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public ClaimCommand() {
        this.aliases.add("claim");

        this.permissions.add(STPermission.CLAIM.getPermission());
    }

    @Override
    public boolean execute(SimpleTowns plugin, CommandSender sender, String commandLabel, String[] args) {
        final Localisation localisation = plugin.getLocalisation();

        if (!(sender instanceof Player)) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_ONLY_COMMAND));
            return true;
        }

        Town decidedTown = null;
        final Player player = (Player) sender;
        
        if (args.length == 0) {
        	
        	int countLeaderOf = 0;
        	Town townLeaderOf = null;
        	
        	for(Town checkingTown : plugin.getTowns().values()) {
        		if(checkingTown.getLeaders().contains(player.getName())) {
        			countLeaderOf++;
        			townLeaderOf = checkingTown;
        		}
        	}
        	
        	if(countLeaderOf == 1) {
        		decidedTown = townLeaderOf;
        	} else {
	        	Location loc = player.getLocation();
	        	Chunk c = loc.getChunk();
	        	
	        	Town townA = plugin.getTown(loc.getWorld().getChunkAt(c.getX()+1, c.getZ()));
	        	Town townB = plugin.getTown(loc.getWorld().getChunkAt(c.getX()-1, c.getZ()));
	        	Town townC = plugin.getTown(loc.getWorld().getChunkAt(c.getX(), c.getZ()+1));
	        	Town townD = plugin.getTown(loc.getWorld().getChunkAt(c.getX(), c.getZ()-1));
	        	
	        	List<Town> towns = new ArrayList<Town>();
	        	
	        	if(townA != null) { 
	        		if(townA.getLeaders().contains(sender.getName()) && !towns.contains(townA)) {
	        			towns.add(townA);
	        		}
	        	}
	        	
	        	if(townB != null) {
	        		if(townB.getLeaders().contains(sender.getName()) && !towns.contains(townB)) {
	        			towns.add(townB);
	        		}
	        	}
	        	
	        	if(townC != null) {
	        		if(townC.getLeaders().contains(sender.getName()) && !towns.contains(townC)) {
	        			towns.add(townC);
	        		}
	        	}
	        	
	        	if(townD != null) {
	        		if(townD.getLeaders().contains(sender.getName()) && !towns.contains(townD)) {
	        			towns.add(townD);
	        		}
	        	}
	        	
	        	if(towns.size() == 0) {
	        		sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_TOWN));
	        		
	        		return false;
	        	} else if(towns.size() == 1) {
	        		decidedTown = towns.get(0);
	        	} else {
	        		String townList = "";
	        		
	        		int i = 0;
	        		for(Town currentTown : towns) {
	        			i++;
	        			if(i == towns.size()) {
	        				townList += currentTown.getName()+".";
	        			} else {
	        				townList += currentTown.getName()+", ";
	        			}
	        		}
	        		
	        		sender.sendMessage(localisation.get(LocalisationEntry.MSG_MULTIPLE_TOWNS_NEARBY, townList));
	        		
	                return true;
	        	}
        	}
        } else {
        	decidedTown = plugin.getTown(args[0]);
        }

        
        final Town town = decidedTown;

        // Check town exists
        if (town == null) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_TOWN_NOT_FOUND, args[0]));
            return true;
        }

        // Check they're a leader of that town.
        if (!(town.getLeaders().contains(sender.getName())) && !sender.hasPermission(STPermission.ADMIN.getPermission())) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_NOT_LEADER, town.getName()));
            return true;
        }

        // Check there isn't already a town in that chunk
        final Chunk chunk = player.getLocation().getChunk();
        final Town chunkOwner = plugin.getTown(chunk);
        if (chunkOwner != null) {
            sender.sendMessage(localisation.get(LocalisationEntry.MSG_CHUNK_ALREADY_CLAIMED, chunkOwner.getName()));
            return true;
        }

        //Create and call TownClaimEvent
        final TownClaimEvent event = new TownClaimEvent(town, chunk, sender);
        plugin.getServer().getPluginManager().callEvent(event);

        // Check event has not been cancelled by event listeners
        if (event.isCancelled()) {
            return true;
        }

        final int chunkX = chunk.getX();
        final int chunkZ = chunk.getZ();
        final String worldname = chunk.getWorld().getName();
        final TownChunk townchunk = new TownChunk(chunk);

        final String path = "Towns.";

        // Add chunk to town
        final String chunkString = chunkX + "," + chunkZ;
        final List<String> chunks = plugin.getConfig().getStringList(path + town.getName() + ".Chunks." + worldname);
        chunks.add(chunkString);
        plugin.getConfig().set(path + town.getName() + ".Chunks." + worldname, chunks);
        town.getTownChunks().add(townchunk);

        // Log to file
        new Logger(plugin).log(localisation.get(LocalisationEntry.LOG_CHUNK_CLAIMED, town.getName(), sender.getName(), worldname, chunkX, chunkZ));

        // Save config
        plugin.saveConfig();

        // Send confimation message to sender
        sender.sendMessage(localisation.get(LocalisationEntry.MSG_CHUNK_CLAIMED, town.getName()));
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_CLAIM);
    }
}