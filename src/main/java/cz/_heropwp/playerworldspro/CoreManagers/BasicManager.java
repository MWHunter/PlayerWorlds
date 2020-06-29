/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 */
package cz._heropwp.playerworldspro.CoreManagers;

import cz._heropwp.playerworldspro.Main;
import io.papermc.lib.PaperLib;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class BasicManager {
    private static String pluginPrefix = Main.getPlugin().getConfig().getString("Messages.Prefix").replace("&", "§");
    private static final String c = "§8[§6PlayerWorldsPro§8] §r";

    public static void a() {
        pluginPrefix = Main.getPlugin().getConfig().getString("Messages.Prefix").replace("&", "§");
    }

    public static boolean b() {
        return ConfigManager.getDataConfig().contains("Lobby");
    }

    public static Location c() {
        if (b()) {
            String string = ConfigManager.getDataConfig().getString("Lobby");
            String[] arrstring = string.split(";");
            World world = Bukkit.getWorld((String)arrstring[0]);
            double d2 = Double.parseDouble(arrstring[1]);
            double d3 = Double.parseDouble(arrstring[2]);
            double d4 = Double.parseDouble(arrstring[3]);
            float f2 = Float.parseFloat(arrstring[4]);
            float f3 = Float.parseFloat(arrstring[5]);
            return new Location(world, d2, d3, d4, f2, f3);
        }
        return null;
    }

    public static void a(Player player, Location location) {
        if (Main.getPlugin().getConfig().getBoolean("Basic.Async.Paper")) {
            PaperLib.teleportAsync((Entity)player, location);
        } else {
            player.teleport(location);
        }
    }

    public static String getPluginPrefix() {
        return pluginPrefix;
    }

    public static String f() {
        return c;
    }
}

