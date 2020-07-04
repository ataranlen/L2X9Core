package org.L2X9.EventCore;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		if (block.getType() == Material.BEDROCK && !player.isOp()) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					Main.getPlugin().getConfig().getString("IllegalBlock-Place.Bedrock")));
			event.setCancelled(true);
		}
		if (block.getType() == Material.BARRIER && !player.isOp()) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					Main.getPlugin().getConfig().getString("IllegalBlock-Place.Barrier")));
			event.setCancelled(true);
		}
		if (block.getType() == Material.ENDER_PORTAL_FRAME && !player.isOp()) {
			if (!(player.getInventory().getItemInMainHand().getType() == Material.EYE_OF_ENDER)) {
				if (!(player.getInventory().getItemInOffHand().getType() == Material.EYE_OF_ENDER)) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Main.getPlugin().getConfig().getString("IllegalBlock-Place.End_Portal_Frame")));
					event.setCancelled(true);
				}
			}
			if (block.getType() == Material.MOB_SPAWNER && !player.isOp()) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Main.getPlugin().getConfig().getString("IllegalBlock-Place.Mob_Spawner")));
				event.setCancelled(true);

			}
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if (event.getBlock().getType() == Material.BEDROCK) {
			event.setCancelled(true);
		}
	}
}