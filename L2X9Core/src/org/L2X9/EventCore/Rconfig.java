package org.L2X9.EventCore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Rconfig implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof org.bukkit.command.ConsoleCommandSender) {
			Main.getPlugin().reloadConfig();
			sender.sendMessage(ChatColor.GREEN + "Reloaded config");
		}
		if (sender instanceof org.bukkit.entity.Player && sender.isOp()) {
			Main.getPlugin().reloadConfig();
			sender.sendMessage(ChatColor.GREEN + "Reloaded config");

		}
		return false;
	}
}
