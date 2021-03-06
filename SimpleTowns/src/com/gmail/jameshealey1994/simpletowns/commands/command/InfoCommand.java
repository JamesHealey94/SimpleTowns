package com.gmail.jameshealey1994.simpletowns.commands.command;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class representing an Info command.
 * Allows you to view information about a town
 * /... info            Display information about current town
 * /... info <town>     Display information about a town
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class InfoCommand extends STCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public InfoCommand() {
        this.aliases.add("info");
        this.aliases.add("information");

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

        // Display town information
        // TODO paginate - see ChatPaginator bukkit utility class
        sender.sendMessage(localisation.get(LocalisationEntry.INFO_HEADER, town.getName()));
        if (!(town.getLeaders().isEmpty())) {
            sender.sendMessage(localisation.get(LocalisationEntry.INFO_TOWN_LEADERS_HEADER));
            for (String s : town.getLeaders()) {
                sender.sendMessage(localisation.get(LocalisationEntry.INFO_TOWN_LEADERS_ENTRY, s));
            }
        }
        if (!(town.getCitizens().isEmpty())) {
            sender.sendMessage(localisation.get(LocalisationEntry.INFO_TOWN_CITIZENS_HEADER));
            for (String s : town.getCitizens()) {
                sender.sendMessage(localisation.get(LocalisationEntry.INFO_TOWN_CITIZENS_ENTRY, s));
            }
        }
        sender.sendMessage(localisation.get(LocalisationEntry.INFO_TOWN_CHUNKS, town.getTownChunks().size()));
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_INFO);
    }
}