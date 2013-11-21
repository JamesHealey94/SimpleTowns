package com.gmail.jameshealey1994.simpletowns.commands.command;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.object.TownChunk;
import com.gmail.jameshealey1994.simpletowns.utils.Logger;
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
public class CreateCommand extends SimpleTownsCommand {

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
                return false;
            }
        }

        // TODO validate townname?

        // TODO validate leadername?

        // Check town doesn't already exist
        if (doesTownExist(plugin, townname)) {
            sender.sendMessage(localisation.get(LocalisationEntry.MSG_TOWN_ALREADY_EXISTS, new Object[] {townname}));
            return true;
        }

        // Check there isn't already a town in that chunk
        final Player player = (Player) sender;
        final Chunk chunk = player.getLocation().getChunk();
        final int chunkX = chunk.getX();
        final int chunkZ = chunk.getZ();
        final String worldname = chunk.getWorld().getName();
        final TownChunk townchunk = new TownChunk(chunkX, chunkZ, worldname);

        for (Town t : plugin.getTowns()) {
            for (TownChunk tc : t.getTownChunks()) {
                if (tc.equalsChunk(chunk)) {
                    sender.sendMessage(localisation.get(LocalisationEntry.MSG_CHUNK_ALREADY_CLAIMED, new Object[] {t.getName()}));
                    return true;
                }
            }
        }

        // Create town
        final String path = "Towns.";
        plugin.getConfig().set(path + townname + ".Leaders", leadername);

        // Log to file
        Logger.log(localisation.get(LocalisationEntry.LOG_TOWN_CREATED, new Object[] {townname, player.getName()}), plugin);
        Logger.log(localisation.get(LocalisationEntry.LOG_TOWN_LEADER_ADDED, new Object[] {townname, leadername, player.getName()}), plugin);

        // Add first chunk to town
        plugin.getConfig().set(path + townname + ".Chunks." + worldname, Arrays.asList(chunkX + "," + chunkZ));
        plugin.getTowns().add(new Town(townname, leadername, townchunk));

        // Log to file
        Logger.log(localisation.get(LocalisationEntry.LOG_CHUNK_CLAIMED, new Object[] {townname, player.getName(), worldname, chunkX, chunkZ}), plugin);

        // Save config
        plugin.saveConfig();

        // Send confimation message to sender
        sender.sendMessage(localisation.get(LocalisationEntry.MSG_TOWN_CREATED, new Object[] {townname}));

        // Broadcast to server? TODO add config value
        plugin.getServer().broadcastMessage(localisation.get(LocalisationEntry.MSG_TOWN_CREATED_BROADCAST, new Object[] {townname}));

        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_DEBUG);
    }

    /**
     * Returns if Town with passed name already exists.
     *
     * @param plugin        plugin with associated list of Towns
     * @param townname      townname to be checked
     * @return              if Town with passed name already exists
     */
    private boolean doesTownExist(SimpleTowns plugin, final String townname) {
        for (Town t : plugin.getTowns()) {
            if (t.getName().equalsIgnoreCase(townname)) {
                return true;
            }
        }
        return false;
    }
}