package com.gmail.jameshealey1994.simpletowns.commands.command;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.events.TownAfterReloadEvent;
import com.gmail.jameshealey1994.simpletowns.events.TownBeforeReloadEvent;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import com.gmail.jameshealey1994.simpletowns.utils.TownUtils;
import org.bukkit.command.CommandSender;

/**
 * Class representing a reload command.
 * Allows you to reload the config
 *
 * /... reload      Reloads config values
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class ReloadCommand extends STCommand {

    /**
     * Constructor to add aliases and permissions.
     */
    public ReloadCommand() {
        this.aliases.add("reload");

        this.permissions.add(STPermission.RELOAD.getPermission());
    }

    @Override
    public boolean execute(SimpleTowns plugin, CommandSender sender, String commandLabel, String[] args) {

        //Create and call TownBeforeReloadEvent
        final TownBeforeReloadEvent reloadEvent = new TownBeforeReloadEvent();
        plugin.getServer().getPluginManager().callEvent(reloadEvent);

        // Check event has not been cancelled by event listeners
        if (reloadEvent.isCancelled()) {
            return true;
        }

        plugin.reloadConfig();
        plugin.setTowns(TownUtils.getTownsFromConfig(plugin));
        sender.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_CONFIG_RELOADED));

        //Create and call TownAfterReloadEvent
        final TownAfterReloadEvent reloadCompletedEvent = new TownAfterReloadEvent();
        plugin.getServer().getPluginManager().callEvent(reloadCompletedEvent);
        return true;
    }

    @Override
    public String getDescription(Localisation localisation) {
        return localisation.get(LocalisationEntry.DESCRIPTION_RELOAD);
    }
}