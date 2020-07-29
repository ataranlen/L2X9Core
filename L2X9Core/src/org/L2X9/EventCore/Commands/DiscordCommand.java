package org.L2X9.EventCore.Commands;

import org.L2X9.EventCore.Utils;
import org.L2X9.EventCore.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Utils.sendMessage(sender, Main.getPlugin().getConfig().getString("Discord"));	
		return true;
	}

}
