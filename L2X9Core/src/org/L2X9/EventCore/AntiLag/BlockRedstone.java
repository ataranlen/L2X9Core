package org.L2X9.EventCore.AntiLag;

import java.io.File;

import org.L2X9.EventCore.API;
import org.L2X9.EventCore.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class BlockRedstone implements Listener {
	public static File lmFile;

	// public static ArrayList<String> possibleLm = new ArrayList();
	@EventHandler
	public void onRedstoneTick(BlockRedstoneEvent event) {
		if (API.getTps() <= Main.getPlugin().getConfig().getInt("Redstone.Disable-TPS")
				&& !(event.getBlock().getType() == Material.TRAPPED_CHEST)) {
			Block block = event.getBlock();
			String fagMachine = "Removed a lagmache at " + block.getLocation().getBlockX() + " "
					+ block.getLocation().getBlockY() + " " + block.getLocation().getBlockZ() + " in world "
					+ block.getLocation().getWorld().getName() + "";
			event.setNewCurrent(0);
			event.getBlock().setType(Material.AIR);
			event.getBlock().getLocation().getWorld().strikeLightning(event.getBlock().getLocation());
			System.out.println(ChatColor.translateAlternateColorCodes('&', "&a" + fagMachine));
			// possibleLm.add(fagMachine);
		}
	}

}

//	public static void createLogFile() {
//		File dir = new File("plugins/L2X9Core");
//		lmFile = new File(dir, "LagMachineLog.yml");
//		if (!(lmFile.exists())) {
//			try {
//				lmFile.createNewFile();
//			} catch (IOException e) {
//			}
//		} else {
//			System.out.println(
//					ChatColor.translateAlternateColorCodes('&', "&3[L2X9Core]&r&a Loaded lagmachine log file!"));
//		}
//	}
//
//	@SuppressWarnings("resource")
//	public void save() {
//		try {
//			BufferedWriter br = new BufferedWriter(new FileWriter("plugins/L2X9Core/LagMachineLog.yml"));
//			possibleLm.forEach(s -> {
//				try {
//					br.append(s);
//					br.newLine();
//					br.flush();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			});
//		} catch (Exception e) {
//		}
//	}
//}
