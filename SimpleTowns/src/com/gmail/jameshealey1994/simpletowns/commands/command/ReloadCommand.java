package com.gmail.jameshealey1994.simpletowns.commands.command;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import org.bukkit.command.CommandSender;

/**
 * Class representing a reload command.
 * Allows you to reload the config
 *
 * /... reload      Reloads config values
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class ReloadCommand extends SimpleTownsCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public ReloadCommand() {
        this.aliases.add("reload");

        this.permissions.add(STPermission.RELOAD.getPermission());
    }

    @Override
    public boolean execute(SimpleTowns plugin, CommandSender sender, String commandLabel, String[] args) {
        plugin.reloadConfig();
        sender.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_CONFIG_RELOADED));
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_RELOAD);
    }
}