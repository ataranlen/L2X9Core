package org.L2X9.EventCore.AntiIllegal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.L2X9.EventCore.Main;
import org.bukkit.block.BlockState;
import org.bukkit.block.ShulkerBox;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_12_R1.ItemArmor;

public class PlaceEvent implements Listener {
	@SuppressWarnings({ "deprecation", "rawtypes" })
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (Main.getPlugin().getConfig().getBoolean("AntiIllegal-Enabled") == true) {
			ItemStack placed = e.getItemInHand();
			BlockState state = e.getBlockPlaced().getState();
			if (placed.getAmount() > placed.getMaxStackSize()) {
				placed.setAmount(placed.getMaxStackSize());
			}

			ItemStack[] var7;
			int var6 = (var7 = e.getPlayer().getInventory().getArmorContents()).length;

			ItemStack item;
			int var5;
			for (var5 = 0; var5 < var6; ++var5) {
				item = var7[var5];
				if (item != null && CraftItemStack.asNMSCopy(item).getItem() instanceof ItemArmor) {
					if (item.getDurability() > item.getType().getMaxDurability()) {
						item.setDurability((short) 0);
					}

					if (item.getDurability() < 0) {
						item.setDurability((short) 0);
					}
				}
			}

			if (placed.getType() != null && Main.blockedids.contains(String.valueOf(placed.getTypeId()))) {
				var6 = (var7 = e.getPlayer().getInventory().getContents()).length;

				for (var5 = 0; var5 < var6; ++var5) {
					item = var7[var5];
					if (item != null) {
						if (item.getAmount() > item.getMaxStackSize()) {
							item.setAmount(item.getMaxStackSize());
						}

						List list = Main.blockedids;
						if (list.contains(String.valueOf(item.getTypeId()))) {
							e.getPlayer().getInventory().removeItem(new ItemStack[] { item });
						}
					}
				}

				e.setCancelled(true);
			}

			if (placed.getType() != null && state instanceof ShulkerBox) {
				BlockStateMeta meta = (BlockStateMeta) placed.getItemMeta();
				ShulkerBox box = (ShulkerBox) meta.getBlockState();
				boolean foundIllegals = false;
				ItemStack[] var10;
				int var9 = (var10 = box.getInventory().getContents()).length;

				for (int var23 = 0; var23 < var9; ++var23) {
					ItemStack i = var10[var23];
					if (i != null) {
						if (i.getType().isItem() && !i.getType().isEdible() && !i.getType().isBlock()
								&& i.getTypeId() != 397) {
							if (i.getDurability() > i.getType().getMaxDurability()) {
								i.setDurability((short) 0);
							}

							if (i.getDurability() < 0) {
								i.setDurability((short) 0);
							}
						}

						if (Main.blockedids.contains(String.valueOf(i.getTypeId()))) {
							foundIllegals = true;
							i.setAmount(0);
							box.getInventory().remove(i);
						}

						if (i.getEnchantments() != null) {
							Map Enchants = i.getEnchantments();
							Iterator var13 = Enchants.entrySet().iterator();

							label113: while (true) {
								Entry entry;
								Integer lvl;
								do {
									if (!var13.hasNext()) {
										break label113;
									}

									entry = (Entry) var13.next();
									lvl = (Integer) entry.getValue();
								} while (lvl <= ((Enchantment) entry.getKey()).getMaxLevel());

								foundIllegals = true;
								Iterator var16 = i.getEnchantments().keySet().iterator();

								while (var16.hasNext()) {
									Enchantment en = (Enchantment) var16.next();
									i.removeEnchantment(en);

									try {
										if (en.getId() != 18 && en.getId() != 17) {
											i.addEnchantment(en, en.getMaxLevel());
										}
									} catch (Exception var18) {
									}
								}

								ItemMeta m = i.getItemMeta();
								i.setItemMeta(m);
							}
						}

						if (i.getItemMeta() instanceof BlockStateMeta) {
							BlockStateMeta im = (BlockStateMeta) i.getItemMeta();
							if (im.getBlockState() instanceof ShulkerBox) {
								foundIllegals = true;
								i.setAmount(0);
								box.getInventory().remove(i);
							}
						}

						if (i.getAmount() > i.getMaxStackSize()) {
							foundIllegals = true;
							i.setAmount(i.getMaxStackSize());
						}
					}
				}

				meta.setBlockState(box);
				placed.setItemMeta(meta);
				if (foundIllegals) {
					e.setCancelled(true);
				}
			}

		}
	}
}
