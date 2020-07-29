package org.L2X9.EventCore.AntiLag;

import org.L2X9.EventCore.Utils;
import org.L2X9.EventCore.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Elytra implements Listener {
	@EventHandler
	public void onToggleGlide(EntityToggleGlideEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (Utils.getTps() <= Main.getPlugin().getConfig().getInt("Elytra.Disable-TPS")) {
				event.setCancelled(true);
				Utils.sendMessage(player, Main.getPlugin().getConfig().getString("ElytraLowTPS.Message").replace("{tps}", "" + Main.getPlugin().getConfig().getInt("Elytra.Disable-TPS")));
			}
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (Utils.getTps() <= Main.getPlugin().getConfig().getInt("Elytra.Disable-TPS")) {
			if (player.isGliding()) {
				Utils.sendMessage(player, Main.getPlugin().getConfig().getString("ElytraLowTPS.Message").replace("{tps}", "" + Main.getPlugin().getConfig().getInt("Elytra.Disable-TPS")));
				player.setGliding(false);
				if (!(player.getWorld().getBlockAt(
					 player.getLocation().getBlockX(),
					  player.getLocation().getBlockY() - 1,
					  player.getLocation().getBlockZ())
					 .getType() == Material.BEDROCK)) {
					  player.getLocation().getBlock().setType(Material.OBSIDIAN);
				}
				PlayerInventory inventory = player.getInventory();
				if (!((inventory.getChestplate() != null) && inventory.getChestplate().getType().equals(Material.ELYTRA)))
					return;
				ItemStack elytra = inventory.getChestplate();
				inventory.setChestplate(null);
				if (inventory.firstEmpty() != -1) {
					inventory.addItem(elytra);
				} else {
					Location loc = inventory.getLocation();
					loc.getWorld().dropItemNaturally(loc, elytra);
					player.updateInventory();
				}
			}
		}
	}
}
