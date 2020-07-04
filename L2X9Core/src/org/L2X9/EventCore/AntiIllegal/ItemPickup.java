package org.L2X9.EventCore.AntiIllegal;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.server.v1_12_R1.Item;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.TileEntityShulkerBox;

import org.L2X9.EventCore.Main;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("deprecation")
public class ItemPickup implements Listener {
	@SuppressWarnings("rawtypes")
	private static final List shulkerList = Arrays.asList(Item.getById(219), Item.getById(220), Item.getById(221),
			Item.getById(222), Item.getById(223), Item.getById(224), Item.getById(225), Item.getById(226),
			Item.getById(227), Item.getById(228), Item.getById(229), Item.getById(230), Item.getById(231),
			Item.getById(232), Item.getById(233), Item.getById(234));

	@SuppressWarnings({ "rawtypes", "unused" })
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		if (Main.getPlugin().getConfig().getBoolean("AntiIllegal-Enabled") == true) {
			ItemStack item = e.getItem().getItemStack();
			if (item != null) {
				List ublist = Main.allowedUBs;
				if (ublist.contains(String.valueOf(item.getTypeId()))) {
					if (item.getDurability() > item.getType().getMaxDurability()) {
						item.setDurability((short) 0);
					}

					if (item.getDurability() < 0) {
						item.setDurability((short) 0);
					}
				}

				int bookbanShulkers = 0;
				ItemStack[] var8;
				int var7 = (var8 = e.getPlayer().getInventory().getContents()).length;

				for (int var6 = 0; var6 < var7; ++var6) {
					ItemStack i = var8[var6];
					if (shulkerList.contains(CraftItemStack.asNMSCopy(i).getItem())) {
						net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(i);
						NBTTagCompound shulkerNBT = getShulkerNBT(nmsStack);
						if (nmsStack != null && shulkerNBT != null) {
							TileEntityShulkerBox fakeShulker = new TileEntityShulkerBox();
							fakeShulker.load(shulkerNBT);
							String customName = "container.shulkerBox";
							boolean hasCustomName = false;
							if (shulkerNBT.hasKeyOfType("CustomName", 8)) {
								customName = shulkerNBT.getString("CustomName");
								hasCustomName = true;
							}

							int bookamt = 0;

							for (int x = 0; x < 27; ++x) {
								net.minecraft.server.v1_12_R1.ItemStack iStack = fakeShulker.getItem(x);
								if (iStack.getItem().equals(Item.getById(387))) {
									bookamt += iStack.getCount();
								}
							}

							if (bookamt > 0) {
								++bookbanShulkers;
							}
						}
					}
				}

				if (bookbanShulkers >= 2) {
					e.setCancelled(true);
				}

				boolean illegalsFound = false;
				if (Main.blockedids.contains(String.valueOf(item.getTypeId()))) {
					illegalsFound = true;
					item.setTypeId(0);
					item.setAmount(0);
				}

				if (item.getEnchantments() != null) {
					Map Enchants = item.getEnchantments();
					Iterator var21 = Enchants.entrySet().iterator();

					label79: while (true) {
						Entry entry;
						Integer lvl;
						do {
							if (!var21.hasNext()) {
								break label79;
							}

							entry = (Entry) var21.next();
							lvl = (Integer) entry.getValue();
						} while (lvl <= ((Enchantment) entry.getKey()).getMaxLevel());

						illegalsFound = true;
						Iterator var25 = item.getEnchantments().keySet().iterator();

						while (var25.hasNext()) {
							Enchantment en = (Enchantment) var25.next();
							item.removeEnchantment(en);

							try {
								if (en.getId() != 18 && en.getId() != 17) {
									item.addEnchantment(en, en.getMaxLevel());
								}
							} catch (Exception var17) {
							}
						}

						ItemMeta m = item.getItemMeta();
						item.setItemMeta(m);
					}
				}

				if (illegalsFound) {
					e.getItem().remove();
					e.setCancelled(true);
				}
			}

		}
	}

	public static NBTTagCompound getShulkerNBT(net.minecraft.server.v1_12_R1.ItemStack stack) {
		NBTTagCompound compound = stack.hasTag() ? stack.getTag() : new NBTTagCompound();
		if (compound != null && compound.hasKeyOfType("BlockEntityTag", 10)) {
			NBTTagCompound tags = compound.getCompound("BlockEntityTag");
			return tags.hasKeyOfType("Items", 9) ? tags : null;
		} else {
			return null;
		}
	}
}
