/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
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

public class GUI_Settings_UnbanPlayer
implements Listener {

    public static void a(Player player) {
        if (!GUI_Settings.b().containsKey(player.getName())) {
            player.closeInventory();
            return;
        }
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Ban") && !player.hasPermission("PlayerWorldsPro.ban")) {
            player.closeInventory();
            player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Insufficient-Permission").replace("&", "§"));
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, (int)54, (String)Main.getPlugin().getConfig().getString("GUI.Unban-Player.Title").replace("&", "§"));
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
            if (ConfigManager.getDataConfig().contains("Worlds." + string + ".Banned")) {
                for (String string2 : ConfigManager.getDataConfig().getStringList("Worlds." + string + ".Banned")) {
                    if (n >= inventory.getSize()) break;
                    if (GUI_Main.c().containsKey(player.getName())) {
                        if (n2 > GUI_Main.c().get(player.getName()) * 45) {
                            ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.PLAYER_HEAD), 1, (short)SkullType.PLAYER.ordinal());
                            SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
                            if (GUI_Main.d().contains(player.getName())) {
                                skullMeta.setOwner(string2);
                            }
                            skullMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Unban-Player.Items.Player.Displayname").replace("&", "§").replace("%player%", string2));
                            ArrayList<String> arrayList = new ArrayList<String>();
                            for (String string3 : Main.getPlugin().getConfig().getStringList("GUI.Unban-Player.Items.Player.Lore")) {
                                string3 = string3.replace("&", "§");
                                arrayList.add(string3);
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
        }
        GUI_Main.d().add(player.getName());
        GUI_Main.a(n, inventory);
    }

    private static ItemStack a(String string) {
        if (GUI_Main.c().get(string) > 0) {
            ItemStack itemStack = new ItemStack(Material.ARROW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Unban-Player.Items.Previous").replace("&", "§"));
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        return new ItemStack(Material.AIR);
    }

    private static ItemStack b(String string) {
        String string2;
        int n = 0;
        if (GUI_Settings.b().containsKey(string)) {
            string2 = GUI_Settings.b().get(string);
            if (ConfigManager.getDataConfig().contains("Worlds." + string2 + ".Banned")) {
                n = ConfigManager.getDataConfig().getStringList("Worlds." + string2 + ".Banned").size();
            }
        }
        if (n > (GUI_Main.c().get(string) + 1) * 45) {
            ItemStack itemStack = new ItemStack(Material.ARROW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Unban-Player.Items.Next").replace("&", "§"));
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        return new ItemStack(Material.AIR);
    }

    @EventHandler
    public void a(InventoryClickEvent inventoryClickEvent) {
        Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(Main.getPlugin().getConfig().getString("GUI.Unban-Player.Title").replace("&", "§"))) {
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
        if (inventoryClickEvent.getCurrentItem().getType() == Material.ARROW && Main.getPlugin().getConfig().getString("GUI.Unban-Player.Items.Previous").replace("&", "§").contains(string)) {
            GUI_Main.c().put(player.getName(), GUI_Main.c().get(player.getName()) - 1);
            this.b(player, player.getOpenInventory().getTopInventory());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.ARROW && Main.getPlugin().getConfig().getString("GUI.Unban-Player.Items.Next").replace("&", "§").contains(string)) {
            GUI_Main.c().put(player.getName(), GUI_Main.c().get(player.getName()) + 1);
            this.b(player, player.getOpenInventory().getTopInventory());
        } else if (inventoryClickEvent.getCurrentItem().getType() == MaterialManager.a(MaterialManager.a.PLAYER_HEAD)) {
            String string2 = GUI_Main.a("GUI.Unban-Player.Items.Player.Displayname", string);
            String string3 = GUI_Settings.b().get(player.getName());
            List<String> list = ConfigManager.getDataConfig().getStringList("Worlds." + string3 + ".Banned");
            list.remove(string2);
            ConfigManager.getDataConfig().set("Worlds." + string3 + ".Banned", (Object)list);
            ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
            ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
            String string4 = Main.getPlugin().getConfig().getString("Basic.World-Prefix") + string3;
            for (Player player2 : Bukkit.getOnlinePlayers()) {
                if (!player2.getWorld().getName().equals(string4)) continue;
                String string5 = Main.getPlugin().getConfig().getString("Messages.Broadcast.Unban");
                string5 = string5.replace("&", "§");
                string5 = string5.replace("%player%", string2);
                string5 = string5.replace("%executor%", player.getName());
                player2.sendMessage(BasicManager.getPluginPrefix() + string5);
            }
            player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Unban-Player").replace("&", "§").replace("%player%", string2));
            player.closeInventory();
            GUI_Settings.b().remove(player.getName());
        }
    }

    @EventHandler
    public void a(InventoryCloseEvent inventoryCloseEvent) {
        Player player = (Player)inventoryCloseEvent.getPlayer();
        if (inventoryCloseEvent.getView().getTitle().equals(Main.getPlugin().getConfig().getString("GUI.Unban-Player.Title").replace("&", "§"))) {
            GUI_Main.a(player.getName(), true);
        }
    }
}

