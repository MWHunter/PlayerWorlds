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
    private final Main a;
    private HashMap<String, String> b;

    public GUI_Settings(Main main) {
        this.a = main;
        this.b = new HashMap();
    }

    public void a(Player player, String string) {
        this.b.put(player.getName(), string);
        Inventory inventory = Bukkit.createInventory(null, (int)54, (String)this.a.getConfig().getString("GUI.Settings.Title").replace("&", "§"));
        player.openInventory(inventory);
        inventory.setItem(0, this.a(player));
        inventory.setItem(1, this.b(player));
        inventory.setItem(4, this.d(player));
        inventory.setItem(7, this.e(player));
        inventory.setItem(8, this.f(player));
        inventory.setItem(9, this.c(player));
        inventory.setItem(10, this.h(player));
        inventory.setItem(12, this.n(player));
        inventory.setItem(13, this.o(player));
        inventory.setItem(14, this.p(player));
        inventory.setItem(16, this.g(player));
        inventory.setItem(17, this.k(player));
        inventory.setItem(25, this.i(player));
        inventory.setItem(26, this.j(player));
        inventory.setItem(30, this.q(player));
        inventory.setItem(31, this.r(player));
        inventory.setItem(32, this.s(player));
        inventory.setItem(34, this.l(player));
        inventory.setItem(35, this.m(player));
        if (this.a.G().b()) {
            inventory.setItem(46, this.c());
        }
        inventory.setItem(49, this.d());
        inventory.setItem(51, this.t(player));
        inventory.setItem(52, this.u(player));
    }

    private ItemStack a(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Change-Time") && !player.hasPermission("PlayerWorldsPro.changeTime") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(this.a.F().a(MaterialManager.a.CLOCK));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Change-Time.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Change-Time.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack b(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Change-Weather") && !player.hasPermission("PlayerWorldsPro.changeWeather") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.WATER_BUCKET);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Change-Weather.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Change-Weather.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack c(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Change-Difficulty") && !player.hasPermission("PlayerWorldsPro.changeDifficulty") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.BONE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Change-Difficulty.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Change-Difficulty.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack d(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Set-Spawn-Location") && !player.hasPermission("PlayerWorldsPro.setSpawn") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(this.a.F().a(MaterialManager.a.RED_BED));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Spawn-Location.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Spawn-Location.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack e(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Block-Breaking") && !player.hasPermission("PlayerWorldsPro.editBlockBreaking") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Block-Breaking.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Block-Breaking.Lore")) {
            string = string.replace("&", "§");
            String string2 = "Variables.";
            string2 = this.a.G().g(this.b.get(player.getName())) ? string2 + "Enabled" : string2 + "Disabled";
            string = string.replace("%status%", this.a.getConfig().getString(string2).replace("&", "§"));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack f(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Block-Placing") && !player.hasPermission("PlayerWorldsPro.editBlockPlacing") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.BRICK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Block-Placing.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Block-Placing.Lore")) {
            string = string.replace("&", "§");
            String string2 = "Variables.";
            string2 = this.a.G().h(this.b.get(player.getName())) ? string2 + "Enabled" : string2 + "Disabled";
            string = string.replace("%status%", this.a.getConfig().getString(string2).replace("&", "§"));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack g(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.PvP") && !player.hasPermission("PlayerWorldsPro.editPvP") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.PvP.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.PvP.Lore")) {
            string = string.replace("&", "§");
            String string2 = "Variables.";
            string2 = this.a.G().i(this.b.get(player.getName())) ? string2 + "Enabled" : string2 + "Disabled";
            string = string.replace("%status%", this.a.getConfig().getString(string2).replace("&", "§"));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack h(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.World-Border") && !player.hasPermission("PlayerWorldsPro.worldBorder") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.World-Border.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.World-Border.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack i(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Item-Pickup") && !player.hasPermission("PlayerWorldsPro.editItemPickup") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Pickup.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Pickup.Lore")) {
            string = string.replace("&", "§");
            String string2 = "Variables.";
            string2 = this.a.G().k(this.b.get(player.getName())) ? string2 + "Enabled" : string2 + "Disabled";
            string = string.replace("%status%", this.a.getConfig().getString(string2).replace("&", "§"));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack j(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Drop-Item") && !player.hasPermission("PlayerWorldsPro.editDropItem") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.ROTTEN_FLESH);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Drop.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Drop.Lore")) {
            string = string.replace("&", "§");
            String string2 = "Variables.";
            string2 = this.a.G().l(this.b.get(player.getName())) ? string2 + "Enabled" : string2 + "Disabled";
            string = string.replace("%status%", this.a.getConfig().getString(string2).replace("&", "§"));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack k(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Player-Damage") && !player.hasPermission("PlayerWorldsPro.editPlayerDamage") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Damage.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Damage.Lore")) {
            string = string.replace("&", "§");
            String string2 = "Variables.";
            string2 = this.a.G().m(this.b.get(player.getName())) ? string2 + "Enabled" : string2 + "Disabled";
            string = string.replace("%status%", this.a.getConfig().getString(string2).replace("&", "§"));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack l(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Hunger") && !player.hasPermission("PlayerWorldsPro.editHunger") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.APPLE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Hunger.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Hunger.Lore")) {
            string = string.replace("&", "§");
            String string2 = "Variables.";
            string2 = this.a.G().n(this.b.get(player.getName())) ? string2 + "Enabled" : string2 + "Disabled";
            string = string.replace("%status%", this.a.getConfig().getString(string2).replace("&", "§"));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack m(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Bucket") && !player.hasPermission("PlayerWorldsPro.editBucket") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.BUCKET);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Bucket.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Bucket.Lore")) {
            string = string.replace("&", "§");
            String string2 = "Variables.";
            string2 = this.a.G().o(this.b.get(player.getName())) ? string2 + "Enabled" : string2 + "Disabled";
            string = string.replace("%status%", this.a.getConfig().getString(string2).replace("&", "§"));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack n(Player player) {
        if (this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission") && this.a.getConfig().getBoolean("Permissions.Change-GameMode") && !player.hasPermission("PlayerWorldsPro.changeGameMode") && this.a.getConfig().getBoolean("Permissions.Change-Default-GameMode") && !player.hasPermission("PlayerWorldsPro.changeGameMode.default")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.DIAMOND);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.GameMode.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.GameMode.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack o(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Teleport") && !player.hasPermission("PlayerWorldsPro.teleport") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(this.a.F().a(MaterialManager.a.PLAYER_HEAD), 1, (short)SkullType.PLAYER.ordinal());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Teleport.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Teleport.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack p(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Fly") && !player.hasPermission("PlayerWorldsPro.fly") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.FEATHER, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Fly.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Fly.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack q(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Access") && !player.hasPermission("PlayerWorldsPro.access") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(this.a.F().a(MaterialManager.a.WRITABLE_BOOK));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Access.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Access.Lore")) {
            string = string.replace("&", "§");
            String string2 = "Variables." + this.a.G().p(this.b.get(player.getName()));
            string = string.replace("%status%", this.a.getConfig().getString(string2).replace("&", "§"));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack r(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Access") && !player.hasPermission("PlayerWorldsPro.access") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(this.a.F().a(MaterialManager.a.PLAYER_HEAD), 1, (short)SkullType.PLAYER.ordinal());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Add-Member.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Add-Member.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack s(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Access") && !player.hasPermission("PlayerWorldsPro.access") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(this.a.F().a(MaterialManager.a.PLAYER_HEAD), 1, (short)SkullType.PLAYER.ordinal());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Remove-Member.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Remove-Member.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack c() {
        ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Extend.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Extend.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack d() {
        ItemStack itemStack = new ItemStack(Material.TNT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Delete.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Delete.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack t(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Kick") && !player.hasPermission("PlayerWorldsPro.kick") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Kick.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Kick.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack u(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Ban") && !player.hasPermission("PlayerWorldsPro.ban") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Settings.Items.Ban.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Settings.Items.Ban.Lore")) {
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
        if (!inventoryClickEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.Settings.Title").replace("&", "§"))) {
            return;
        }
        if (inventoryClickEvent.getView() == null) {
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
        if (!this.b.containsKey(player.getName())) {
            player.closeInventory();
            return;
        }
        String string = inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName();
        String string2 = this.b.get(player.getName());
        if (inventoryClickEvent.getCurrentItem().getType() == this.a.F().a(MaterialManager.a.CLOCK)) {
            this.a.r().a(player);
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.WATER_BUCKET) {
            this.a.s().a(player);
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.BONE) {
            this.a.o().a(player);
        } else if (inventoryClickEvent.getCurrentItem().getType().toString().contains(this.a.F().a(MaterialManager.a.RED_BED).toString())) {
            if (this.a.getConfig().getBoolean("Permissions.Set-Spawn-Location") && !player.hasPermission("PlayerWorldsPro.setSpawn")) {
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Insufficient-Permission").replace("&", "§"));
            } else {
                this.a.G().a(player, string2, true);
            }
            player.closeInventory();
            this.a.k().b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.IRON_PICKAXE) {
            player.closeInventory();
            if (this.a.getConfig().getBoolean("Permissions.Block-Breaking") && !player.hasPermission("PlayerWorldsPro.editBlockBreaking")) {
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Block-Breaking.Insufficient-Permission").replace("&", "§"));
            } else if (inventoryClickEvent.isLeftClick()) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Block-Breaking", (Object)true);
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Block-Breaking.Allow").replace("&", "§"));
            } else if (inventoryClickEvent.isRightClick()) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Block-Breaking", (Object)false);
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Block-Breaking.Deny").replace("&", "§"));
            }
            this.a.k().b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.BRICK) {
            player.closeInventory();
            if (this.a.getConfig().getBoolean("Permissions.Block-Placing") && !player.hasPermission("PlayerWorldsPro.editBlockPlacing")) {
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Block-Placing.Insufficient-Permission").replace("&", "§"));
            } else if (inventoryClickEvent.isLeftClick()) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Block-Placing", (Object)true);
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Block-Placing.Allow").replace("&", "§"));
            } else if (inventoryClickEvent.isRightClick()) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Block-Placing", (Object)false);
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Block-Placing.Deny").replace("&", "§"));
            }
            this.a.k().b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.IRON_SWORD) {
            player.closeInventory();
            if (this.a.getConfig().getBoolean("Permissions.PvP") && !player.hasPermission("PlayerWorldsPro.editPvP")) {
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.PvP.Insufficient-Permission").replace("&", "§"));
            } else if (inventoryClickEvent.isLeftClick()) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".PvP", (Object)true);
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.PvP.Allow").replace("&", "§"));
            } else if (inventoryClickEvent.isRightClick()) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".PvP", (Object)false);
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.PvP.Deny").replace("&", "§"));
            }
            this.a.k().b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.BARRIER && string.equals(this.a.getConfig().getString("GUI.Settings.Items.World-Border.Displayname").replace("&", "§"))) {
            this.a.z().a(player);
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
            player.closeInventory();
            if (this.a.getConfig().getBoolean("Permissions.Item-Pickup") && !player.hasPermission("PlayerWorldsPro.editItemPickup")) {
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Pickup.Insufficient-Permission").replace("&", "§"));
            } else if (inventoryClickEvent.isLeftClick()) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Pickup", (Object)true);
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Pickup.Allow").replace("&", "§"));
            } else if (inventoryClickEvent.isRightClick()) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Pickup", (Object)false);
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Pickup.Deny").replace("&", "§"));
            }
            this.a.k().b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.ROTTEN_FLESH) {
            player.closeInventory();
            if (this.a.getConfig().getBoolean("Permissions.Drop-Item") && !player.hasPermission("PlayerWorldsPro.editDropItem")) {
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Drop.Insufficient-Permission").replace("&", "§"));
            } else if (inventoryClickEvent.isLeftClick()) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Drop", (Object)true);
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Drop.Allow").replace("&", "§"));
            } else if (inventoryClickEvent.isRightClick()) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Drop", (Object)false);
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Drop.Deny").replace("&", "§"));
            }
            this.a.k().b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.LEATHER_CHESTPLATE) {
            player.closeInventory();
            if (this.a.getConfig().getBoolean("Permissions.Player-Damage") && !player.hasPermission("PlayerWorldsPro.editPlayerDamage")) {
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Damage.Insufficient-Permission").replace("&", "§"));
            } else if (inventoryClickEvent.isLeftClick()) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Damage", (Object)true);
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Damage.Allow").replace("&", "§"));
            } else if (inventoryClickEvent.isRightClick()) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Damage", (Object)false);
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Damage.Deny").replace("&", "§"));
            }
            this.a.k().b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.APPLE) {
            player.closeInventory();
            if (this.a.getConfig().getBoolean("Permissions.Hunger") && !player.hasPermission("PlayerWorldsPro.editHunger")) {
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Hunger.Insufficient-Permission").replace("&", "§"));
            } else if (inventoryClickEvent.isLeftClick()) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Hunger", (Object)true);
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Hunger.Allow").replace("&", "§"));
            } else if (inventoryClickEvent.isRightClick()) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Hunger", (Object)false);
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Hunger.Deny").replace("&", "§"));
            }
            this.a.k().b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.BUCKET) {
            player.closeInventory();
            if (this.a.getConfig().getBoolean("Permissions.Bucket") && !player.hasPermission("PlayerWorldsPro.editBucket")) {
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Bucket.Insufficient-Permission").replace("&", "§"));
            } else if (inventoryClickEvent.isLeftClick()) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Bucket", (Object)true);
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Bucket.Allow").replace("&", "§"));
            } else if (inventoryClickEvent.isRightClick()) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Bucket", (Object)false);
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Bucket.Deny").replace("&", "§"));
            }
            this.a.k().b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.DIAMOND) {
            if (inventoryClickEvent.isLeftClick()) {
                this.a.p().a(player);
            } else if (inventoryClickEvent.isRightClick()) {
                this.a.n().a(player);
            }
        } else if (inventoryClickEvent.getCurrentItem().getType() == this.a.F().a(MaterialManager.a.PLAYER_HEAD) && string.equals(this.a.getConfig().getString("GUI.Settings.Items.Teleport.Displayname").replace("&", "§"))) {
            if (inventoryClickEvent.isLeftClick()) {
                this.a.w().a(player);
            } else if (inventoryClickEvent.isRightClick()) {
                this.a.x().a(player);
            }
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.FEATHER) {
            String string3 = this.a.getConfig().getString("Basic.World-Prefix") + string2;
            if (!player.getWorld().getName().equals(string3)) {
                player.closeInventory();
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Same-World").replace("&", "§"));
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
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Fly").replace("&", "§"));
                player.closeInventory();
                this.a.k().b().remove(player.getName());
            } else if (inventoryClickEvent.isRightClick()) {
                this.a.t().a(player);
            }
        } else if (inventoryClickEvent.getCurrentItem().getType() == this.a.F().a(MaterialManager.a.WRITABLE_BOOK)) {
            if (this.a.getConfig().getBoolean("Permissions.Access") && !player.hasPermission("PlayerWorldsPro.access")) {
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Access.Insufficient-Permission").replace("&", "§"));
            } else if (inventoryClickEvent.isLeftClick()) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Access", (Object)"PUBLIC");
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Access.Public").replace("&", "§"));
            } else if (inventoryClickEvent.isRightClick()) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Access", (Object)"PRIVATE");
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Access.Private").replace("&", "§"));
                for (Player player2 : Bukkit.getOnlinePlayers()) {
                    if (!player2.getWorld().getName().equals(string2) || this.a.G().c(player2, string2)) continue;
                    if (this.a.D().b()) {
                        this.a.D().a(player2, this.a.D().c());
                        continue;
                    }
                    player2.kickPlayer(this.a.D().e() + this.a.getConfig().getString("Messages.Lobby-Is-Not-Configured").replace("&", "§"));
                }
            }
            player.closeInventory();
            this.a.k().b().remove(player.getName());
        } else if (inventoryClickEvent.getCurrentItem().getType() == this.a.F().a(MaterialManager.a.PLAYER_HEAD) && string.equals(this.a.getConfig().getString("GUI.Settings.Items.Add-Member.Displayname").replace("&", "§"))) {
            this.a.l().a(player);
        } else if (inventoryClickEvent.getCurrentItem().getType() == this.a.F().a(MaterialManager.a.PLAYER_HEAD) && string.equals(this.a.getConfig().getString("GUI.Settings.Items.Remove-Member.Displayname").replace("&", "§"))) {
            this.a.v().a(player);
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.GOLD_INGOT) {
            this.a.h().a(player);
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.TNT && inventoryClickEvent.isShiftClick()) {
            player.closeInventory();
            this.a.G().a((CommandSender)player, this.b.get(player.getName()));
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.LEATHER_BOOTS) {
            this.a.u().a(player);
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.BARRIER) {
            if (inventoryClickEvent.isLeftClick()) {
                this.a.m().a(player);
            } else if (inventoryClickEvent.isRightClick()) {
                this.a.y().a(player);
            }
        }
    }

    public Main a() {
        return this.a;
    }

    public HashMap<String, String> b() {
        return this.b;
    }
}

