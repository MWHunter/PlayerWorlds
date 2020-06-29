/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Difficulty
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.WorldBorder
 *  org.bukkit.command.CommandSender
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerChangedWorldEvent
 *  org.bukkit.event.player.PlayerJoinEvent
 *  org.bukkit.event.player.PlayerPortalEvent
 *  org.bukkit.event.player.PlayerQuitEvent
 *  org.bukkit.event.player.PlayerRespawnEvent
 *  org.bukkit.event.player.PlayerTeleportEvent
 *  org.bukkit.event.player.PlayerTeleportEvent$TeleportCause
 *  org.bukkit.event.world.WorldInitEvent
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitTask
 */
package cz._heropwp.playerworldspro.EventsListener;

import cz._heropwp.playerworldspro.CoreManagers.BasicManager;
import cz._heropwp.playerworldspro.CoreManagers.WorldManager;
import cz._heropwp.playerworldspro.GUI.GUI_Main;
import cz._heropwp.playerworldspro.GUI.GUI_Settings;
import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.CoreManagers.ConfigManager;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.plugin.Plugin;

public class BasicEvents
implements Listener {
    private final HashMap<String, Long> b = new HashMap<>();

    @EventHandler
    public void a(PlayerJoinEvent playerJoinEvent) {
        Player player = playerJoinEvent.getPlayer();
        World world = player.getWorld();
        String string = Main.getPlugin().getConfig().getString("Basic.World-Prefix");
        if (world.getName().startsWith(string)) {
            String string2 = world.getName().split(string)[1];
            if (WorldManager.c(string2)) {
                player.setGameMode(GameMode.valueOf(WorldManager.j(string2)));
            }
        }
    }

    @EventHandler
    public void a(PlayerQuitEvent playerQuitEvent) {
        Player player = playerQuitEvent.getPlayer();
        GUI_Main.a(player.getName(), true);
        GUI_Settings.b().remove(player.getName());
        this.b.remove(player.getName());
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void a(PlayerRespawnEvent playerRespawnEvent) {
        Player player = playerRespawnEvent.getPlayer();
        if (Main.getPlugin().getConfig().getBoolean("Basic.Respawn")) {
            World world = player.getWorld();
            String string = Main.getPlugin().getConfig().getString("Basic.World-Prefix");
            if (world.getName().startsWith(string)) {
                String string2 = world.getName().split(string)[1];
                if (WorldManager.c(string2)) {
                    if (ConfigManager.getDataConfig().getStringList("Worlds." + string2 + ".Banned").contains(player.getName())) {
                        player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Banned-Teleport").replace("&", "§"));
                        return;
                    }
                    if (WorldManager.p(string2).equals("PRIVATE") && !WorldManager.c(player, string2)) {
                        player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Access.Only-For-Members").replace("&", "§"));
                        return;
                    }
                    Location location = WorldManager.a(string2);
                    if (!this.a(location)) {
                        playerRespawnEvent.setRespawnLocation(location);
                    }
                }
            }
        }
    }

    private boolean a(Location location) {
        WorldBorder worldBorder = location.getWorld().getWorldBorder();
        double d2 = worldBorder.getSize() / 2.0;
        Location location2 = worldBorder.getCenter();
        double d3 = location.getX() - location2.getX();
        double d4 = location.getZ() - location2.getZ();
        return d3 > d2 || -d3 > d2 || d4 > d2 || -d4 > d2;
    }

    @EventHandler
    public void a(WorldInitEvent worldInitEvent) {
        World world = worldInitEvent.getWorld();
        String string = Main.getPlugin().getConfig().getString("Basic.World-Prefix");
        if (world.getName().startsWith(string)) {
            String string2 = world.getName().split(string)[1];
            if (WorldManager.c(string2)) {
                worldInitEvent.getWorld().setKeepSpawnInMemory(Main.getPlugin().getConfig().getBoolean("Basic.Keep-Spawn-In-Memory"));
                world.setDifficulty(Difficulty.valueOf(WorldManager.f(string2)));
            }
        }
    }

    @EventHandler
    public void a(PlayerTeleportEvent playerTeleportEvent) {
        World world = playerTeleportEvent.getTo().getWorld();
        String string = Main.getPlugin().getConfig().getString("Basic.World-Prefix");
        if (world.getName().startsWith(string)) {
            String string2 = world.getName().split(string)[1];
            if (WorldManager.c(string2)) {
                Player player = playerTeleportEvent.getPlayer();
                if (playerTeleportEvent.getCause() == PlayerTeleportEvent.TeleportCause.SPECTATE) {
                    playerTeleportEvent.setCancelled(!playerTeleportEvent.getFrom().getWorld().getName().equals(playerTeleportEvent.getTo().getWorld().getName()));
                    return;
                }
                if (WorldManager.d(player, string2)) {
                    player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Banned-Teleport").replace("&", "§"));
                    playerTeleportEvent.setCancelled(true);
                    if (playerTeleportEvent.getFrom().getWorld() == world) {
                        Bukkit.getScheduler().runTaskLater(Main.getPlugin(), () -> BasicManager.a(player, BasicManager.c()), 1L);
                    }
                } else if (WorldManager.p(string2).equals("PRIVATE") && !WorldManager.c(player, string2)) {
                    player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Access.Only-For-Members").replace("&", "§"));
                    playerTeleportEvent.setCancelled(true);
                    if (playerTeleportEvent.getFrom().getWorld() == world) {
                        Bukkit.getScheduler().runTaskLater(Main.getPlugin(), () -> BasicManager.a(player, BasicManager.c()), 1L);
                    }
                }
            }
        }
    }

    @EventHandler
    public void a(PlayerPortalEvent playerPortalEvent) {
        Player player = playerPortalEvent.getPlayer();
        World world = player.getWorld();
        String string = Main.getPlugin().getConfig().getString("Basic.World-Prefix");
        if (world.getName().startsWith(string)) {
            String string2 = world.getName().split(string)[1];
            if (WorldManager.c(string2)) {
                boolean bl = Main.getPlugin().getConfig().getBoolean("Basic.Portals");
                if (playerPortalEvent.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
                    if (!bl) {
                        playerPortalEvent.setCancelled(true);
                    }
                    if (Main.getPlugin().getConfig().getBoolean("Commands.Nether.Enabled")) {
                        for (String string3 : Main.getPlugin().getConfig().getStringList("Commands.Nether.List")) {
                            string3 = string3.replace("%player%", player.getName());
                            string3 = string3.replace("%world%", world.getName());
                            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)string3);
                        }
                    }
                } else if (playerPortalEvent.getCause() == PlayerTeleportEvent.TeleportCause.END_PORTAL) {
                    if (!bl) {
                        playerPortalEvent.setCancelled(true);
                    }
                    if (Main.getPlugin().getConfig().getBoolean("Commands.End.Enabled")) {
                        if (this.b.containsKey(player.getName()) && System.currentTimeMillis() - this.b.get(player.getName()) < 250L) {
                            return;
                        }
                        for (String string4 : Main.getPlugin().getConfig().getStringList("Commands.End.List")) {
                            string4 = string4.replace("%player%", player.getName());
                            string4 = string4.replace("%world%", world.getName());
                            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)string4);
                        }
                        this.b.put(player.getName(), System.currentTimeMillis());
                    }
                }
            }
        }
    }

    @EventHandler
    public void a(PlayerChangedWorldEvent playerChangedWorldEvent) {
        String string;
        Player player = playerChangedWorldEvent.getPlayer();
        World world = player.getWorld();
        World world2 = playerChangedWorldEvent.getFrom();
        String string2 = Main.getPlugin().getConfig().getString("Basic.World-Prefix");
        if (world2.getName().startsWith(string2)) {
            string = world2.getName().split(string2)[1];
            if (WorldManager.c(string)) {
                for (Player player2 : world.getPlayers()) {
                    if (player2 == player) continue;
                    player2.sendMessage(Main.getPlugin().getConfig().getString("JoinAndLeaveMSG.Messages.Leave").replace("&", "§").replace("%player%", player.getName()));
                }
            }
        }
        if (Main.getPlugin().getConfig().getBoolean("JoinAndLeaveMSG.Enabled")) {
            if (Main.getPlugin().getConfig().getBoolean("JoinAndLeaveMSG.Bypass") && player.hasPermission("PlayerWorldsPro.bypass.JoinAndLeaveMSG")) {
                return;
            }
            if (world.getName().startsWith(string2)) {
                string = world.getName().split(string2)[1];
                if (WorldManager.c(string)) {
                    for (Player player2 : world.getPlayers()) {
                        player2.sendMessage(Main.getPlugin().getConfig().getString("JoinAndLeaveMSG.Messages.Join").replace("&", "§").replace("%player%", player.getName()));
                    }
                }
            }
        }
    }
}

