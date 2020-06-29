/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.SkullType
 *  org.bukkit.World
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.event.inventory.InventoryCloseEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.inventory.meta.SkullMeta
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitTask
 */
package cz._heropwp.playerworldspro.GUI;

import cz._heropwp.playerworldspro.CoreManagers.BasicManager;
import cz._heropwp.playerworldspro.CoreManagers.WorldManager;
import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.CoreManagers.ConfigManager;
import cz._heropwp.playerworldspro.CoreManagers.MaterialManager;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

public class GUI_Settings_BanPlayer
implements Listener {

    public static void a(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Ban") && !player.hasPermission("PlayerWorldsPro.ban")) {
            player.closeInventory();
            player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Insufficient-Permission").replace("&", "§"));
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, (int)54, (String)Main.getPlugin().getConfig().getString("GUI.Ban-Player.Title").replace("&", "§"));
        player.openInventory(inventory);
        GUI_Main.c().put(player.getName(), 0);
        a(player, inventory);
    }

    private static void a(Player player, Inventory inventory) {
        GUI_Main.b().put(player.getName(), Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getPlugin(), () -> c(player, inventory), 0L, 10L));
    }

    private void b(Player player, Inventory inventory) {
        GUI_Main.a(player.getName(), false);
        GUI_Main.a(9, inventory);
        this.a(player, inventory);
    }

    private static void c(Player player, Inventory inventory) {
        inventory.setItem(0, a(player.getName()));
        inventory.setItem(8, b(player.getName()));
        int n = 9;
        int n2 = 1;
        if (GUI_Settings.b().containsKey(player.getName()) && GUI_Main.c().containsKey(player.getName())) {
            String string = GUI_Settings.b().get(player.getName());
            for (Player player2 : Bukkit.getOnlinePlayers()) {
                if (player2.getName().equals(player.getName()) || player2.getName().equals(string) || player2.hasPermission("PlayerWorldsPro.bypass.ban") || ConfigManager.getDataConfig().getStringList("Worlds." + string + ".Banned").contains(player2.getName())) continue;
                if (n >= inventory.getSize()) break;
                if (GUI_Main.c().containsKey(player.getName())) {
                    if (n2 > GUI_Main.c().get(player.getName()) * 45) {
                        ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.PLAYER_HEAD), 1, (short)SkullType.PLAYER.ordinal());
                        SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
                        if (GUI_Main.d().contains(player.getName())) {
                            skullMeta.setOwner(player2.getName());
                        }
                        skullMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Ban-Player.Items.Player.Displayname").replace("&", "§").replace("%player%", player2.getName()));
                        ArrayList<String> arrayList = new ArrayList<String>();
                        for (String string2 : Main.getPlugin().getConfig().getStringList("GUI.Ban-Player.Items.Player.Lore")) {
                            string2 = string2.replace("&", "§");
                            arrayList.add(string2);
                        }
                        skullMeta.setLore(arrayList);
                        itemStack.setItemMeta((ItemMeta)skullMeta);
                        inventory.setItem(n, itemStack);
                        ++n;
                    }
                } else {
                    GUI_Main.a(player.getName(), true);
                    return;
                }
                ++n2;
            }
        }
        GUI_Main.d().add(player.getName());
        GUI_Main.a(n, inventory);
    }

    private static ItemStack a(String string) {
        if (GUI_Main.c().get(string) > 0) {
            ItemStack itemStack = new ItemStack(Material.ARROW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Ban-Player.Items.Previous").replace("&", "§"));
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        return new ItemStack(Material.AIR);
    }

    private static ItemStack b(String string) {
        int n = 0;
        String string2 = Main.getPlugin().getConfig().getString("Basic.World-Prefix");
        String string3 = GUI_Settings.b().get(string);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equals(string) || player.getName().equals(string3) || !player.getWorld().getName().equals(string2 + string3) || player.hasPermission("PlayerWorldsPro.bypass.kick")) continue;
            ++n;
        }
        if (n > (GUI_Main.c().get(string) + 1) * 45) {
            //Player player;
            ItemStack itemStack = new ItemStack(Material.ARROW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Ban-Player.Items.Next").replace("&", "§"));
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        return new ItemStack(Material.AIR);
    }

    @EventHandler
    public void a(InventoryClickEvent inventoryClickEvent) {
        Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(Main.getPlugin().getConfig().getString("GUI.Ban-Player.Title").replace("&", "§"))) {
            return;
        }
        if (inventoryClickEvent.getCurrentItem() == null) {
            return;
        }
        inventoryClickEvent.setCancelled(true);
        if (!inventoryClickEvent.getCurrentItem().hasItemMeta()) {
            return;
        }
        if (!inventoryClickEvent.getCurrentItem().getItemMeta().hasDisplayName()) {
            return;
        }
        inventoryClickEvent.setCancelled(true);
        if (!GUI_Settings.b().containsKey(player.getName())) {
            player.closeInventory();
            return;
        }
        String string = inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName();
        if (inventoryClickEvent.getCurrentItem().getType() == Material.ARROW && Main.getPlugin().getConfig().getString("GUI.Ban-Player.Items.Previous").replace("&", "§").contains(string)) {
            GUI_Main.c().put(player.getName(), GUI_Main.c().get(player.getName()) - 1);
            this.b(player, player.getOpenInventory().getTopInventory());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.ARROW && Main.getPlugin().getConfig().getString("GUI.Ban-Player.Items.Next").replace("&", "§").contains(string)) {
            GUI_Main.c().put(player.getName(), GUI_Main.c().get(player.getName()) + 1);
            this.b(player, player.getOpenInventory().getTopInventory());
        } else if (inventoryClickEvent.getCurrentItem().getType() == MaterialManager.a(MaterialManager.a.PLAYER_HEAD)) {
            Player player2 = Bukkit.getPlayer((String)GUI_Main.a("GUI.Ban-Player.Items.Player.Displayname", string));
            String string2 = GUI_Settings.b().get(player.getName());
            if (player2 == null || !player2.isOnline()) {
                player.closeInventory();
                return;
            }
            if (WorldManager.d(player2, string2)) {
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Already-Banned").replace("&", "§").replace("%player%", player2.getName()));
                return;
            }
            List<String> list = ConfigManager.getDataConfig().contains("Worlds." + string2 + ".Banned") ? ConfigManager.getDataConfig().getStringList("Worlds." + string2 + ".Banned") : new ArrayList<String>();
            list.add(player2.getName());
            ConfigManager.getDataConfig().set("Worlds." + string2 + ".Banned", list);
            ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
            ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
            String string3 = Main.getPlugin().getConfig().getString("Basic.World-Prefix") + string2;
            if (player2.getWorld().getName().equals(string3)) {
                if (BasicManager.b()) {
                    BasicManager.a(player2, BasicManager.c());
                    player2.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Banned").replace("&", "§"));
                } else {
                    player2.kickPlayer(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Lobby-Is-Not-Configured").replace("&", "§"));
                }
            }
            for (Player player3 : Bukkit.getOnlinePlayers()) {
                if (!player3.getWorld().getName().equals(string3)) continue;
                String string4 = Main.getPlugin().getConfig().getString("Messages.Broadcast.Ban");
                string4 = string4.replace("&", "§");
                string4 = string4.replace("%player%", player2.getName());
                string4 = string4.replace("%executor%", player.getName());
                player3.sendMessage(BasicManager.getPluginPrefix() + string4);
            }
            player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Ban-Player").replace("&", "§").replace("%player%", player2.getName()));
            player.closeInventory();
            GUI_Settings.b().remove(player.getName());
        }
    }

    @EventHandler
    public void a(InventoryCloseEvent inventoryCloseEvent) {
        Player player = (Player)inventoryCloseEvent.getPlayer();
        if (inventoryCloseEvent.getView().getTitle().equals(Main.getPlugin().getConfig().getString("GUI.Ban-Player.Title").replace("&", "§"))) {
            GUI_Main.a(player.getName(), true);
        }
    }
}

