/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.World
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
import cz._heropwp.playerworldspro.CoreManagers.MaterialManager;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI_Settings_ChangeTime
implements Listener {

    public static void a(Player player) {
        if (!GUI_Settings.b().containsKey(player.getName()) || Bukkit.getWorld((String)(Main.getPlugin().getConfig().getString("Basic.World-Prefix") + GUI_Settings.b().get(player.getName()))) == null) {
            player.closeInventory();
            player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Unloaded-World").replace("&", "§"));
            return;
        }
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Change-Time") && !player.hasPermission("PlayerWorldsPro.changeTime")) {
            player.closeInventory();
            player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Change-Time.Insufficient-Permission").replace("&", "§"));
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, (int)27, (String)Main.getPlugin().getConfig().getString("GUI.Change-Time.Title").replace("&", "§"));
        player.openInventory(inventory);
        inventory.setItem(10, a());
        inventory.setItem(11, b());
        inventory.setItem(12, c());
        inventory.setItem(13, d());
        inventory.setItem(16, a(Bukkit.getWorld(Main.getPlugin().getConfig().getString("Basic.World-Prefix") + GUI_Settings.b().get(player.getName()))));
    }

    private static ItemStack a() {
        ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.CLOCK));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Change-Time.Items.Day.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Change-Time.Items.Day.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack b() {
        ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.CLOCK));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Change-Time.Items.Sunset.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Change-Time.Items.Sunset.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack c() {
        ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.CLOCK));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Change-Time.Items.Night.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Change-Time.Items.Night.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack d() {
        ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.CLOCK));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Change-Time.Items.Sunrise.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Change-Time.Items.Sunrise.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack a(World world) {
        String string = "Unlock";
        if (Boolean.parseBoolean(world.getGameRuleValue("doDaylightCycle"))) {
            string = "Lock";
        }
        ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.DEAD_BUSH));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Change-Time.Items.Daylight-Cycle-" + string + ".Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string2 : Main.getPlugin().getConfig().getStringList("GUI.Change-Time.Items.Daylight-Cycle-" + string + ".Lore")) {
            string2 = string2.replace("&", "§");
            arrayList.add(string2);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void a(InventoryClickEvent inventoryClickEvent) {
        Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(Main.getPlugin().getConfig().getString("GUI.Change-Time.Title").replace("&", "§"))) {
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
        if (GUI_Settings.b().containsKey(player.getName()) && Bukkit.getWorld((String)(Main.getPlugin().getConfig().getString("Basic.World-Prefix") + GUI_Settings.b().get(player.getName()))) != null) {
            String string = inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName();
            World world = Bukkit.getWorld((String)(Main.getPlugin().getConfig().getString("Basic.World-Prefix") + GUI_Settings.b().get(player.getName())));
            if (string.equals(Main.getPlugin().getConfig().getString("GUI.Change-Time.Items.Day.Displayname").replace("&", "§"))) {
                player.closeInventory();
                world.setTime(1000L);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Change-Time.Day").replace("&", "§"));
            } else if (string.equals(Main.getPlugin().getConfig().getString("GUI.Change-Time.Items.Sunset.Displayname").replace("&", "§"))) {
                player.closeInventory();
                world.setTime(12000L);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Change-Time.Sunset").replace("&", "§"));
            } else if (string.equals(Main.getPlugin().getConfig().getString("GUI.Change-Time.Items.Night.Displayname").replace("&", "§"))) {
                player.closeInventory();
                world.setTime(13000L);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Change-Time.Night").replace("&", "§"));
            } else if (string.equals(Main.getPlugin().getConfig().getString("GUI.Change-Time.Items.Sunrise.Displayname").replace("&", "§"))) {
                player.closeInventory();
                world.setTime(23000L);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Change-Time.Sunrise").replace("&", "§"));
            } else if (string.equals(Main.getPlugin().getConfig().getString("GUI.Change-Time.Items.Daylight-Cycle-Lock.Displayname").replace("&", "§")) && Boolean.parseBoolean(world.getGameRuleValue("doDaylightCycle"))) {
                player.closeInventory();
                world.setGameRuleValue("doDaylightCycle", "false");
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Daylight-Cycle.Lock").replace("&", "§"));
            } else if (string.equals(Main.getPlugin().getConfig().getString("GUI.Change-Time.Items.Daylight-Cycle-Unlock.Displayname").replace("&", "§")) && !Boolean.parseBoolean(world.getGameRuleValue("doDaylightCycle"))) {
                player.closeInventory();
                world.setGameRuleValue("doDaylightCycle", "true");
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Daylight-Cycle.Unlock").replace("&", "§"));
            }
        } else {
            player.closeInventory();
        }
        GUI_Settings.b().remove(player.getName());
    }
}

