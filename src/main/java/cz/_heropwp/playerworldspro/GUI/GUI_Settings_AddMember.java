/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.SkullType
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

public class GUI_Settings_AddMember
implements Listener {

    public static void a(Player player) {
        if (!GUI_Settings.b().containsKey(player.getName())) {
            player.closeInventory();
            return;
        }
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Access") && !player.hasPermission("PlayerWorldsPro.access")) {
            player.closeInventory();
            player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Access.Insufficient-Permission").replace("&", "§"));
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, (int)54, (String)Main.getPlugin().getConfig().getString("GUI.Add-Member.Title").replace("&", "§"));
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
                if (player2.getName().equals(string) || ConfigManager.getDataConfig().getStringList("Worlds." + string + ".Members").contains(player2.getName())) continue;
                if (n >= inventory.getSize()) break;
                if (GUI_Main.c().containsKey(player.getName())) {
                    if (n2 > GUI_Main.c().get(player.getName()) * 45) {
                        ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.PLAYER_HEAD), 1, (short)SkullType.PLAYER.ordinal());
                        SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
                        if (GUI_Main.d().contains(player.getName())) {
                            skullMeta.setOwner(player2.getName());
                        }
                        skullMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Add-Member.Items.Player.Displayname").replace("&", "§").replace("%player%", player2.getName()));
                        ArrayList<String> arrayList = new ArrayList<String>();
                        for (String string2 : Main.getPlugin().getConfig().getStringList("GUI.Add-Member.Items.Player.Lore")) {
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
            itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Add-Member.Items.Previous").replace("&", "§"));
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        return new ItemStack(Material.AIR);
    }

    private static ItemStack b(String string) {
        String string2;
        //ItemStack string2;
        int n = 0;
        if (GUI_Settings.b().containsKey(string)) {
            string2 = GUI_Settings.b().get(string);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getName().equals(string2) || ConfigManager.getDataConfig().getStringList("Worlds." + string2 + ".Members").contains(player.getName())) continue;
                ++n;
            }
        }
        if (n > (GUI_Main.c().get(string) + 1) * 45) {
            ItemStack stringStack = new ItemStack(Material.ARROW);
            ItemMeta itemMeta = stringStack.getItemMeta();
            itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Add-Member.Items.Next").replace("&", "§"));
            stringStack.setItemMeta(itemMeta);
            return stringStack;
        }
        return new ItemStack(Material.AIR);
    }

    @EventHandler
    public void a(InventoryClickEvent inventoryClickEvent) {
        Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(Main.getPlugin().getConfig().getString("GUI.Add-Member.Title").replace("&", "§"))) {
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
        if (inventoryClickEvent.getCurrentItem().getType() == Material.ARROW && Main.getPlugin().getConfig().getString("GUI.Add-Member.Items.Previous").replace("&", "§").contains(string)) {
            GUI_Main.c().put(player.getName(), GUI_Main.c().get(player.getName()) - 1);
            this.b(player, player.getOpenInventory().getTopInventory());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.ARROW && Main.getPlugin().getConfig().getString("GUI.Add-Member.Items.Next").replace("&", "§").contains(string)) {
            GUI_Main.c().put(player.getName(), GUI_Main.c().get(player.getName()) + 1);
            this.b(player, player.getOpenInventory().getTopInventory());
        } else if (inventoryClickEvent.getCurrentItem().getType() == MaterialManager.a(MaterialManager.a.PLAYER_HEAD)) {
            Player player2 = Bukkit.getPlayer((String)GUI_Main.a("GUI.Add-Member.Items.Player.Displayname", string));
            String string2 = GUI_Settings.b().get(player.getName());
            if (player2 == null || !player2.isOnline()) {
                player.closeInventory();
                return;
            }
            List<String> list = ConfigManager.getDataConfig().contains("Worlds." + string2 + ".Members") ? ConfigManager.getDataConfig().getStringList("Worlds." + string2 + ".Members") : new ArrayList<String>();
            list.add(player2.getName());
            ConfigManager.getDataConfig().set("Worlds." + string2 + ".Members", list);
            ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
            ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
            player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Access.Add-Member").replace("&", "§").replace("%player%", player2.getName()));
            player.closeInventory();
            GUI_Settings.b().remove(player.getName());
        }
    }

    @EventHandler
    public void a(InventoryCloseEvent inventoryCloseEvent) {
        Player player = (Player)inventoryCloseEvent.getPlayer();
        if (inventoryCloseEvent.getView().getTitle().equals(Main.getPlugin().getConfig().getString("GUI.Add-Member.Title").replace("&", "§"))) {
            GUI_Main.a(player.getName(), true);
        }
    }
}

