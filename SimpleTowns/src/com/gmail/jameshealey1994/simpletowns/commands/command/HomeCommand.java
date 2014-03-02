package com.gmail.jameshealey1994.simpletowns.commands.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;

/**
 * Class representing the Home command.
 * /... home <town>              Sends the player to the home of a town
 *
 * @author MarkehMe <mark@markeh.me> 
 */
public class HomeCommand extends STCommand {
	
    /**
     * Constructor to add aliases and permissions.
     */
    public HomeCommand() {
        this.aliases.add("home");
        this.permissions.add(STPermission.HOME.getPermission());
    }
    
    @Override
    public boolean execute(SimpleTowns plugin, CommandSender sender, String commandLabel, String[] args) {
        final Localisation localisation = plugin.getLocalisation();
        
        // Don't allow the console to run this command
        if (!(sender instanceof Player)) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_ONLY_COMMAND));
            return true;
        }
        
        if (args.length == 0) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_TOWN));
            return true;
        }
        
        final Player player = (Player) sender;

        final Town town = plugin.getTown(args[0]);
        
        // Check the town exists
        if (town == null) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_TOWN_NOT_FOUND, args[0]));
            return true;
        }
        
        // Check if the player is a member of that town
        if (!town.hasMember(player.getName()) && !player.hasPermission(STPermission.ADMIN.getPermission())) {
        	sender.sendMessage(localisation.get(LocalisationEntry.ERR_PLAYER_NOT_MEMBER, player.getName(), args[0]));
        	return true;
        }
        
        // All must be OK so teleport the player to the home
        // TODO: Look for Essentials and use their SafeLocation check?
        
        if (town.getHome() == null ) {
        	sender.sendMessage(localisation.get(LocalisationEntry.MSG_NO_HOME_SET, args[0]));
        } else {
        	player.teleport(town.getHome());
        	sender.sendMessage(localisation.get(LocalisationEntry.MSG_SENT_HOME, args[0]));
        }
        
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_HOME);
    }
    
}
