/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.SkullType
 *  org.bukkit.World
 *  org.bukkit.command.CommandSender
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
import cz._heropwp.playerworldspro.CoreManagers.WorldManager;
import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.CoreManagers.ConfigManager;
import cz._heropwp.playerworldspro.CoreManagers.MaterialManager;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI_Settings
implements Listener {
    private static HashMap<String, String> b = new HashMap<>();

    public static void a(Player player, String string) {
        b.put(player.getName(), string);
        Inventory inventory = Bukkit.createInventory(null, (int)54, (String)Main.getPlugin().getConfig().getString("GUI.Settings.Title").replace("&", "§"));
        player.openInventory(inventory);
        inventory.setItem(0, a(player));
        inventory.setItem(1, b(player));
        inventory.setItem(4, d(player));
        inventory.setItem(7, e(player));
        inventory.setItem(8, f(player));
        inventory.setItem(9, c(player));
        inventory.setItem(10, h(player));
        inventory.setItem(12, n(player));
        inventory.setItem(13, o(player));
        inventory.setItem(14, p(player));
        inventory.setItem(16, g(player));
        inventory.setItem(17, k(player));
        inventory.setItem(25, i(player));
        inventory.setItem(26, j(player));
        inventory.setItem(30, q(player));
        inventory.setItem(31, r(player));
        inventory.setItem(32, s(player));
        inventory.setItem(34, l(player));
        inventory.setItem(35, m(player));
        if (WorldManager.b()) {
            inventory.setItem(46, c());
        }
        inventory.setItem(49, d());
        inventory.setItem(51, t(player));
        inventory.setItem(52, u(player));
    }

    private static ItemStack a(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Change-Time") && !player.hasPermission("PlayerWorldsPro.changeTime") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.CLOCK));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Change-Time.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Change-Time.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack b(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Change-Weather") && !player.hasPermission("PlayerWorldsPro.changeWeather") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.WATER_BUCKET);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Change-Weather.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Change-Weather.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack c(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Change-Difficulty") && !player.hasPermission("PlayerWorldsPro.changeDifficulty") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.BONE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Change-Difficulty.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Change-Difficulty.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack d(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Set-Spawn-Location") && !player.hasPermission("PlayerWorldsPro.setSpawn") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.RED_BED));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Spawn-Location.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Spawn-Location.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack e(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Block-Breaking") && !player.hasPermission("PlayerWorldsPro.editBlockBreaking") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Block-Breaking.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Block-Breaking.Lore")) {
            string = string.replace("&", "§");
            String string2 = "Variables.";
            string2 = WorldManager.g(b.get(player.getName())) ? string2 + "Enabled" : string2 + "Disabled";
            string = string.replace("%status%", Main.getPlugin().getConfig().getString(string2).replace("&", "§"));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack f(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Block-Placing") && !player.hasPermission("PlayerWorldsPro.editBlockPlacing") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.BRICK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Block-Placing.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Block-Placing.Lore")) {
            string = string.replace("&", "§");
            String string2 = "Variables.";
            string2 = WorldManager.h(b.get(player.getName())) ? string2 + "Enabled" : string2 + "Disabled";
            string = string.replace("%status%", Main.getPlugin().getConfig().getString(string2).replace("&", "§"));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack g(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.PvP") && !player.hasPermission("PlayerWorldsPro.editPvP") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.PvP.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.PvP.Lore")) {
            string = string.replace("&", "§");
            String string2 = "Variables.";
            string2 = WorldManager.i(b.get(player.getName())) ? string2 + "Enabled" : string2 + "Disabled";
            string = string.replace("%status%", Main.getPlugin().getConfig().getString(string2).replace("&", "§"));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack h(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.World-Border") && !player.hasPermission("PlayerWorldsPro.worldBorder") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.World-Border.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.World-Border.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack i(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Item-Pickup") && !player.hasPermission("PlayerWorldsPro.editItemPickup") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Pickup.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Pickup.Lore")) {
            string = string.replace("&", "§");
            String string2 = "Variables.";
            string2 = WorldManager.k(b.get(player.getName())) ? string2 + "Enabled" : string2 + "Disabled";
            string = string.replace("%status%", Main.getPlugin().getConfig().getString(string2).replace("&", "§"));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack j(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Drop-Item") && !player.hasPermission("PlayerWorldsPro.editDropItem") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.ROTTEN_FLESH);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Drop.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Drop.Lore")) {
            string = string.replace("&", "§");
            String string2 = "Variables.";
            string2 = WorldManager.l(b.get(player.getName())) ? string2 + "Enabled" : string2 + "Disabled";
            string = string.replace("%status%", Main.getPlugin().getConfig().getString(string2).replace("&", "§"));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack k(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Player-Damage") && !player.hasPermission("PlayerWorldsPro.editPlayerDamage") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Damage.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Damage.Lore")) {
            string = string.replace("&", "§");
            String string2 = "Variables.";
            string2 = WorldManager.m(b.get(player.getName())) ? string2 + "Enabled" : string2 + "Disabled";
            string = string.replace("%status%", Main.getPlugin().getConfig().getString(string2).replace("&", "§"));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack l(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Hunger") && !player.hasPermission("PlayerWorldsPro.editHunger") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.APPLE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Hunger.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Hunger.Lore")) {
            string = string.replace("&", "§");
            String string2 = "Variables.";
            string2 = WorldManager.n(b.get(player.getName())) ? string2 + "Enabled" : string2 + "Disabled";
            string = string.replace("%status%", Main.getPlugin().getConfig().getString(string2).replace("&", "§"));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack m(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Bucket") && !player.hasPermission("PlayerWorldsPro.editBucket") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.BUCKET);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Bucket.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Bucket.Lore")) {
            string = string.replace("&", "§");
            String string2 = "Variables.";
            string2 = WorldManager.o(b.get(player.getName())) ? string2 + "Enabled" : string2 + "Disabled";
            string = string.replace("%status%", Main.getPlugin().getConfig().getString(string2).replace("&", "§"));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack n(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission") && Main.getPlugin().getConfig().getBoolean("Permissions.Change-GameMode") && !player.hasPermission("PlayerWorldsPro.changeGameMode") && Main.getPlugin().getConfig().getBoolean("Permissions.Change-Default-GameMode") && !player.hasPermission("PlayerWorldsPro.changeGameMode.default")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.DIAMOND);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.GameMode.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.GameMode.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack o(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Teleport") && !player.hasPermission("PlayerWorldsPro.teleport") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.PLAYER_HEAD), 1, (short)SkullType.PLAYER.ordinal());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Teleport.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Teleport.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack p(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Fly") && !player.hasPermission("PlayerWorldsPro.fly") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.FEATHER, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Fly.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Fly.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack q(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Access") && !player.hasPermission("PlayerWorldsPro.access") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.WRITABLE_BOOK));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Access.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Access.Lore")) {
            string = string.replace("&", "§");
            String string2 = "Variables." + WorldManager.p(b.get(player.getName()));
            string = string.replace("%status%", Main.getPlugin().getConfig().getString(string2).replace("&", "§"));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack r(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Access") && !player.hasPermission("PlayerWorldsPro.access") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.PLAYER_HEAD), 1, (short)SkullType.PLAYER.ordinal());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Add-Member.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Add-Member.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack s(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Access") && !player.hasPermission("PlayerWorldsPro.access") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.PLAYER_HEAD), 1, (short)SkullType.PLAYER.ordinal());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Remove-Member.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Remove-Member.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack c() {
        ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Extend.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Extend.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack d() {
        ItemStack itemStack = new ItemStack(Material.TNT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Delete.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Delete.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack t(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Kick") && !player.hasPermission("PlayerWorldsPro.kick") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Kick.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Kick.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack u(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Ban") && !player.hasPermission("PlayerWorldsPro.ban") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Ban.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Settings.Items.Ban.Lore")) {
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
        if (!inventoryClickEvent.getView().getTitle().equals(Main.getPlugin().getConfig().getString("GUI.Settings.Title").replace("&", "§"))) {
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
        if (!b.containsKey(player.getName())) {
            player.closeInventory();
            return;
        }
        String string = inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName();
        String string2 = b.get(player.getName());
        if (inventoryClickEvent.getCurrentItem().getType() == MaterialManager.a(MaterialManager.a.CLOCK)) {
            GUI_Settings_ChangeTime.a(player);
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.WATER_BUCKET) {
            GUI_Settings_ChangeWeather.a(player);
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.BONE) {
            GUI_Settings_ChangeDifficulty.a(player);
        } else if (inventoryClickEvent.getCurrentItem().getType().toString().contains(MaterialManager.a(MaterialManager.a.RED_BED).toString())) {
            if (Main.getPlugin().getConfig().getBoolean("Permissions.Set-Spawn-Location") && !player.hasPermission("PlayerWorldsPro.setSpawn")) {
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Insufficient-Permission").replace("&", "§"));
            } else {
                WorldManager.a(player, string2, true);
            }
            player.closeInventory();
            GUI_Settings.b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.IRON_PICKAXE) {
            player.closeInventory();
            if (Main.getPlugin().getConfig().getBoolean("Permissions.Block-Breaking") && !player.hasPermission("PlayerWorldsPro.editBlockBreaking")) {
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Block-Breaking.Insufficient-Permission").replace("&", "§"));
            } else if (inventoryClickEvent.isLeftClick()) {
                ConfigManager.getDataConfig().set("Worlds." + string2 + ".Block-Breaking", (Object)true);
                ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Block-Breaking.Allow").replace("&", "§"));
            } else if (inventoryClickEvent.isRightClick()) {
                ConfigManager.getDataConfig().set("Worlds." + string2 + ".Block-Breaking", (Object)false);
                ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Block-Breaking.Deny").replace("&", "§"));
            }
            GUI_Settings.b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.BRICK) {
            player.closeInventory();
            if (Main.getPlugin().getConfig().getBoolean("Permissions.Block-Placing") && !player.hasPermission("PlayerWorldsPro.editBlockPlacing")) {
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Block-Placing.Insufficient-Permission").replace("&", "§"));
            } else if (inventoryClickEvent.isLeftClick()) {
                ConfigManager.getDataConfig().set("Worlds." + string2 + ".Block-Placing", (Object)true);
                ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Block-Placing.Allow").replace("&", "§"));
            } else if (inventoryClickEvent.isRightClick()) {
                ConfigManager.getDataConfig().set("Worlds." + string2 + ".Block-Placing", (Object)false);
                ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Block-Placing.Deny").replace("&", "§"));
            }
            GUI_Settings.b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.IRON_SWORD) {
            player.closeInventory();
            if (Main.getPlugin().getConfig().getBoolean("Permissions.PvP") && !player.hasPermission("PlayerWorldsPro.editPvP")) {
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.PvP.Insufficient-Permission").replace("&", "§"));
            } else if (inventoryClickEvent.isLeftClick()) {
                ConfigManager.getDataConfig().set("Worlds." + string2 + ".PvP", (Object)true);
                ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.PvP.Allow").replace("&", "§"));
            } else if (inventoryClickEvent.isRightClick()) {
                ConfigManager.getDataConfig().set("Worlds." + string2 + ".PvP", (Object)false);
                ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.PvP.Deny").replace("&", "§"));
            }
            GUI_Settings.b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.BARRIER && string.equals(Main.getPlugin().getConfig().getString("GUI.Settings.Items.World-Border.Displayname").replace("&", "§"))) {
            GUI_Settings_WorldBorder.a(player);
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
            player.closeInventory();
            if (Main.getPlugin().getConfig().getBoolean("Permissions.Item-Pickup") && !player.hasPermission("PlayerWorldsPro.editItemPickup")) {
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Pickup.Insufficient-Permission").replace("&", "§"));
            } else if (inventoryClickEvent.isLeftClick()) {
                ConfigManager.getDataConfig().set("Worlds." + string2 + ".Pickup", (Object)true);
                ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Pickup.Allow").replace("&", "§"));
            } else if (inventoryClickEvent.isRightClick()) {
                ConfigManager.getDataConfig().set("Worlds." + string2 + ".Pickup", (Object)false);
                ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Pickup.Deny").replace("&", "§"));
            }
            GUI_Settings.b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.ROTTEN_FLESH) {
            player.closeInventory();
            if (Main.getPlugin().getConfig().getBoolean("Permissions.Drop-Item") && !player.hasPermission("PlayerWorldsPro.editDropItem")) {
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Drop.Insufficient-Permission").replace("&", "§"));
            } else if (inventoryClickEvent.isLeftClick()) {
                ConfigManager.getDataConfig().set("Worlds." + string2 + ".Drop", (Object)true);
                ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Drop.Allow").replace("&", "§"));
            } else if (inventoryClickEvent.isRightClick()) {
                ConfigManager.getDataConfig().set("Worlds." + string2 + ".Drop", (Object)false);
                ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Drop.Deny").replace("&", "§"));
            }
            GUI_Settings.b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.LEATHER_CHESTPLATE) {
            player.closeInventory();
            if (Main.getPlugin().getConfig().getBoolean("Permissions.Player-Damage") && !player.hasPermission("PlayerWorldsPro.editPlayerDamage")) {
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Damage.Insufficient-Permission").replace("&", "§"));
            } else if (inventoryClickEvent.isLeftClick()) {
                ConfigManager.getDataConfig().set("Worlds." + string2 + ".Damage", (Object)true);
                ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Damage.Allow").replace("&", "§"));
            } else if (inventoryClickEvent.isRightClick()) {
                ConfigManager.getDataConfig().set("Worlds." + string2 + ".Damage", (Object)false);
                ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Damage.Deny").replace("&", "§"));
            }
            GUI_Settings.b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.APPLE) {
            player.closeInventory();
            if (Main.getPlugin().getConfig().getBoolean("Permissions.Hunger") && !player.hasPermission("PlayerWorldsPro.editHunger")) {
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Hunger.Insufficient-Permission").replace("&", "§"));
            } else if (inventoryClickEvent.isLeftClick()) {
                ConfigManager.getDataConfig().set("Worlds." + string2 + ".Hunger", (Object)true);
                ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Hunger.Allow").replace("&", "§"));
            } else if (inventoryClickEvent.isRightClick()) {
                ConfigManager.getDataConfig().set("Worlds." + string2 + ".Hunger", (Object)false);
                ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Hunger.Deny").replace("&", "§"));
            }
            GUI_Settings.b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.BUCKET) {
            player.closeInventory();
            if (Main.getPlugin().getConfig().getBoolean("Permissions.Bucket") && !player.hasPermission("PlayerWorldsPro.editBucket")) {
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Bucket.Insufficient-Permission").replace("&", "§"));
            } else if (inventoryClickEvent.isLeftClick()) {
                ConfigManager.getDataConfig().set("Worlds." + string2 + ".Bucket", (Object)true);
                ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Bucket.Allow").replace("&", "§"));
            } else if (inventoryClickEvent.isRightClick()) {
                ConfigManager.getDataConfig().set("Worlds." + string2 + ".Bucket", (Object)false);
                ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Bucket.Deny").replace("&", "§"));
            }
            GUI_Settings.b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.DIAMOND) {
            if (inventoryClickEvent.isLeftClick()) {
                GUI_Settings_ChangeGameMode.a(player);
            } else if (inventoryClickEvent.isRightClick()) {
                GUI_Settings_ChangeDefaultGameMode.a(player);
            }
        } else if (inventoryClickEvent.getCurrentItem().getType() == MaterialManager.a(MaterialManager.a.PLAYER_HEAD) && string.equals(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Teleport.Displayname").replace("&", "§"))) {
            if (inventoryClickEvent.isLeftClick()) {
                GUI_Settings_Teleport.a(player);
            } else if (inventoryClickEvent.isRightClick()) {
                GUI_Settings_TeleportHere.a(player);
            }
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.FEATHER) {
            String string3 = Main.getPlugin().getConfig().getString("Basic.World-Prefix") + string2;
            if (!player.getWorld().getName().equals(string3)) {
                player.closeInventory();
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Same-World").replace("&", "§"));
                return;
            }
            if (inventoryClickEvent.isLeftClick()) {
                if (player.getAllowFlight()) {
                    player.setAllowFlight(false);
                    player.setFlying(false);
                } else {
                    player.setAllowFlight(true);
                    player.setFlying(true);
                }
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Fly").replace("&", "§"));
                player.closeInventory();
                GUI_Settings.b().remove(player.getName());
            } else if (inventoryClickEvent.isRightClick()) {
                GUI_Settings_Fly.a(player);
            }
        } else if (inventoryClickEvent.getCurrentItem().getType() == MaterialManager.a(MaterialManager.a.WRITABLE_BOOK)) {
            if (Main.getPlugin().getConfig().getBoolean("Permissions.Access") && !player.hasPermission("PlayerWorldsPro.access")) {
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Access.Insufficient-Permission").replace("&", "§"));
            } else if (inventoryClickEvent.isLeftClick()) {
                ConfigManager.getDataConfig().set("Worlds." + string2 + ".Access", (Object)"PUBLIC");
                ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Access.Public").replace("&", "§"));
            } else if (inventoryClickEvent.isRightClick()) {
                ConfigManager.getDataConfig().set("Worlds." + string2 + ".Access", (Object)"PRIVATE");
                ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Access.Private").replace("&", "§"));
                for (Player player2 : Bukkit.getOnlinePlayers()) {
                    if (!player2.getWorld().getName().equals(string2) || WorldManager.c(player2, string2)) continue;
                    if (BasicManager.b()) {
                        BasicManager.a(player2, BasicManager.c());
                        continue;
                    }
                    player2.kickPlayer(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Lobby-Is-Not-Configured").replace("&", "§"));
                }
            }
            player.closeInventory();
            GUI_Settings.b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == MaterialManager.a(MaterialManager.a.PLAYER_HEAD) && string.equals(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Add-Member.Displayname").replace("&", "§"))) {
            GUI_Settings_AddMember.a(player);
        } else if (inventoryClickEvent.getCurrentItem().getType() == MaterialManager.a(MaterialManager.a.PLAYER_HEAD) && string.equals(Main.getPlugin().getConfig().getString("GUI.Settings.Items.Remove-Member.Displayname").replace("&", "§"))) {
            GUI_Settings_RemoveMember.a(player);
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.GOLD_INGOT) {
            GUI_Extend_PlayerWorld.a(player);
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.TNT && inventoryClickEvent.isShiftClick()) {
            player.closeInventory();
            WorldManager.a((CommandSender)player, b.get(player.getName()));
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.LEATHER_BOOTS) {
            GUI_Settings_KickPlayer.a(player);
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.BARRIER) {
            if (inventoryClickEvent.isLeftClick()) {
                GUI_Settings_BanPlayer.a(player);
            } else if (inventoryClickEvent.isRightClick()) {
                GUI_Settings_UnbanPlayer.a(player);
            }
        }
    }

    public static HashMap<String, String> b() {
        return b;
    }
}

