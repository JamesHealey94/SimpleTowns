package com.gmail.jameshealey1994.simpletowns.commands.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import com.gmail.jameshealey1994.simpletowns.utils.TownUtils;

/**
 * Class representing the sethome command.
 * /... sethome              Sets the current position to the home of the town they're standing in
 *
 * @author MarkehMe <mark@markeh.me>
 */
public class SetHomeCommand extends STCommand {
	
    /**
     * Constructor to add aliases and permissions.
     */
    public SetHomeCommand() {
        this.aliases.add("sethome");
        this.permissions.add(STPermission.SET_HOME.getPermission());
    }
    
    @Override
    public boolean execute(SimpleTowns plugin, CommandSender sender, String commandLabel, String[] args) {
        final Localisation localisation = plugin.getLocalisation();
        
        // Don't allow the console to run this command
        if (!(sender instanceof Player)) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_ONLY_COMMAND));
            return true;
        }
        
        final Player player = (Player) sender;

        final Town town = plugin.getTown(player.getLocation().getChunk());
        
        // Confirm there is a town at the current chunk
        if (town == null) {
        	sender.sendMessage(localisation.get(LocalisationEntry.ERR_NO_TOWN_HERE));
        	return true;
        }
        
        // Confirm the player is a leader or have the admin permission
        if (!town.getLeaders().contains(player.getName()) && !sender.hasPermission(STPermission.ADMIN.getPermission())) {
        	sender.sendMessage(localisation.get(LocalisationEntry.ERR_NOT_LEADER, town.getName()));
        	return true;
        }
        
        // Update home location internally 
        town.setHome(player.getLocation());
        
        // Update in configuration XXX: Should this be done in the Town class? Would be helpful for addons 
        plugin.getConfig().set(TownUtils.PATH + "." + town.getName() + ".Home", (player.getWorld().getName() + " " + player.getLocation().getX() + " " + player.getLocation().getY() + " " + player.getLocation().getZ()));
        plugin.saveConfig();
        
        sender.sendMessage(localisation.get(LocalisationEntry.MSG_HOME_SET, args[0]));
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_SETHOME);
    }
    
}
