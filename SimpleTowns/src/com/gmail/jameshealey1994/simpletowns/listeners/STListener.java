package com.gmail.jameshealey1994.simpletowns.listeners;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.localisation.Localisation;
import com.gmail.jameshealey1994.simpletowns.localisation.LocalisationEntry;
import com.gmail.jameshealey1994.simpletowns.object.Town;
import com.gmail.jameshealey1994.simpletowns.permissions.STPermission;
import com.gmail.jameshealey1994.simpletowns.utils.TownUtils;
import java.util.Objects;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Listener class for SimpleTowns plugin.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class STListener implements Listener {

    /**
     * Plugin associated with the Listener.
     */
    private SimpleTowns plugin;

    /**
     * Constructor - Initialises associated plugin.
     *
     * @param plugin    plugin associated with the listener
     */
    public STListener(SimpleTowns plugin) {
        this.plugin = plugin;
    }

    /**
     * Checks the player is allowed to break the block.
     *
     * @param event     event being handled
     */
    @EventHandler (priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockBreakEvent(BlockBreakEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getBlock();
        if (!canBuild(player, block)) {
            final Town town = plugin.getTown(block.getChunk());
            if (town == null) {
                player.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_CANNOT_BUILD_HERE));
            } else {
                player.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_ONLY_TOWN_MEMBERS_CAN_BREAK_BLOCKS, town.getName()));
            }
            event.setCancelled(true);
        }
    }

    /**
     * Checks the player is allowed to place the block.
     *
     * @param event     event being handled
     */
    @EventHandler (priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getBlockPlaced();
        if (!canBuild(player, block)) {
            final Town town = plugin.getTown(block.getChunk());
            if (town == null) {
                player.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_CANNOT_BUILD_HERE));
            } else {
                player.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_ONLY_TOWN_MEMBERS_CAN_PLACE_BLOCKS, town.getName()));
            }
            event.setCancelled(true);
        }
    }

    /**
     * Checks the player is allowed to fill the bucket.
     *
     * @param event     event being handled
     */
    @EventHandler (priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerBucketFillEvent(PlayerBucketFillEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getBlockClicked();
        if (!canBuild(player, block)) {
            final Town town = plugin.getTown(block.getChunk());
            if (town == null) {
                player.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_CANNOT_BUILD_HERE));
            } else {
                player.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_ONLY_TOWN_MEMBERS_CAN_BREAK_BLOCKS, town.getName()));
            }
            event.setCancelled(true);
        }
    }

    /**
     * Checks the player is allowed to empty the bucket.
     *
     * @param event     event being handled
     */
    @EventHandler (priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getBlockClicked();
        if (!canBuild(player, block)) {
            final Town town = plugin.getTown(block.getChunk());
            if (town == null) {
                player.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_CANNOT_BUILD_HERE));
            } else {
                player.sendMessage(plugin.getLocalisation().get(LocalisationEntry.MSG_ONLY_TOWN_MEMBERS_CAN_PLACE_BLOCKS, town.getName()));
            }
            event.setCancelled(true);
        }
    }

    /**
     * Sends player a message when moving in / out of Town chunks.
     *
     * @param event     event being handled
     */
    @EventHandler (priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent event) {
        final Town exited = plugin.getTown(event.getFrom().getChunk());
        final Town entered = plugin.getTown(event.getTo().getChunk());
        if (!Objects.equals(exited, entered)) {
            final Player player = event.getPlayer();
            final Localisation localisation = plugin.getLocalisation();
            if (exited != null) {
                player.sendMessage(localisation.get(LocalisationEntry.MSG_EXITED_TOWN, exited.getName()));
            }
            if (entered != null) {
                player.sendMessage(localisation.get(LocalisationEntry.MSG_ENTERED_TOWN, entered.getName()));
            }
        }
    }

    /**
     * Returns if a player can break or place a block.
     * (if the chunk the block is in belongs to a town they are a member of)
     *
     * @param player        player being checked
     * @param block         block being checked
     * @return              if the player can build
     */
    private boolean canBuild(Player player, Block block) {
        if (player.hasPermission(STPermission.ADMIN.getPermission())) {
            return true;
        }

        final Town town = plugin.getTown(block.getChunk());
        if (town == null) {
            if (block.getLocation().getBlockY() <= TownUtils.getMineRoofY(plugin)) {
                return player.hasPermission(STPermission.BUILD_MINES.getPermission());
            } else {
                return player.hasPermission(STPermission.BUILD_WILDERNESS.getPermission());
            }
        } else {
            return town.hasMember(player.getName()) && player.hasPermission(STPermission.BUILD_TOWNS.getPermission());
        }
    }
}