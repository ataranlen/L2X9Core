package org.L2X9.EventCore.Events;

import org.L2X9.EventCore.Main;
import org.L2X9.EventCore.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandEvent implements Listener {

	@EventHandler
	public void onCMD(PlayerCommandPreprocessEvent event) {
		int spawn = Main.getPlugin().getConfig().getInt("Spawn.Raidus");
		Player player = event.getPlayer();
		if (!player.isOp() && player.getLocation().getBlockX() < spawn && player.getLocation().getBlockX() > -spawn
				&& player.getLocation().getBlockZ() < spawn && player.getLocation().getBlockZ() > -spawn) {
			if (event.getMessage().toLowerCase().contains("/home")) {
				event.setCancelled(true);
				Utils.sendMessage(player, Main.getPlugin().getConfig().getString("Spawn.Message").replace("%r%", "" + spawn + ""));
			}
		}
	}
}
