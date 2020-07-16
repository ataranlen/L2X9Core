package org.L2X9.EventCore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SayCommand implements CommandExecutor {
	@Override
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    if (sender.hasPermission("SayCommand.say") && cmd.getName().equalsIgnoreCase("say")) {
	      if (args.length == 0) {
	        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Message cannot be empty!"));
	        return true;
	      } 
	      StringBuilder str = new StringBuilder();
	      for (int i = 0; i < args.length; i++)
	        str.append(String.valueOf(args[i]) + " "); 
	      String s = str.toString();
	      String coloredString = ChatColor.translateAlternateColorCodes('&', s);
	      String prefix = ChatColor.translateAlternateColorCodes('&', "[" + Bukkit.getServerName() + "] ");
	      Bukkit.broadcastMessage(prefix.concat(coloredString));
	    } 
	    return true;
	  }
	}