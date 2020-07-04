package org.L2X9.EventCore.Patches;

import java.util.HashMap;

import org.L2X9.EventCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class Offhand implements Listener {
	@EventHandler
	public void PlayerSwapHandItemsEvent(PlayerSwapHandItemsEvent event) {
		if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.WRITTEN_BOOK
				|| event.getPlayer().getInventory().getItemInOffHand().getType() == Material.WRITTEN_BOOK
				|| event.getPlayer().getInventory().getItemInMainHand().getType().toString().contains("SHULKER_BOX")
				|| event.getPlayer().getInventory().getItemInOffHand().getType().toString().contains("SHULKER_BOX")) {
			Offhand.addVls(event.getPlayer(), 1);
			Offhand.removeVL(event.getPlayer(), 1);
			if (Offhand.Vls(event.getPlayer()) > 10) {
				event.setCancelled(true);
				event.getPlayer().getInventory().remove(event.getMainHandItem());
				event.getPlayer().getInventory().remove(event.getOffHandItem());
				event.getPlayer().setHealth(0);
				Offhand.resetVls(event.getPlayer());
			}
		}
	}

	public static void addVls(Player player, int vls) {
		if (!vlMap.containsKey(player)) {
			vlMap.put(player, vls);
		} else {
			vlMap.replace(player, Offhand.Vls(player) + 1);
		}
	}

	public static int Vls(Player player) {
		int vls = vlMap.get(player);
		return vls;
	}

	public static void resetVls(Player player) {
		vlMap.remove(player);
	}

	public static void removeVL(Player player, int vlsToRemove) {
		Bukkit.getScheduler().runTaskTimer(Main.getPlugin(), new Runnable() {
			@Override
			public void run() {
				if (Offhand.vlMap.containsKey(player)) {
					if (Offhand.Vls(player) > 0) {
						Offhand.vlMap.replace(player, Offhand.Vls(player) - vlsToRemove);
					} else {
						Bukkit.getScheduler().cancelTasks(Main.getPlugin());
					}
				} else {
					Bukkit.getScheduler().cancelTasks(Main.getPlugin());
				}
			}
		}, 20, 20);
	}

	public static HashMap<Player, Integer> vlMap = new HashMap<Player, Integer>();
}
