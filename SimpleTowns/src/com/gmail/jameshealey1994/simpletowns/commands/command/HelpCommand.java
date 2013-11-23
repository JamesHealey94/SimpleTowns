package com.gmail.jameshealey1994.simpletowns.commands.command;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.permissions.PermissionUtils;
import org.bukkit.command.CommandSender;

/**
 * Class representing a help command.
 *
 * /... [help]      Shows help menu
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class HelpCommand extends STCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public HelpCommand() {
        this.aliases.add("help");
    }

    @Override
    public boolean execute(SimpleTowns plugin, CommandSender sender, String commandLabel, String[] args) {
        final Localisation localisation = plugin.getLocalisation();

        sender.sendMessage(localisation.get(LocalisationEntry.HELP_HEADER, plugin.getDescription().getFullName()));

        for (STCommand command : plugin.getCommands()) {
            if (PermissionUtils.canExecute(command, sender, true)) {
                sender.sendMessage(localisation.get(LocalisationEntry.HELP_ENTRY, command.aliases.get(0), command.getDescription(localisation)));
            }
        }
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_HELP);
    }
}