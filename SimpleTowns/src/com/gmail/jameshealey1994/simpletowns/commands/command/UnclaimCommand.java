package com.gmail.jameshealey1994.simpletowns.commands.command;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.object.TownChunk;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import com.gmail.jameshealey1994.simpletowns.utils.Logger;
import java.util.List;
import org.bukkit.Chunk;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class representing an Unclaim command.
 *
 * /... unclaim         Unclaims current chunk
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class UnclaimCommand extends STCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public UnclaimCommand() {
        this.aliases.add("unclaim");

        /*
         * No permission added as command can be used by anyone,
         * as long as they're the leader of that town.
         */
    }

    @Override
    public boolean execute(SimpleTowns plugin, CommandSender sender, String commandLabel, String[] args) {
        final Localisation localisation = plugin.getLocalisation();

        if (!(sender instanceof Player)) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_ONLY_COMMAND));
            return true;
        }

        final Player player = (Player) sender;
        final Town town = plugin.getTown(player.getLocation().getChunk());
        final Chunk chunk = player.getLocation().getChunk();
        final String worldname = chunk.getWorld().getName();
        final int chunkX = chunk.getX();
        final int chunkZ = chunk.getZ();

        // Check there's a town in current chunk
        if (town == null) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_NO_TOWN_OWNS_CHUNK, worldname, chunkX, chunkZ));
            return true;
        }

        // Check player is a leader of that town.
        if (!(town.getLeaders().contains(sender.getName())) && !sender.hasPermission(STPermission.ADMIN.getPermission())) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_NOT_LEADER, town.getName()));
            return true;
        }

        // Remove chunk from config town
        final String path = "Towns.";
        final List<String> chunks = plugin.getConfig().getStringList(path + town.getName() + ".Chunks." + worldname);
        final String chunkString = chunkX + "," + chunkZ;
        chunks.remove(chunkString);
        plugin.getConfig().set(path + town.getName() + ".Chunks." + worldname, chunks);

        // Remove chunk from local town
        final TownChunk townchunk = new TownChunk(chunk);
        town.getTownChunks().remove(townchunk);

        // Log to file
        new Logger(plugin).log(localisation.get(LocalisationEntry.LOG_CHUNK_UNCLAIMED, town.getName(), sender.getName(), worldname, chunkX, chunkZ));

        // Save config
        plugin.saveConfig();

        // Send confimation message to sender
        sender.sendMessage(localisation.get(LocalisationEntry.MSG_CHUNK_UNCLAIMED, town.getName()));
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_UNCLAIM);
    }
}