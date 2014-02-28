package com.gmail.jameshealey1994.simpletowns.commands.command;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import com.gmail.jameshealey1994.simpletowns.utils.Logger;
import com.gmail.jameshealey1994.simpletowns.utils.TownUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

/**
 * Class representing a Rename command.
 * /... rename <newname>              Renames current town to <newname>
 * /... rename <town> <newname>       Renames current <town> to <newname>
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class RenameCommand extends STCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public RenameCommand() {
        this.aliases.add("rename");
        this.aliases.add("renametown");
    }

    @Override
    public boolean execute(SimpleTowns plugin, CommandSender sender, String commandLabel, String[] args) {
        final Localisation localisation = plugin.getLocalisation();

        final String townName;
        final String newName;

        switch (args.length) {
            case 0: {
                sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_TOWN_NAME));
                return false;
            }
            case 1: {
                // ... newname
                newName = args[0];

                if (sender instanceof Player) {
                    final Player player = (Player) sender;
                    final Town town = plugin.getTown(player.getLocation().getChunk());

                    if (town == null) {
                        sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_TOWN_NAME));
                        return false;
                    }

                    townName = town.getName();
                } else {
                    sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_TOWN_NAME));
                    return false;
                }
                break;
            }
            case 2: {
                // ... townname newname
                townName = args[0];
                newName = args[1];
                break;
            }
            default: {
                return false;
            }
        }

        // Validate town name
        if (!TownUtils.isValidName(townName)) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_INVALID_TOWN_NAME, townName));
            return true;
        }

        final Town town = plugin.getTown(townName);

        // Check town exists
        if (town == null) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_TOWN_NOT_FOUND, townName));
            return true;
        }

        // Check sender is a leader of that town.
        if ((sender instanceof Player) && (!(town.getLeaders().contains(sender.getName()))) && !sender.hasPermission(STPermission.ADMIN.getPermission())) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_NOT_LEADER, town.getName()));
            return true;
        }

        // Check a town with the new name doesn't already exist
        if (plugin.getTowns().containsKey(newName)) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_TOWN_ALREADY_EXISTS, townName));
            return true;
        }

        // Rename town in config
        final String oldName = town.getName();
        final String oldPath = "Towns." + oldName;
        final ConfigurationSection values = plugin.getConfig().getConfigurationSection(oldPath);
        plugin.getConfig().set(oldPath, null);

        final String newPath = "Towns." + newName;
        plugin.getConfig().set(newPath, values);

        // Save config
        plugin.saveConfig();

        // Rename town locally
        plugin.getTowns().remove(town.getName().toLowerCase());
        town.setName(newName);
        plugin.getTowns().put(newName.toLowerCase(), town);

        // Log to file
        new Logger(plugin).log(localisation.get(LocalisationEntry.LOG_TOWN_RENAMED, oldName, town.getName(), sender.getName()));

        // Send confimation message to sender
        sender.sendMessage(localisation.get(LocalisationEntry.MSG_RENAMED_TOWN, oldName, town.getName()));

        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_RENAME);
    }
}