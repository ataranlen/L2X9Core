package org.L2X9.EventCore.Patches;

import java.util.Arrays;
import java.util.List;

import org.L2X9.EventCore.API;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.Item;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.TileEntityShulkerBox;

public class BookBan implements Listener {
	@SuppressWarnings("rawtypes")
	private static final List shulkerList = Arrays.asList(Item.getById(219), Item.getById(220), Item.getById(221),
			Item.getById(222), Item.getById(223), Item.getById(224), Item.getById(225), Item.getById(226),
			Item.getById(227), Item.getById(228), Item.getById(229), Item.getById(230), Item.getById(231),
			Item.getById(232), Item.getById(233), Item.getById(234));

	@EventHandler
	public void Join(PlayerJoinEvent e) {
		ItemStack[] var5;
		int var4 = (var5 = e.getPlayer().getInventory().getContents()).length;

		for (int var3 = 0; var3 < var4; ++var3) {
			ItemStack item = var5[var3];
			if (item != null && shulkerList.contains(CraftItemStack.asNMSCopy(item).getItem())) {
				net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
				NBTTagCompound shulkerNBT = getShulkerNBT(nmsStack);
				if (nmsStack != null && shulkerNBT != null) {
					TileEntityShulkerBox fakeShulker = new TileEntityShulkerBox();
					fakeShulker.load(shulkerNBT);
					@SuppressWarnings("unused")
					String customName = "container.shulkerBox";
					@SuppressWarnings("unused")
					boolean hasCustomName = false;
					if (shulkerNBT.hasKeyOfType("CustomName", 8)) {
						customName = shulkerNBT.getString("CustomName");
						hasCustomName = true;
					}
					int bookamt = 0;
					for (int i = 0; i < 27; ++i) {
						net.minecraft.server.v1_12_R1.ItemStack iStack = fakeShulker.getItem(i);
						if (iStack.getItem().equals(Item.getById(387))) {
							bookamt += iStack.getCount();
						}
					}
					if (bookamt >= 2) {
						e.getPlayer().getInventory().remove(item);
						API.sendMessage(e.getPlayer(), "[&b&lL2X9&r&3&lCore&r]&6 You have been unbookbanned");
					}
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
