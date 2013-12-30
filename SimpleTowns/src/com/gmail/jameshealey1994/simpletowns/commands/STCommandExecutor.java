package com.gmail.jameshealey1994.simpletowns.commands;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.commands.command.STCommand;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.permissions.PermissionUtils;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import java.util.Arrays;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Command executor for the SimpleTowns plugin.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class STCommandExecutor implements CommandExecutor {

    /**
     * Plugin the commands are executed for.
     */
    private SimpleTowns plugin;

    /**
     * The default command, executed when no arguments are given.
     * Can be null, in which case no command is executed.
     */
    private STCommand defaultCommand;

    /**
     * Constructor to set plugin instance variable.
     *
     * @param plugin            plugin used to set internal plugin value
     * @param defaultCommand    default command, executed when no args are given
     */
    public STCommandExecutor(SimpleTowns plugin, STCommand defaultCommand) {
        this.plugin = plugin;
        this.defaultCommand = defaultCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args) {
        if (args.length > 0) {
            for (STCommand command : plugin.getCommands()) {
                if (command.getAliases().contains(args[0].toLowerCase())) {
                    if (sender.hasPermission(STPermission.ADMIN.getPermission()) || PermissionUtils.canExecute(command, sender)) {
                        return command.execute(plugin, sender, args[0], Arrays.copyOfRange(args, 1, args.length));
                    } else {
                        sender.sendMessage(plugin.getLocalisation().get(LocalisationEntry.ERR_PERMISSION_DENIED));
                        return true;
                    }
                }
            }
        } else { // No args given
            if (defaultCommand == null) {
                return false;
            } else {
                return defaultCommand.execute(plugin, sender, commandlabel, args);
            }
        }
        return false;
    }
}