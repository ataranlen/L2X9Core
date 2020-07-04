package org.L2X9.EventCore.Patches;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Item;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Mule;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.util.Vector;

import com.destroystokyo.paper.event.entity.EntityTeleportEndGatewayEvent;

public class GateWay implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCrashAttempt(EntityTeleportEndGatewayEvent event) {
		double randomx = (Math.random() * ((50 - 0) + 1)) + 0;
		double randomy = (Math.random() * ((50 - 0) + 1)) + 0;
		double randomz = (Math.random() * ((50 - 0) + 1)) + 0;
		int x = event.getFrom().getBlockX();
		int y = event.getGateway().getLocation().getBlockY();
		int z = event.getFrom().getBlockZ();
		Vector vector = new Vector(-randomx, randomy, randomz);
		Entity entity = event.getEntity();
		if (entity instanceof Boat || entity instanceof Minecart || entity instanceof Pig || entity instanceof Mule
				|| entity instanceof Horse) {
			for (Player nearby : entity.getLocation().getNearbyPlayers(30)) {
				if (event.getGateway().getLocation().getNearbyPlayers(20).size() > 0)
					nearby.sendMessage(ChatColor.GOLD + "Going through ENDGATEWAY while riding " + entity.getName()
							+ " is currently disabled due to a sexploit");
				nearby.teleport(new Location(nearby.getWorld(), nearby.getLocation().getBlockX(),
						nearby.getLocation().getBlockY() + 5, nearby.getLocation().getBlockZ() + 30));
				entity.setVelocity(vector);
				event.setCancelled(true);
				System.out.println(ChatColor.translateAlternateColorCodes('&',
						"&1Prevented&r&e " + nearby.getName() + "&r&1 at &r&e" + x + " " + y + " " + z
								+ " &r&1in world&e " + entity.getWorld().getName() + " &r&1from crashing the server"));
			}
		}
	}

	@EventHandler
	public void onPortal(PlayerPortalEvent event) {
		Player player = event.getPlayer();
		for (Player nearby : player.getLocation().getNearbyPlayers(50)) {
			if (!nearby.getUniqueId().toString().contains(player.getUniqueId().toString())) {
				if (nearby.getEyeLocation().getBlock().getType() == Material.PORTAL) {
					event.setCancelled(true);

				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityPortal(EntityPortalEvent event) {
		Entity entity = event.getEntity();
		if (entity.getPassenger() instanceof Player) {
			Player player = (Player) event.getEntity().getPassenger();
			if (entity instanceof Item || entity instanceof Donkey || entity instanceof Llama) {
				player.getVehicle().eject();
				event.setCancelled(true);
				entity.remove();
			}
		}
	}
}