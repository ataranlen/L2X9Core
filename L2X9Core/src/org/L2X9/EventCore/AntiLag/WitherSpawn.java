package org.L2X9.EventCore.AntiLag;

import org.L2X9.EventCore.API;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class WitherSpawn implements Listener {
	@EventHandler
	public void onWitherSpawn(EntitySpawnEvent event) {
		if (event.getEntity() instanceof Wither) {
			if (API.getTps() <= 16) {
				event.setCancelled(true);

			}
		}
	}
}
