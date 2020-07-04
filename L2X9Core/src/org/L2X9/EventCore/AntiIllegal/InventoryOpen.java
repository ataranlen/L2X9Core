package org.L2X9.EventCore.AntiIllegal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.server.v1_12_R1.ItemArmor;

import org.L2X9.EventCore.Main;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryOpen implements Listener {
	@SuppressWarnings({ "deprecation", "rawtypes" })
	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent event) {
		if (Main.getPlugin().getConfig().getBoolean("AntiIllegal-Enabled") == true) {
			ItemStack[] var5;
			int var4 = (var5 = event.getPlayer().getInventory().getArmorContents()).length;

			ItemStack item;
			int var3;
			for (var3 = 0; var3 < var4; ++var3) {
				item = var5[var3];
				if (item != null && CraftItemStack.asNMSCopy(item).getItem() instanceof ItemArmor) {
					if (item.getDurability() > item.getType().getMaxDurability()) {
						item.setDurability((short) 0);
					}

					if (item.getDurability() < 0) {
						item.setDurability((short) 0);
					}
				}
			}

			var4 = (var5 = event.getPlayer().getInventory().getContents()).length;

			List ublist;
			List list;
			Map Enchants;
			Entry entry;
			Iterator var10;
			Integer lvl;
			Enchantment e;
			Iterator var13;
			ItemMeta m;
			label149: for (var3 = 0; var3 < var4; ++var3) {
				item = var5[var3];
				if (item != null) {
					ublist = Main.allowedUBs;
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

					list = Main.blockedids;
					if (list.contains(String.valueOf(item.getTypeId()))) {
						event.getPlayer().getInventory().removeItem(new ItemStack[] { item });
					}

					if (item.getEnchantments() != null) {
						Enchants = item.getEnchantments();
						var10 = Enchants.entrySet().iterator();

						while (true) {
							do {
								if (!var10.hasNext()) {
									continue label149;
								}

								entry = (Entry) var10.next();
								lvl = (Integer) entry.getValue();
							} while (lvl <= ((Enchantment) entry.getKey()).getMaxLevel());

							var13 = item.getEnchantments().keySet().iterator();

							while (var13.hasNext()) {
								e = (Enchantment) var13.next();
								item.removeEnchantment(e);

								try {
									if (e.getId() != 18 && e.getId() != 17) {
										item.addEnchantment(e, e.getMaxLevel());
									}
								} catch (Exception var16) {
								}
							}

							m = item.getItemMeta();
							item.setItemMeta(m);
						}
					}
				}
			}

			var4 = (var5 = event.getView().getTopInventory().getContents()).length;

			label121: for (var3 = 0; var3 < var4; ++var3) {
				item = var5[var3];
				if (item != null) {
					ublist = Main.allowedUBs;
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

					list = Main.blockedids;
					if (list.contains(String.valueOf(item.getTypeId()))) {
						event.getPlayer().getOpenInventory().getTopInventory().removeItem(new ItemStack[] { item });
					}

					if (item.getEnchantments() != null) {
						Enchants = item.getEnchantments();
						var10 = Enchants.entrySet().iterator();

						while (true) {
							do {
								if (!var10.hasNext()) {
									continue label121;
								}

								entry = (Entry) var10.next();
								lvl = (Integer) entry.getValue();
							} while (lvl <= ((Enchantment) entry.getKey()).getMaxLevel());

							var13 = item.getEnchantments().keySet().iterator();

							while (var13.hasNext()) {
								e = (Enchantment) var13.next();
								item.removeEnchantment(e);

								try {
									if (e.getId() != 18 && e.getId() != 17) {
										item.addEnchantment(e, e.getMaxLevel());
									}
								} catch (Exception var15) {
								}
							}

							m = item.getItemMeta();
							item.setItemMeta(m);
						}
					}
				}
			}

		}
	}
}
