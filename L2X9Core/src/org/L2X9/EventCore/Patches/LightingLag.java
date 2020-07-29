package org.L2X9.EventCore.Patches;

import org.L2X9.EventCore.Utils;
import org.L2X9.EventCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class LightingLag implements Listener {
	@EventHandler
	public void delay(BlockBreakEvent event) {
		if (event.getBlock().getLocation().getBlockY() > 250
				&& Utils.getTps() <= Main.getPlugin().getConfig().getInt("LightingLag-StepIn.TPS")) {
			event.setCancelled(true);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
				@Override
				public void run() {
					event.getBlock().breakNaturally();
					Bukkit.getScheduler().cancelTasks(Main.getPlugin());

				}
			}, 20);
		}
	}

	@EventHandler
	public void delay(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Material block = event.getPlayer().getInventory().getItemInMainHand().getType();
		if (event.getBlock().getLocation().getBlockY() > 250
				&& Utils.getTps() <= Main.getPlugin().getConfig().getInt("LightingLag-StepIn.TPS")) {
			event.setCancelled(true);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
				@Override
				public void run() {
					player.getInventory().getItemInMainHand()
							.setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
					event.getBlock().getLocation().getWorld()
							.getBlockAt(event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ())
							.setType(block);
					Bukkit.getScheduler().cancelTasks(Main.getPlugin());

				}
			}, 20);
		}
	}
}