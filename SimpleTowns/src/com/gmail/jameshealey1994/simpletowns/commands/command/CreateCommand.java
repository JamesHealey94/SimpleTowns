package com.gmail.jameshealey1994.simpletowns.commands.command;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.events.TownCreateEvent;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.object.TownChunk;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import com.gmail.jameshealey1994.simpletowns.utils.Logger;
import com.gmail.jameshealey1994.simpletowns.utils.NameValidityChecker;
import com.gmail.jameshealey1994.simpletowns.utils.TownUtils;
import java.util.Arrays;
import org.bukkit.Chunk;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class representing a create command.
 *
 * /... create <townname>                   Creates a town with <townname> and current chunk
 * /... create <townname> <username>        Creates a town with <townname> and current chunk, <username> as leader
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class CreateCommand extends STCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public CreateCommand() {
        this.aliases.add("create");
        this.aliases.add("new");

        this.permissions.add(STPermission.CREATE.getPermission());
    }

    @Override
    public boolean execute(SimpleTowns plugin, CommandSender sender, String commandLabel, String[] args) {
        final Localisation localisation = plugin.getLocalisation();

        if (!(sender instanceof Player)) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_ONLY_COMMAND));
            return true;
        }

        final String townname;
        final String leadername;

        switch (args.length) {
            case 0: {
                sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_TOWN_NAME));
                return false;
            }
            case 1: {
                // ... townname
                townname = args[0];
                leadername = sender.getName();
                break;
            }
            case 2: {
                // ... townname leader
                townname = args[0];
                leadername = args[1];
                break;
            }
            default: {
                sender.sendMessage(localisation.get(LocalisationEntry.ERR_TOWN_NAMES_MUST_BE_A_SINGLE_WORD));
                return false;
            }
        }

        // Validate town name
        if (!TownUtils.isValidName(townname)) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_INVALID_TOWN_NAME, townname));
            return true;
        }

        // Validate leader name
        if (!new NameValidityChecker(leadername).isValidName()) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_INVALID_PLAYER_NAME, leadername));
            return true;
        }

        // Check town doesn't already exist
        if (plugin.getTown(townname) != null) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_TOWN_ALREADY_EXISTS, townname));
            return true;
        }

        // Check there isn't already a town in that chunk
        final Player player = (Player) sender;
        final Chunk chunk = player.getLocation().getChunk();

        final Town town = plugin.getTown(chunk);
        if (town != null) {
            sender.sendMessage(localisation.get(LocalisationEntry.MSG_CHUNK_ALREADY_CLAIMED, town.getName()));
            return true;
        }

        //Create and call TownCreateEvent
        final TownCreateEvent event = new TownCreateEvent(town, sender);
        plugin.getServer().getPluginManager().callEvent(event);

        // Check event has not been cancelled by event listeners
        if (event.isCancelled()) {
            return true;
        }

        final int chunkX = chunk.getX();
        final int chunkZ = chunk.getZ();
        final String worldname = chunk.getWorld().getName();
        final TownChunk townchunk = new TownChunk(chunk);

        // Create town
        final String path = "Towns.";
        plugin.getConfig().set(path + townname + ".Leaders", Arrays.asList(leadername));

        // Log to file
        final Logger logger = new Logger(plugin);
        logger.log(localisation.get(LocalisationEntry.LOG_TOWN_CREATED, townname, player.getName()));
        logger.log(localisation.get(LocalisationEntry.LOG_TOWN_LEADER_ADDED, townname, leadername, player.getName()));

        // Add first chunk to town
        plugin.getConfig().set(path + townname + ".Chunks." + worldname, Arrays.asList(chunkX + "," + chunkZ));
        plugin.getTowns().put(townname.toLowerCase(), new Town(townname, leadername, townchunk));

        // Log to file
        logger.log(localisation.get(LocalisationEntry.LOG_CHUNK_CLAIMED, townname, player.getName(), worldname, chunkX, chunkZ));

        // Save config
        plugin.saveConfig();

        // Send confimation message to sender
        sender.sendMessage(localisation.get(LocalisationEntry.MSG_TOWN_CREATED, townname));

        // Broadcast to server
        plugin.getServer().broadcastMessage(localisation.get(LocalisationEntry.MSG_TOWN_CREATED_BROADCAST, townname));

        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_CREATE);
    }
}