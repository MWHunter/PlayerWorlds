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
package cz._heropwp.playerworldspro.d;

import cz._heropwp.playerworldspro.Main;
import io.papermc.lib.PaperLib;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class BasicManager {
    private final Main a;
    private String b;
    private final String c;

    public BasicManager(Main main) {
        this.a = main;
        this.a();
        this.c = "§8[§6PlayerWorldsPro§8] §r";
    }

    public void a() {
        this.b = this.a.getConfig().getString("Messages.Prefix").replace("&", "§");
    }

    public boolean b() {
        return this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Lobby");
    }

    public Location c() {
        if (this.b()) {
            String string = this.a.E().a(ConfigManager.dataOrPlayers.DATA).getString("Lobby");
            String[] arrstring = string.split(";");
            World world = Bukkit.getWorld((String)arrstring[0]);
            double d2 = Double.parseDouble(arrstring[1]);
            double d3 = Double.parseDouble(arrstring[2]);
            double d4 = Double.parseDouble(arrstring[3]);
            float f2 = Float.parseFloat(arrstring[4]);
            float f3 = Float.parseFloat(arrstring[5]);
            Location location = new Location(world, d2, d3, d4, f2, f3);
            return location;
        }
        return null;
    }

    public void a(Player player, Location location) {
        if (this.a.getConfig().getBoolean("Basic.Async.Paper")) {
            PaperLib.teleportAsync((Entity)player, location);
        } else {
            player.teleport(location);
        }
    }

    public Main d() {
        return this.a;
    }

    public String e() {
        return this.b;
    }

    public String f() {
        return this.c;
    }
}

