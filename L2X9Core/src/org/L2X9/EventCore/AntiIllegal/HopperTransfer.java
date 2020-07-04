package org.L2X9.EventCore.AntiIllegal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.L2X9.EventCore.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HopperTransfer implements Listener {
	@SuppressWarnings({ "deprecation", "rawtypes", "unused" })
	@EventHandler
	public void onTransfer(InventoryClickEvent event) {
		if (event.getClickedInventory() != null) {
			ItemStack[] var5;
			ItemStack ite = event.getCursor();
			int var4 = (var5 = event.getClickedInventory().getContents()).length;

			for (int var3 = 0; var3 < var4; ++var3) {
				ItemStack item = var5[var3];
				if (item != null) {
					List ublist = Main.allowedUBs;
					String na = ite.getI18NDisplayName();
					if (ublist.contains(String.valueOf(item.getTypeId()))) {
						if (item.getDurability() > item.getType().getMaxDurability()) {
							item.setDurability((short) 0);
						}

						if (item.getDurability() < 0) {
							item.setDurability((short) 0);
						}
					}

					if (item.getAmount() > item.getMaxStackSize()) {
						item.setAmount(item.getMaxStackSize());
					}

					boolean illegalsFound = false;
					List list = Main.blockedids;
					if (list.contains(String.valueOf(item.getTypeId()))) {
						illegalsFound = true;
						event.getInventory().removeItem(new ItemStack[] { item });
					}

					if (item.getEnchantments() != null) {
						Map Enchants = item.getEnchantments();
						Iterator var11 = Enchants.entrySet().iterator();

						label66: while (true) {
							Entry entry;
							Integer lvl;
							do {
								if (!var11.hasNext()) {
									break label66;
								}

								entry = (Entry) var11.next();
								lvl = (Integer) entry.getValue();
							} while (lvl <= ((Enchantment) entry.getKey()).getMaxLevel());

							illegalsFound = true;
							Iterator var14 = item.getEnchantments().keySet().iterator();

							while (var14.hasNext()) {

								Enchantment e = (Enchantment) var14.next();
								item.removeEnchantment(e);

								try {
									if (e.getId() != 18 && e.getId() != 17) {
										item.addEnchantment(e, e.getMaxLevel());
									}
								} catch (Exception var16) {
								}
							}

							ItemMeta m = item.getItemMeta();
							item.setItemMeta(m);
						}
					}

					if (illegalsFound) {
						event.setCancelled(true);

					}
				}
			}
		}
	}
}