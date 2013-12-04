package com.gmail.jameshealey1994.simpletowns.listeners;

import com.gmail.jameshealey1994.simpletowns.SimpleTowns;
import com.gmail.jameshealey1994.simpletowns.object.Town;
import java.util.Objects;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
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
    SimpleTowns plugin;

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
    @EventHandler (priority = EventPriority.HIGH)
    public void onBlockBreakEvent(BlockBreakEvent event) {
        final Player player = event.getPlayer();
        final Chunk chunk = event.getBlock().getChunk();
        if (!canBuild(player, chunk)) {
            player.sendMessage("You aren't allowed to build here!"); // TODO - Change
            event.setCancelled(true);
        }
    }

    /**
     * Checks the player is allowed to place the block.
     *
     * @param event     event being handled
     */
    @EventHandler (priority = EventPriority.HIGH)
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        final Chunk chunk = event.getBlockPlaced().getLocation().getChunk();
        if (!canBuild(player, chunk)) {
            player.sendMessage("You aren't allowed to build here!"); // TODO - Change
            event.setCancelled(true);
        }
    }

    /**
     * Sends player a message when moving in / out of Town chunks.
     *
     * @param event     event being handled
     */
    @EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerMove(PlayerMoveEvent event) {
        final Town exited = plugin.getTown(event.getFrom().getChunk());
        final Town entered = plugin.getTown(event.getTo().getChunk());
        if (!Objects.equals(exited, entered)) {
            if (exited != null) {
                event.getPlayer().sendMessage("exited " + exited.getName());
            }
            if (entered != null) {
                event.getPlayer().sendMessage("entered " + entered.getName());
            }
        }
    }

    /**
     * Returns if a player can build in a chunk.
     * (if the chunk belongs to a town they are a member of)
     *
     * @param player        player being checked
     * @param chunk         chunk being checked
     * @return              if the player can build
     */
    private boolean canBuild(Player player, Chunk chunk) {
        final Town town = plugin.getTown(chunk);
        if (town != null && !town.hasMember(player.getName())) {
            return false;
        }
        return true;
    }
}