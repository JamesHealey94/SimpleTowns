package com.gmail.jameshealey1994.simpletowns.commands.command;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.object.TownChunk;
import org.bukkit.command.CommandSender;

/**
 * Class representing an Info command.
 * Allows you to view information about a town
 *
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

        if (args.length == 0) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_TOWN));
            return false;
        }

        final Town town = plugin.getTown(args[0]);
        if (town == null) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_TOWN_NOT_FOUND, args[0]));
            return true;
        }

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
        if (!(town.getTownChunks().isEmpty())) {
            sender.sendMessage(localisation.get(LocalisationEntry.INFO_TOWN_CHUNKS_HEADER));
            for (TownChunk tc : town.getTownChunks()) {
                sender.sendMessage(localisation.get(LocalisationEntry.INFO_TOWN_CHUNKS_ENTRY, tc.getWorldname(), tc.getX(), tc.getZ()));
            }
        }
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_INFO);
    }
}