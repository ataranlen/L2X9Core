package org.L2X9.EventCore.Patches;

import org.L2X9.EventCore.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class ChinkBan implements Listener {
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();
		if (!(player.hasPermission("chunkban.bypass"))) {
			if (block.getType() == Material.FURNACE || block.getType() == Material.CHEST
					|| block.getType() == Material.TRAPPED_CHEST || block.getType() == Material.ENCHANTMENT_TABLE
					|| block.getType() == Material.WALL_SIGN || block.getType() == Material.HOPPER
					|| block.getType() == Material.DROPPER || block.getType() == Material.DISPENSER
					|| block.getType() == Material.BREWING_STAND || block.getType() == Material.BEACON
					|| block.getType() == Material.SIGN_POST || block.getType() == Material.ENDER_CHEST) {
				if (event.getBlock().getChunk().getTileEntities().length > Main.getPlugin().getConfig()
						.getInt("ChunkBan.TileEntity-Max")) {
					event.setCancelled(true);
					player.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Main.getPlugin().getConfig().getString("ChunkBan.Prevent-Message")));

				}
			}
			if (block.getType() == Material.SKULL || block.getType() == Material.SKULL_ITEM) {
				if (block.getChunk().getTileEntities().length > Main.getPlugin().getConfig()
						.getInt("ChunkBan.Skull-Max")) {
					event.setCancelled(true);
					player.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Main.getPlugin().getConfig().getString("ChunkBan.Prevent-Message")));

				}
			}
		}
	}
}