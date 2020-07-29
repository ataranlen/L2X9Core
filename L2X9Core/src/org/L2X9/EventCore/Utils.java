package org.L2X9.EventCore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.MinecraftServer;

public class Utils {
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

	public static void sendMessage(CommandSender sender, String string) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
	}

	public static void kickPlayer(Player player, String string) {
		player.kickPlayer(ChatColor.translateAlternateColorCodes('&', string));
	}

	public static void teleportPlayer(Player player, int x, int y, int z) {
		player.teleport(new Location(player.getWorld(), x, y, z));
	}

	public static void teleportPlayer(Player player, double x, double y, double z) {
		player.teleport(new Location(player.getWorld(), x, y, z));
	}

	public static void runSysCommand(String string) {
		String command = string;
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

	public static void runMemClear(String string) {
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), string);
	}

	public static void sendOpMessgge(String message) {
		for (Player online : Bukkit.getOnlinePlayers()) {
			if (online.isOp()) {
				online.sendMessage(ChatColor.translateAlternateColorCodes('&', message));

			}
		}
	}

	public static Player getNearbyPlayers(int i, Location loc) {
		Player plrs = null;
		for (Player nearby : loc.getNearbyPlayers(i)) {
			plrs = nearby;

		}
		return plrs;

	}

	public static Player getElytraFlyers() {
		Player plrs = null;
		for (Player online : Bukkit.getOnlinePlayers()) {
			if (online.isGliding()) {
				plrs = online;

			}
		}
		return plrs;

	}

	public static String getFormattedInterval(long ms) {
		long seconds = ms / 1000L % 60L;
		long minutes = ms / 60000L % 60L;
		long hours = ms / 3600000L % 24L;
		long days = ms / 86400000L;
		return String.format("%dd %02dh %02dm %02ds",
				new Object[] { Long.valueOf(days), Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds) });
	}

	public static String getPrefix() {
		return "[&b&lL2X9&r&3&lCore&r] ";
	}
}