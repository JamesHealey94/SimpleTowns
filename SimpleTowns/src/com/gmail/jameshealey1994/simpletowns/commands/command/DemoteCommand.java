package com.gmail.jameshealey1994.simpletowns.commands.command;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import com.gmail.jameshealey1994.simpletowns.utils.Logger;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class representing a Promote command.
 * /... demote <user>              Demote <username> to a town citizen in current town
 * /... demote <user> <town>       Demote <username> to a town citizen in [town]
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class DemoteCommand extends STCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public DemoteCommand() {
        this.aliases.add("demote");
        this.aliases.add("demoteplayer");
        this.aliases.add("demotemember");
        this.aliases.add("demoteleader");
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
        if ((sender instanceof Player) && !(town.getLeaders().contains(sender.getName())) && !sender.hasPermission(STPermission.ADMIN.getPermission())) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_NOT_LEADER, town.getName()));
            return true;
        }

        // Check player is a member of the town
        if (!town.hasMember(playername)) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_NOT_MEMBER, playername, town.getName()));
            return true;
        }

        // Check player isn't already a citizen of town (cannot be demoted)
        if (town.getCitizens().contains(playername)) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_ALREADY_CITIZEN, playername, town.getName()));
            return true;
        }

        // Demote player from leader to citizen in town
        // (remove from leaders, add to citizens)
        final String basePath = "Towns." + town.getName();
        final String leadersPath = basePath + ".Leaders";
        final List<String> leaders = plugin.getConfig().getStringList(leadersPath);
        leaders.remove(playername);
        plugin.getConfig().set(leadersPath, leaders);
        plugin.getTown(town.getName()).getLeaders().remove(playername);

        final String citizensPath = basePath + ".Citizens";
        final List<String> citizens = plugin.getConfig().getStringList(citizensPath);
        citizens.add(playername);
        plugin.getConfig().set(citizensPath, citizens);
        plugin.getTown(town.getName()).getCitizens().add(playername);

        // Log to file
        new Logger(plugin).log(localisation.get(LocalisationEntry.LOG_LEADER_DEMOTED, town.getName(), sender.getName(), playername));

        // Save config
        plugin.saveConfig();

        // Send confimation message to sender
        sender.sendMessage(localisation.get(LocalisationEntry.MSG_LEADER_DEMOTED, town.getName(), playername));

        // Send message to citizen, if they're online
        final Player leader = plugin.getServer().getPlayer(playername);
        if (leader != null) {
            leader.sendMessage(localisation.get(LocalisationEntry.MSG_DEMOTED, town.getName(), sender.getName()));
        }
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_DEMOTE);
    }
}