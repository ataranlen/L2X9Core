package org.L2X9.EventCore;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

public class BucketEvent implements Listener {

	@EventHandler
	public void onBucket(PlayerBucketEmptyEvent event) {
		int yNeg = event.getBlockClicked().getLocation().getBlockY() - 1;
		int yPos = event.getBlockClicked().getLocation().getBlockY() + 1;
		int xNeg = event.getBlockClicked().getLocation().getBlockX() - 1;
		int zNeg = event.getBlockClicked().getLocation().getBlockZ() - 1;
		int xPos = event.getBlockClicked().getLocation().getBlockX() + 1;
		int zPos = event.getBlockClicked().getLocation().getBlockZ() + 1;
		int x = event.getBlockClicked().getLocation().getBlockX();
		int z = event.getBlockClicked().getLocation().getBlockZ();
		int y = event.getBlockClicked().getLocation().getBlockY();
		if (event.getBlockClicked().getType() == Material.ENDER_PORTAL_FRAME
				|| event.getBlockClicked().getLocation().getWorld().getBlockAt(x, yNeg, z)
						.getType() == Material.ENDER_PORTAL
				|| event.getBlockClicked().getLocation().getWorld().getBlockAt(x, yPos, z)
						.getType() == Material.ENDER_PORTAL
				|| event.getBlockClicked().getLocation().getWorld().getBlockAt(x, y, zNeg)
						.getType() == Material.ENDER_PORTAL
				|| event.getBlockClicked().getLocation().getWorld().getBlockAt(xNeg, y, z)
						.getType() == Material.ENDER_PORTAL
				|| event.getBlockClicked().getLocation().getWorld().getBlockAt(x, y, zPos)
						.getType() == Material.ENDER_PORTAL
				|| event.getBlockClicked().getLocation().getWorld().getBlockAt(xPos, y, z)
						.getType() == Material.ENDER_PORTAL) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onDispense(BlockDispenseEvent event) {
		int yNeg = event.getBlock().getLocation().getBlockY() - 1;
		int yPos = event.getBlock().getLocation().getBlockY() + 1;
		int xNeg = event.getBlock().getLocation().getBlockX() - 1;
		int zNeg = event.getBlock().getLocation().getBlockZ() - 1;
		int xPos = event.getBlock().getLocation().getBlockX() + 1;
		int zPos = event.getBlock().getLocation().getBlockZ() + 1;
		int x = event.getBlock().getLocation().getBlockX();
		int z = event.getBlock().getLocation().getBlockZ();
		int y = event.getBlock().getLocation().getBlockY();
		if (event.getBlock().getType() == Material.ENDER_PORTAL_FRAME
				|| event.getBlock().getLocation().getWorld().getBlockAt(x, yNeg, z).getType() == Material.ENDER_PORTAL
				|| event.getBlock().getLocation().getWorld().getBlockAt(x, yPos, z).getType() == Material.ENDER_PORTAL
				|| event.getBlock().getLocation().getWorld().getBlockAt(x, y, zNeg).getType() == Material.ENDER_PORTAL
				|| event.getBlock().getLocation().getWorld().getBlockAt(xNeg, y, z).getType() == Material.ENDER_PORTAL
				|| event.getBlock().getLocation().getWorld().getBlockAt(x, y, zPos).getType() == Material.ENDER_PORTAL
				|| event.getBlock().getLocation().getWorld().getBlockAt(xPos, y, z)
						.getType() == Material.ENDER_PORTAL) {
			event.setCancelled(true);

		}
	}
}