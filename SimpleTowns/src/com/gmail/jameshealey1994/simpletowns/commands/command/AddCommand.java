package com.gmail.jameshealey1994.simpletowns.commands.command;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.events.TownAddEvent;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import com.gmail.jameshealey1994.simpletowns.utils.Logger;
import com.gmail.jameshealey1994.simpletowns.utils.NameValidityChecker;
import java.util.List;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class representing an Add command.
 * /... add <user>              Claims current chunk for sender's current town
 * /... add <user> <town>       Claims current chunk for <town>
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class AddCommand extends STCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public AddCommand() {
        this.aliases.add("add");
        this.aliases.add("addplayer");
        this.aliases.add("addmember");
        this.aliases.add("addcitizen");
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
        if ((sender instanceof Player) && (!(town.getLeaders().contains(sender.getName()))) && !sender.hasPermission(STPermission.ADMIN.getPermission())) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_NOT_LEADER, town.getName()));
            return true;
        }

        // Validate player name
        if (!new NameValidityChecker(playername).isValidName()) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_INVALID_PLAYER_NAME, playername));
            return true;
        }

        // Get player's full name
        final String fullPlayerName = getFullName(plugin.getServer(), playername);

        // Check player isn't already a member of town (citizen or leader)
        if (town.hasMember(fullPlayerName)) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_ALREADY_MEMBER, fullPlayerName, town.getName()));
            return true;
        }

        //Create and call event
        final TownAddEvent event = new TownAddEvent(town, sender, fullPlayerName);
        plugin.getServer().getPluginManager().callEvent(event);

        // Check event has not been cancelled by event listeners
        if (event.isCancelled()) {
            return true;
        }

        // Add citizen to town locally
        town.getCitizens().add(fullPlayerName);

        // Add citizen to town in config
        final String path = "Towns." + town.getName() + ".Citizens";
        final List<String> citizens = plugin.getConfig().getStringList(path);
        citizens.add(fullPlayerName);
        plugin.getConfig().set(path, citizens);

        // Log to file
        new Logger(plugin).log(localisation.get(LocalisationEntry.LOG_CITIZEN_ADDED, town.getName(), sender.getName(), fullPlayerName));

        // Save config
        plugin.saveConfig();

        // Send confimation message to sender
        sender.sendMessage(localisation.get(LocalisationEntry.MSG_CITIZEN_ADDED, town.getName(), fullPlayerName));

        // Send message to citizen, if they're online
        final Player citizen = plugin.getServer().getPlayer(fullPlayerName);
        if (citizen != null) {
            citizen.sendMessage(localisation.get(LocalisationEntry.MSG_ADDED_AS_CITIZEN, town.getName(), sender.getName()));
        }
        return true;
    }

    /**
     * Get full name of a player.
     * The name with either match a player in the passed server,
     * or, if no match is found, the passed playername will be used.
     *
     * @param server        server containing players
     * @param playername    name possibly matching a player in the server
     * @return              If the passed playername matches the start of a
     *                      name from a player in the passed server, the full
     *                      name of that player will be returned.
     *                      If no names match, the original passed playername
     *                      will be returned.
     */
    private String getFullName(Server server, final String playername) {
        final Player player = server.getPlayer(playername);
        if (player == null) {
            return playername;
        } else {
            return player.getName();
        }
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_ADD);
    }
}