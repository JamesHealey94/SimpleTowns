package com.gmail.jameshealey1994.simpletowns.permissions;

import com.gmail.jameshealey1994.simpletowns.commands.command.STCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * Utility methods that relate to commands and permissions.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class PermissionUtils {

    /**
     * Returns if there's a chance the sender can execute the command.
     * Some commands could pass this check, but then be denied from within the
     * command's execute method.
     *
     * @param command   the command being executed
     * @param sender    the sender of the command
     * @return          if there's a chance the sender can execute the command
     */
    public static boolean canExecute(STCommand command, CommandSender sender) {
        return ((!(sender instanceof Player))
                || ((sender instanceof Player) && (canExecute(command, sender, true))));
    }

    /**
     * Returns if a sender is allowed to execute a command.
     *
     * @param command       the command with permissions being checked
     * @param sender        sender of the command
     * @param allowConsole  if the command is allowed to be executed by console
     * @return              if a player has permissions for the command
     */
    public static boolean canExecute(STCommand command, CommandSender sender, boolean allowConsole) {
        if (sender instanceof Player) {
            if (command.getPermissions().isEmpty()) {
                return true;
            }

            final Player player = (Player) sender;
            for (Permission p : command.getPermissions()) {
                if (player.hasPermission(p)) {
                    return true;
                }
            }
            return false;
        } else {
            return allowConsole;
        }
    }
}