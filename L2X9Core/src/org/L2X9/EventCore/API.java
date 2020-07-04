package org.L2X9.EventCore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.MinecraftServer;

public class API {
	@SuppressWarnings("deprecation")
	public static double getTps() {
		return (MinecraftServer.getServer().recentTps[0]);

	}

	public static void crashPlayer(Player player) {
		for (int i = 0; i < 100; i++) {
			player.spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), Integer.MAX_VALUE, 1, 1, 1);
		}
	}

	public static void sendMessage(Player player, String string) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', string));

	}

	public static void kickPlayer(Player player, String string) {
		player.kickPlayer(ChatColor.translateAlternateColorCodes('&', string));
	}

	public static void teleportPlayer(Player player, int x, int y, int z) {
		player.teleport(new Location(player.getWorld(), x, y, z));
	}

	public static void runSysCommand(String s) {
		String command = s;

		try {
			Process process = Runtime.getRuntime().exec(command);

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			reader.close();

		} catch (IOException e) {
		}
	}

	public static void runMcCommand(String s) {
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), s);
	}
}
