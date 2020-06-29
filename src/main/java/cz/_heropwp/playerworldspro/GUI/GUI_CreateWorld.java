/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package cz._heropwp.playerworldspro.GUI;

import cz._heropwp.playerworldspro.CoreManagers.BasicManager;
import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.CoreManagers.ConfigManager;
import cz._heropwp.playerworldspro.CoreManagers.MaterialManager;
import cz._heropwp.playerworldspro.CoreManagers.WorldManager;

import java.text.MessageFormat;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI_CreateWorld
implements Listener {

    public static void a(Player player) {
        if (WorldManager.c(player.getName())) {
            player.closeInventory();
            player.sendMessage(MessageFormat.format("{0}{1}", BasicManager.getPluginPrefix(), Main.getPlugin().getConfig().getString("Messages.Already-Have").replace("&", "§")));
            return;
        }
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Create-World") && !player.hasPermission("PlayerWorldsPro.createWorld")) {
            player.closeInventory();
            player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Insufficient-Permission-Create-World").replace("&", "§"));
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, (int)27, (String)Main.getPlugin().getConfig().getString("GUI.Create-World.Title").replace("&", "§"));
        player.openInventory(inventory);
        inventory.setItem(10, b(player));
        inventory.setItem(11, c(player));
        inventory.setItem(12, d(player));
        inventory.setItem(15, e(player));
        inventory.setItem(16, f(player));
    }

    private static ItemStack b(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Create-World-Type.Normal") && !player.hasPermission("PlayerWorldsPro.createWorld.normal") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.OAK_SAPLING));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Create-World.Items.Normal.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Create-World.Items.Normal.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack c(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Create-World-Type.Flat") && !player.hasPermission("PlayerWorldsPro.createWorld.flat") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.GRASS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Create-World.Items.Flat.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Create-World.Items.Flat.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack d(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Create-World-Type.Empty") && !player.hasPermission("PlayerWorldsPro.createWorld.empty") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Create-World.Items.Empty.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Create-World.Items.Empty.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack e(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Create-World-Type.Pre-Built-Maps") && !player.hasPermission("PlayerWorldsPro.createWorld.preBuiltMaps") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.BRICK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Create-World.Items.Pre-Built-Maps.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Create-World.Items.Pre-Built-Maps.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack f(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Create-World-Type.Custom-Generators") && !player.hasPermission("PlayerWorldsPro.createWorld.customGenerators") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.COMMAND_BLOCK));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Create-World.Items.Custom-Generators.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Create-World.Items.Custom-Generators.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void a(InventoryClickEvent inventoryClickEvent) {
        Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(Main.getPlugin().getConfig().getString("GUI.Create-World.Title").replace("&", "§"))) {
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
        if (inventoryClickEvent.getCurrentItem().getType() == MaterialManager.a(MaterialManager.a.OAK_SAPLING)) {
            if (Main.getPlugin().getConfig().getBoolean("Permissions.Create-World-Type.Normal") && !player.hasPermission("PlayerWorldsPro.createWorld.normal")) {
                player.closeInventory();
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Insufficient-Permission-Create-World.Type").replace("&", "§"));
                return;
            }
            if (WorldManager.b()) {
                if (Main.getPlugin().getConfig().getBoolean("Claim.Enabled") && !ConfigManager.getPlayersConfig().contains("Claim." + player.getName())) {
                    WorldManager.a(player, WorldManager.a.NORMAL, null, null, Main.getPlugin().getConfig().getInt("Claim.Length"), null, true);
                } else {
                    GUI_Buy_PlayerWorld.a(player, WorldManager.a.NORMAL, null, null);
                }
            } else {
                WorldManager.a(player, WorldManager.a.NORMAL, null, null, null, null, false);
            }
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.GRASS) {
            if (Main.getPlugin().getConfig().getBoolean("Permissions.Create-World-Type.Flat") && !player.hasPermission("PlayerWorldsPro.createWorld.flat")) {
                player.closeInventory();
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Insufficient-Permission-Create-World.Type").replace("&", "§"));
                return;
            }
            if (WorldManager.b()) {
                if (Main.getPlugin().getConfig().getBoolean("Claim.Enabled") && !ConfigManager.getPlayersConfig().contains("Claim." + player.getName())) {
                    WorldManager.a(player, WorldManager.a.FLAT, null, null, Main.getPlugin().getConfig().getInt("Claim.Length"), null, true);
                } else {
                    GUI_Buy_PlayerWorld.a(player, WorldManager.a.FLAT, null, null);
                }
            } else {
                WorldManager.a(player, WorldManager.a.FLAT, null, null, null, null, false);
            }
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.BARRIER) {
            if (Main.getPlugin().getConfig().getBoolean("Permissions.Create-World-Type.Empty") && !player.hasPermission("PlayerWorldsPro.createWorld.empty")) {
                player.closeInventory();
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Insufficient-Permission-Create-World.Type").replace("&", "§"));
                return;
            }
            if (WorldManager.b()) {
                if (Main.getPlugin().getConfig().getBoolean("Claim.Enabled") && !ConfigManager.getPlayersConfig().contains("Claim." + player.getName())) {
                    WorldManager.a(player, WorldManager.a.EMPTY, null, null, Main.getPlugin().getConfig().getInt("Claim.Length"), null, true);
                } else {
                    GUI_Buy_PlayerWorld.a(player, WorldManager.a.EMPTY, null, null);
                }
            } else {
                WorldManager.a(player, WorldManager.a.EMPTY, null, null, null, null, false);
            }
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.BRICK) {
            if (Main.getPlugin().getConfig().getBoolean("Permissions.Create-World-Type.Pre-Built-Maps") && !player.hasPermission("PlayerWorldsPro.createWorld.preBuiltMaps")) {
                player.closeInventory();
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Insufficient-Permission-Create-World.Type").replace("&", "§"));
                return;
            }
            GUI_PreBuiltMaps.a(player);
        } else if (inventoryClickEvent.getCurrentItem().getType() == MaterialManager.a(MaterialManager.a.COMMAND_BLOCK)) {
            if (Main.getPlugin().getConfig().getBoolean("Permissions.Create-World-Type.Custom-Generators") && !player.hasPermission("PlayerWorldsPro.createWorld.customGenerators")) {
                player.closeInventory();
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Insufficient-Permission-Create-World.Type").replace("&", "§"));
                return;
            }
            GUI_CustomGenerators.a(player);
        }
    }
}

