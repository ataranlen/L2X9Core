package org.L2X9.EventCore;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandEvent implements Listener {
	@SuppressWarnings({ "unused" })
	@EventHandler
	public void onCMD(PlayerCommandPreprocessEvent event) {
		int spawn = Main.getPlugin().getConfig().getInt("Spawn.Raidus");
		Player player = event.getPlayer();
		if (!player.isOp() && player.getLocation().getBlockX() < spawn && player.getLocation().getBlockX() > -spawn
				&& player.getLocation().getBlockZ() < spawn && player.getLocation().getBlockZ() > -spawn) {
			String cmd = event.getMessage();
			String firstcmd = "";
			String firstfour = "";
			if (cmd.length() > 4) {
				firstcmd = cmd.substring(0, 5);
			}
			if (cmd.length() > 3) {
				firstfour = cmd.substring(0, 4);
			}
			if (firstcmd.equalsIgnoreCase("/home")) {
				event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&6you must be &r&c" + spawn + "&6 blocks away from spawn to use /home"));
				event.setCancelled(true);
			}
		}

	}
}
