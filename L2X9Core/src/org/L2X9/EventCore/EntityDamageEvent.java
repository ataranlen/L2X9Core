package org.L2X9.EventCore;

import java.io.IOException;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class EntityDamageEvent implements Listener {
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			Player damager = (Player) event.getDamager();
			if (event.getDamage() > 30) {
				damager.damage(event.getDamage());
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBow(EntityDamageByEntityEvent event) {
		if (Main.getPlugin().getConfig().getBoolean("AntiIllegal-Enabled") == true) {
			if (event.getDamager() instanceof Arrow) {
				if (((Arrow) event.getDamager()).getShooter() instanceof Player && event.getDamage() > 40) {
					Player damager = (Player) (((Arrow) event.getDamager()).getShooter());
					damager.damage(event.getDamage());
					event.setCancelled(true);

				}
			}
		}
	}

	@EventHandler
	public void onThrow(PotionSplashEvent event) throws IOException {
		if (event.getPotion().getShooter() instanceof Player) {
			Player player = (Player) event.getPotion().getShooter();
			ItemStack pot = event.getPotion().getItem();
			for (PotionEffect effects : event.getPotion().getEffects()) {
				if (effects.getAmplifier() > 5) {
					event.setCancelled(true);
					player.getInventory().remove(pot);
					API.sendMessage(player, "&3[L2X9Core]&r&6 no illegal potions my guy");
				}

			}
		}
	}
}