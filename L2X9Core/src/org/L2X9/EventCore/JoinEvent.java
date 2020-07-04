package org.L2X9.EventCore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
	public static Player player;

	@EventHandler
	public static void onJoin(PlayerJoinEvent e) {
		player = e.getPlayer();
		if (!player.hasPlayedBefore()) {
			Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
					Main.getPlugin().getConfig().getString("FirstJoin.Message").replace("{Player}", player.getName())));
		}
	}

}
