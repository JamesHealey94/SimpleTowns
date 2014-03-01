package com.gmail.jameshealey1994.simpletowns.commands.command;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import com.gmail.jameshealey1994.simpletowns.utils.BooleanParser;
import com.gmail.jameshealey1994.simpletowns.utils.DebugUtils;
import org.bukkit.command.CommandSender;

/**
 * Class representing a debug command.
 * Allows you to change the debug status
 *
 * /... debug <status>      Sets debug status to <status>
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class DebugCommand extends STCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public DebugCommand() {
        this.aliases.add("debug");

        this.permissions.add(STPermission.DEBUG.getPermission());
    }

    @Override
    public boolean execute(SimpleTowns plugin, CommandSender sender, String commandLabel, String[] args) {
        final Localisation localisation = plugin.getLocalisation();

        if (args.length == 0) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_STATUS));
            return false;
        }

        final DebugUtils debugUtils = new DebugUtils(plugin);
        final boolean current = debugUtils.isEnabled();
        final Boolean debugStatus = new BooleanParser(args[0]).parse(current);
        if (debugStatus == null) {
            sender.sendMessage(localisation.get(LocalisationEntry.ERR_SPECIFY_STATUS));
            return false;
        }
        debugUtils.setEnabled(debugStatus);
        sender.sendMessage(localisation.get(LocalisationEntry.MSG_DEBUG_SET_TO_STATUS, debugUtils.isEnabled()));
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_DEBUG);
    }
}