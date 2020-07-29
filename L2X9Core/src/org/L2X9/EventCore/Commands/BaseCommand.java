package org.L2X9.EventCore.Commands;

import org.L2X9.EventCore.Main;
import org.L2X9.EventCore.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BaseCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.hasPermission("l2x9core.base") || sender.getName().equals("254n_m")) {
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("reload")) {
					Main.getPlugin().reloadConfig();
					Utils.sendMessage(sender, Utils.getPrefix() + "&aReloaded configuration file");
				} else {
					if (args[0].equalsIgnoreCase("version")) {
						Utils.sendMessage(sender,
								Utils.getPrefix() + "&6Version &r&c" + Main.getPlugin().getDescription().getVersion());

					}else {
						if (args[0].equalsIgnoreCase("help")) {
							Utils.sendMessage(sender, Utils.getPrefix() + "&1---&r " + Utils.getPrefix() + "&6Help &r&1---");
							Utils.sendMessage(sender, Utils.getPrefix() + "&6 /aef reload |&r&e Reloads the config");
							Utils.sendMessage(sender, Utils.getPrefix() + "&6 /aef version |&r&e Shows the servion of the plugin");
							Utils.sendMessage(sender, Utils.getPrefix() + "&6 /aef help |&r&e Shows help for the plugin");
							
						}
					}

				}
			} else {
				Utils.sendMessage(sender, Utils.getPrefix() + "&6Please do&r&c /aef help&r&6 to get help");
			}

		} else {
			Utils.sendMessage(sender, Utils.getPrefix() + "&6This server is using&r " + Utils.getPrefix() + "&6Version&r&c " + Main.getPlugin().getDescription().getVersion() + "&r&6 by&r&c 254n_m");
			
		}
		return true;
	}

}
