package com.gmail.jameshealey1994.simpletowns.commands.command;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.events.TownDeleteEvent;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import com.gmail.jameshealey1994.simpletowns.utils.Logger;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class representing a delete command.
 *
 * /... delete <townname>                   Deletes town named <townname>
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class DeleteCommand extends STCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public DeleteCommand() {
        this.aliases.add("delete");
        this.aliases.add("deletetown");

        this.permissions.add(STPermission.DELETE.getPermission());
    }

    @Override
    public boolean execute(SimpleTowns plugin, CommandSender sender, String commandLabel, String[] args) {
        final Localisation localisation = plugin.getLocalisation();

        if (args.length == 0) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_TOWN_NAME));
            return false;
        }

        // Check town exists
        final String attemptedTownName = args[0];
        final Town town = plugin.getTown(attemptedTownName);
        if (town == null) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_TOWN_NOT_FOUND, attemptedTownName));
            return true;
        }

        // Check player is town leader or admin
        if (sender instanceof Player && !town.getLeaders().contains(sender.getName()) && !sender.hasPermission(STPermission.ADMIN.getPermission())) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_NOT_LEADER, attemptedTownName));
            return true;
        }

        //Create and call TownDeleteEvent
        final TownDeleteEvent event = new TownDeleteEvent(town, sender);
        plugin.getServer().getPluginManager().callEvent(event);

        // Check event has not been cancelled by event listeners
        if (event.isCancelled()) {
            return true;
        }

        // Delete town locally
        plugin.getTowns().remove(town.getName().toLowerCase());

        // Delete town from config
        final String path = "Towns.";
        plugin.getConfig().set(path + town.getName(), null);

        // Log to file
        new Logger(plugin).log(localisation.get(LocalisationEntry.LOG_TOWN_DELETED, town.getName(), sender.getName()));

        // Save config
        plugin.saveConfig();

        // Send confimation message to sender
        sender.sendMessage(localisation.get(LocalisationEntry.MSG_TOWN_DELETED, town.getName()));

        // Broadcast to server
        plugin.getServer().broadcastMessage(localisation.get(LocalisationEntry.MSG_TOWN_DELETED_BROADCAST, town.getName()));

        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_DELETE);
    }
}