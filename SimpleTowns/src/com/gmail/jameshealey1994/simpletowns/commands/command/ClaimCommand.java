package com.gmail.jameshealey1994.simpletowns.commands.command;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.object.TownChunk;
import com.gmail.jameshealey1994.simpletowns.utils.Logger;
import java.util.List;
import org.bukkit.Chunk;
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

        if (args.length == 0) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_TOWN));
            return false;
        }

        final Player player = (Player) sender;
        final String townname = args[0];
        final Town town = plugin.getTown(townname);

        // Check town exists
        if (town == null) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_TOWN_NOT_FOUND, townname));
            return true;
        }

        // Check they're a leader of that town.
        if (!(town.getLeaders().contains(sender.getName()))) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_NOT_LEADER, townname));
            return true;
        }

        // Check there isn't already a town in that chunk
        final Chunk chunk = player.getLocation().getChunk();
        final Town chunkOwner = plugin.getTown(chunk);
        if (chunkOwner != null) {
            sender.sendMessage(localisation.get(LocalisationEntry.MSG_CHUNK_ALREADY_CLAIMED, chunkOwner.getName()));
            return true;
        }

        final int chunkX = chunk.getX();
        final int chunkZ = chunk.getZ();
        final String worldname = chunk.getWorld().getName();
        final TownChunk townchunk = new TownChunk(chunkX, chunkZ, worldname);

        final String path = "Towns.";

        // Add chunk to town
        final String chunkString = chunkX + "," + chunkZ;
        final List<String> chunks = plugin.getConfig().getStringList(path + townname + ".Chunks." + worldname);
        chunks.add(chunkString);
        plugin.getConfig().set(path + townname + ".Chunks." + worldname, chunks);
        plugin.getTown(townname).getTownChunks().add(townchunk);

        // Log to file
        Logger.log(localisation.get(LocalisationEntry.LOG_CHUNK_CLAIMED, townname, sender.getName(), worldname, chunkX, chunkZ), plugin);

        // Save config
        plugin.saveConfig();

        // Send confimation message to sender
        sender.sendMessage(localisation.get(LocalisationEntry.MSG_CHUNK_CLAIMED, townname));
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_DEBUG);
    }
}