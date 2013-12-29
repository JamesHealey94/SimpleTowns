package com.gmail.jameshealey1994.simpletowns.commands.command;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.object.TownChunk;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class representing an Chunks command.
 * Allows you to view information about a town's chunks
 * /... chunks            Display information about current town's chunks
 * /... chunks <town>     Display information about a town's chunks
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class ChunksCommand extends STCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public ChunksCommand() {
        this.aliases.add("chunks");

        this.permissions.add(STPermission.INFO.getPermission());
    }

    @Override
    public boolean execute(SimpleTowns plugin, CommandSender sender, String commandLabel, String[] args) {
        final Localisation localisation = plugin.getLocalisation();

        final Town town;

        if (args.length == 0) {
            if (sender instanceof Player) {
                final Player player = (Player) sender;
                town = plugin.getTown(player.getLocation().getChunk());

                if (town == null) {
                    sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_TOWN_NAME));
                    return true;
                }
            } else {
                sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_TOWN_NAME));
                return true;
            }
        } else {
            town = plugin.getTown(args[0]);
        }

        if (town == null) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_TOWN_NOT_FOUND, args[0]));
            return true;
        }

        // Display chunk information
        // TODO paginate - see ChatPaginator bukkit utility class
        sender.sendMessage(localisation.get(LocalisationEntry.INFO_HEADER, town.getName()));
        sender.sendMessage(localisation.get(LocalisationEntry.INFO_TOWN_CHUNKS, town.getTownChunks().size()));
        for (TownChunk tc : town.getTownChunks()) {
            sender.sendMessage(localisation.get(LocalisationEntry.INFO_TOWN_CHUNKS_ENTRY, tc.getWorldname(), tc.getX(), tc.getZ()));
        }

        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_CHUNKS);
    }
}