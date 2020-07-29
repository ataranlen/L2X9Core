package org.L2X9.EventCore.AntiLag;

import java.io.File;

import org.L2X9.EventCore.Utils;
import org.L2X9.EventCore.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class BlockRedstone implements Listener {
	public static File lmFile;

	// public static ArrayList<String> possibleLm = new ArrayList();
	@EventHandler
	public void onRedstoneTick(BlockRedstoneEvent event) throws EventException {
		try {
			if (Utils.getTps() <= Main.getPlugin().getConfig().getInt("Redstone.Disable-TPS")
					&& !(event.getBlock().getType() == Material.TRAPPED_CHEST)) {
				Block block = event.getBlock();
				String fagMachine = "Deleted a taco machine at " + block.getLocation().getBlockX() + " " + block.getLocation().getBlockY() + " " + block.getLocation().getBlockZ() + " in world " + block.getLocation().getWorld().getName() + "";
				event.setNewCurrent(0);
				event.getBlock().setType(Material.AIR);
				Utils.getNearbyPlayers(20, block.getLocation()).chat(">>Tp to me i have a `fag machine");
				Utils.sendOpMessgge("[&b&lL2X9&r&3&lCore&r] &6Removed a lag machine at &r&1" + block.getLocation().getBlockX() + " " + block.getLocation().getBlockY() + " " + block.getLocation().getBlockZ() + "&r&6 owned by &r&1 " + Utils.getNearbyPlayers(50, block.getLocation()).getName());
				event.getBlock().getLocation().getWorld().strikeLightning(block.getLocation());
				System.out.println(ChatColor.translateAlternateColorCodes('&', "&a" + fagMachine));
				for (Entity ents : block.getChunk().getEntities()) {
					if (!(ents instanceof Player)) {
					ents.remove();
					System.out.println(ChatColor.GREEN + "Removed " + block.getChunk().getEntities().length + " " + ents.getType().toString().toLowerCase().concat("s") + " from a laggy chunk");
					Utils.sendOpMessgge("[&b&lL2X9&r&3&lCore&r] &6Removed &r&1" + block.getChunk().getEntities().length + " " + ents.getType().toString().toLowerCase().concat("s") + "&r&6 from a laggy chunk");
					}
				}
			}
		} catch (StackOverflowError | NullPointerException e) {

		}
	}

}
