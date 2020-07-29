package org.L2X9.EventCore.Commands;

import org.L2X9.EventCore.Utils;
import org.L2X9.EventCore.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UptimeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Utils.sendMessage(sender, "&6The server has had &r&c" + Utils.getFormattedInterval(System.currentTimeMillis() - Main.starttime) + "&r&6 uptime");
		return true;
	}

}
