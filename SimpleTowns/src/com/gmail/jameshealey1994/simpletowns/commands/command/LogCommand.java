package com.gmail.jameshealey1994.simpletowns.commands.command;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.utils.BooleanParser;
import com.gmail.jameshealey1994.simpletowns.utils.LogUtils;
import org.bukkit.command.CommandSender;

/**
 * Class representing a Log command.
 * Allows you to change the logging status
 *
 * /... log <status>      Sets logging status to <status>
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class LogCommand extends STCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public LogCommand() {
        this.aliases.add("log");
        this.aliases.add("logging");

        this.permissions.add(STPermission.LOG.getPermission());
    }

    @Override
    public boolean execute(SimpleTowns plugin, CommandSender sender, String commandLabel, String[] args) {
        final Localisation localisation = plugin.getLocalisation();

        if (args.length == 0) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_STATUS));
            return false;
        }

        final boolean current = LogUtils.isEnabled(plugin);
        final Boolean status = BooleanParser.parse(args[0], current);
        if (status == null) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_STATUS));
            return false;
        }
        LogUtils.setEnabled(sender, status, plugin);
        sender.sendMessage(localisation.get(LocalisationEntry.MSG_LOG_STATUS_SET, LogUtils.isEnabled(plugin)));
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_LOG);
    }
}