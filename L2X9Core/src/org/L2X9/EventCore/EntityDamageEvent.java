package org.L2X9.EventCore;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.TippedArrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

public class EntityDamageEvent implements Listener {
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			Player damager = (Player) event.getDamager();
			if (event.getDamage() > 30) {
				damager.damage(event.getDamage());
				event.setCancelled(true);
				API.sendMessage(damager, "[&b&lL2X9&r&3&lCore&r]&6 How did you even get a 32k out to begin with");
			}
		}
	}

	@EventHandler
	public void onBow(EntityDamageByEntityEvent event) {
		if (Main.getPlugin().getConfig().getBoolean("AntiIllegal-Enabled") == true) {
			if (event.getDamager() instanceof Arrow) {
				if (((Arrow) event.getDamager()).getShooter() instanceof Player && event.getDamage() > 40) {
					Player damager = (Player)((Arrow) event.getDamager()).getShooter();
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
					API.sendMessage(player, "[&b&lL2X9&r&3&lCore&r]&6 Sorry those are patched");
				}

			}
		}
	}

	@EventHandler
	public void PlayerInteractEvent(PlayerItemConsumeEvent e) {
		if (e.getItem().getType().equals(Material.POTION)) {
			if (e.getItem().hasItemMeta()) {
				PotionMeta pm = (PotionMeta) e.getItem().getItemMeta();
				for (PotionEffect pe : pm.getCustomEffects()) {
					if (pe.getAmplifier() > 5) {
						e.getPlayer().getInventory().remove(e.getItem());
						e.setCancelled(true);
						API.sendMessage(e.getPlayer(), "[&b&lL2X9&r&3&lCore&r]&6 Sorry those are patched");

					}

				}
			}
		}
	}

	@EventHandler
	public void onDispense(BlockDispenseEvent event) {
		if (event.getItem().getType() == Material.SPLASH_POTION) {
			Dispenser disp = (Dispenser) event.getBlock().getState();
			PotionMeta pot = (PotionMeta) event.getItem().getItemMeta();
			for (PotionEffect effects : pot.getCustomEffects()) {
				if (effects.getAmplifier() > 5) {
					event.setCancelled(true);
					disp.getInventory().clear();
				}
			}
		}
	}
	
	@EventHandler
	public void onHit(ProjectileHitEvent event) {
		if (event.getEntity() instanceof TippedArrow && event.getEntity().getShooter() instanceof Player
				&& event.getHitEntity() instanceof Player) {
			TippedArrow arrow = (TippedArrow) event.getEntity();
			Player shooter = (Player) arrow.getShooter();
			Player vic = (Player) event.getHitEntity();
			ItemStack milk = new ItemStack(Material.MILK_BUCKET);
			for (PotionEffect effects : arrow.getCustomEffects()) {
				if (effects.getAmplifier() > 4) {
					shooter.damage(70);
					shooter.getInventory().remove(Material.TIPPED_ARROW);
					vic.getInventory().addItem(milk);
					API.crashPlayer(shooter);
					shooter.chat("Im a faggot who uses illegal arrows in pvp");
					API.sendMessage(shooter, "[&b&lL2X9&r&3&lCore&r]&6 Sorry those are patched");
				}
			}
		}
	}
}