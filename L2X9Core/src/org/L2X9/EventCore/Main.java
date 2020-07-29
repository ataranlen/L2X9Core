package org.L2X9.EventCore;

import java.util.Arrays;
import java.util.List;

import org.L2X9.EventCore.AntiIllegal.HopperTransfer;
import org.L2X9.EventCore.AntiIllegal.InventoryClose;
import org.L2X9.EventCore.AntiIllegal.InventoryOpen;
import org.L2X9.EventCore.AntiIllegal.ItemPickup;
import org.L2X9.EventCore.AntiIllegal.PlaceEvent;
import org.L2X9.EventCore.AntiLag.BlockRedstone;
import org.L2X9.EventCore.AntiLag.Elytra;
import org.L2X9.EventCore.AntiLag.WitherSpawn;
import org.L2X9.EventCore.Commands.CrashCommand;
import org.L2X9.EventCore.Commands.OpenInv;
import org.L2X9.EventCore.Commands.SayCommand;
import org.L2X9.EventCore.Commands.SpeedCommand;
import org.L2X9.EventCore.Commands.UUidCommand;
import org.L2X9.EventCore.Commands.UptimeCommand;
import org.L2X9.EventCore.Commands.BaseCommand;
import org.L2X9.EventCore.Events.BlockPlace;
import org.L2X9.EventCore.Events.CommandEvent;
import org.L2X9.EventCore.Events.JoinEvent;
import org.L2X9.EventCore.Events.MoveEvent;
import org.L2X9.EventCore.Patches.BookBan;
import org.L2X9.EventCore.Patches.BucketEvent;
import org.L2X9.EventCore.Patches.ChinkBan;
import org.L2X9.EventCore.Patches.EntityDamageEvent;
import org.L2X9.EventCore.Patches.GateWay;
import org.L2X9.EventCore.Patches.LightingLag;
import org.L2X9.EventCore.Patches.Offhand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static String[] idArray = new String[] { "7", "166", "52", "137", "119", "120", "383", "122" };
	public static String[] ubArray = new String[] { "310", "311", "312", "313", "276", "277", "278", "279", "293","259", "261", "443" };
	public static List<String> blockedids;
	public static List<String> allowedUBs;
	static {
		blockedids = Arrays.asList(idArray);
		allowedUBs = Arrays.asList(ubArray);
	}

	public static Main getPlugin() {
		return (Main) getPlugin(Main.class);
	}
	public static long starttime;
	public void onEnable() {
		this.saveDefaultConfig();
		starttime = System.currentTimeMillis();
		getLogger().info(ChatColor.translateAlternateColorCodes('&', "&3[L2X9Core]&r&a enabled"));
		getServer().getPluginManager().registerEvents(new BlockPlace(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new Offhand(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new GateWay(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new BookBan(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new ChinkBan(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new MoveEvent(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new HopperTransfer(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new InventoryOpen(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new InventoryClose(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new ItemPickup(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new PlaceEvent(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new CommandEvent(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new JoinEvent(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new Elytra(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new EntityDamageEvent(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new BlockRedstone(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new WitherSpawn(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new LightingLag(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new BucketEvent(), (Plugin) this);
		getCommand("say").setExecutor(new SayCommand());
		getCommand("crash").setExecutor(new CrashCommand());
		getCommand("open").setExecutor(new OpenInv());
		getCommand("speed").setExecutor(new SpeedCommand());
		getCommand("uuid").setExecutor(new UUidCommand());
		getCommand("uptime").setExecutor(new UptimeCommand());
		getCommand("aef").setExecutor(new BaseCommand());
	}

	public void onDisable() {
		getLogger().info("AnarchyExploitFixer disabled");
	}
}