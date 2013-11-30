package com.gmail.jameshealey1994.simpletowns.commands.command;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.utils.Logger;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class representing a Promote command.
 * /... promote <user>              Promote <username> to a town leader in current town
 * /... promote <user> <town>       Promote <username> to a town leader in [town]
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class PromoteCommand extends STCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public PromoteCommand() {
        this.aliases.add("promote");
        this.aliases.add("promoteplayer");
        this.aliases.add("promotemember");
        this.aliases.add("promotecitizen");
    }

    @Override
    public boolean execute(SimpleTowns plugin, CommandSender sender, String commandLabel, String[] args) {
        final Localisation localisation = plugin.getLocalisation();

        final String playername;
        final String townname;

        switch (args.length) {
            case 0: {
                sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_PLAYER));
                return false;
            }
            case 1: {
                // ... username
                playername = args[0];

                if (sender instanceof Player) {
                    final Player player = (Player) sender;
                    final Town town = plugin.getTown(player.getLocation().getChunk());

                    if (town == null) {
                        sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_TOWN_NAME));
                        return false;
                    }

                    townname = town.getName();
                } else {
                    sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_TOWN_NAME));
                    return false;
                }
                break;
            }
            case 2: {
                // ... username townname
                playername = args[0];
                townname = args[1];
                break;
            }
            default: {
                return false;
            }
        }

        final Town town = plugin.getTown(townname);

        // Check town exists
        if (town == null) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_TOWN_NOT_FOUND, townname));
            return true;
        }

        // Check sender is a leader of that town.
        if ((sender instanceof Player) && (!(town.getLeaders().contains(sender.getName())))) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_NOT_LEADER, town.getName()));
            return true;
        }

        // Check player is a member of the town
        if (!town.hasMember(playername)) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_NOT_MEMBER));
            return true;
        }

        // Check player isn't already a leader of town (cannot be promoted)
        if (town.getLeaders().contains(playername)) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_ALREADY_LEADER, playername, town.getName()));
            return true;
        }

        // Promote player from citizen to leader in town
        // (remove from citizens, add to leaders)
        final String basePath = "Towns." + town.getName();
        final String citizensPath = basePath + ".Citizens";
        final List<String> citizens = plugin.getConfig().getStringList(citizensPath);
        citizens.remove(playername);
        plugin.getConfig().set(citizensPath, citizens);
        plugin.getTown(town.getName()).getCitizens().remove(playername);

        final String leadersPath = basePath + ".Leaders";
        final List<String> leaders = plugin.getConfig().getStringList(leadersPath);
        leaders.add(playername);
        plugin.getConfig().set(leadersPath, citizens);
        plugin.getTown(town.getName()).getLeaders().add(playername);

        // Log to file
        Logger.log(localisation.get(LocalisationEntry.LOG_CITIZEN_PROMOTED, town.getName(), sender.getName(), playername), plugin);

        // Save config
        plugin.saveConfig();

        // Send confimation message to sender
        sender.sendMessage(localisation.get(LocalisationEntry.MSG_CITIZEN_PROMOTED, town.getName(), playername));

        // Send message to citizen, if they're online
        final Player citizen = plugin.getServer().getPlayer(playername);
        if (citizen != null) {
            citizen.sendMessage(localisation.get(LocalisationEntry.MSG_PROMOTED, town.getName(), sender.getName()));
        }
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_PROMOTE);
    }
}