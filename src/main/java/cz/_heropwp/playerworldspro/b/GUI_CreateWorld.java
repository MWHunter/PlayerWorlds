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
package cz._heropwp.playerworldspro.b;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.d.ConfigManager;
import cz._heropwp.playerworldspro.d.MaterialManager;
import cz._heropwp.playerworldspro.d.WorldManager;
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
    private final Main a;

    public GUI_CreateWorld(Main main) {
        this.a = main;
    }

    public void a(Player player) {
        if (this.a.G().c(player.getName())) {
            player.closeInventory();
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Already-Have").replace("&", "§"));
            return;
        }
        if (this.a.getConfig().getBoolean("Permissions.Create-World") && !player.hasPermission("PlayerWorldsPro.createWorld")) {
            player.closeInventory();
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Insufficient-Permission-Create-World").replace("&", "§"));
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, (int)27, (String)this.a.getConfig().getString("GUI.Create-World.Title").replace("&", "§"));
        player.openInventory(inventory);
        inventory.setItem(10, this.b(player));
        inventory.setItem(11, this.c(player));
        inventory.setItem(12, this.d(player));
        inventory.setItem(15, this.e(player));
        inventory.setItem(16, this.f(player));
    }

    private ItemStack b(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Normal") && !player.hasPermission("PlayerWorldsPro.createWorld.normal") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(this.a.F().a(MaterialManager.a.OAK_SAPLING));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Create-World.Items.Normal.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Create-World.Items.Normal.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack c(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Flat") && !player.hasPermission("PlayerWorldsPro.createWorld.flat") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.GRASS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Create-World.Items.Flat.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Create-World.Items.Flat.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack d(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Empty") && !player.hasPermission("PlayerWorldsPro.createWorld.empty") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Create-World.Items.Empty.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Create-World.Items.Empty.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack e(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Pre-Built-Maps") && !player.hasPermission("PlayerWorldsPro.createWorld.preBuiltMaps") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(Material.BRICK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Create-World.Items.Pre-Built-Maps.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Create-World.Items.Pre-Built-Maps.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack f(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Custom-Generators") && !player.hasPermission("PlayerWorldsPro.createWorld.customGenerators") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(this.a.F().a(MaterialManager.a.COMMAND_BLOCK));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Create-World.Items.Custom-Generators.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Create-World.Items.Custom-Generators.Lore")) {
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
        if (!inventoryClickEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.Create-World.Title").replace("&", "§"))) {
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
        if (inventoryClickEvent.getCurrentItem().getType() == this.a.F().a(MaterialManager.a.OAK_SAPLING)) {
            if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Normal") && !player.hasPermission("PlayerWorldsPro.createWorld.normal")) {
                player.closeInventory();
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Insufficient-Permission-Create-World.Type").replace("&", "§"));
                return;
            }
            if (this.a.G().b()) {
                if (this.a.getConfig().getBoolean("Claim.Enabled") && !this.a.E().a(ConfigManager.dataOrPlayers.PLAYERS).contains("Claim." + player.getName())) {
                    this.a.G().a(player, WorldManager.a.NORMAL, null, null, this.a.getConfig().getInt("Claim.Length"), null, true);
                } else {
                    this.a.e().a(player, WorldManager.a.NORMAL, null, null);
                }
            } else {
                this.a.G().a(player, WorldManager.a.NORMAL, null, null, null, null, false);
            }
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.GRASS) {
            if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Flat") && !player.hasPermission("PlayerWorldsPro.createWorld.flat")) {
                player.closeInventory();
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Insufficient-Permission-Create-World.Type").replace("&", "§"));
                return;
            }
            if (this.a.G().b()) {
                if (this.a.getConfig().getBoolean("Claim.Enabled") && !this.a.E().a(ConfigManager.dataOrPlayers.PLAYERS).contains("Claim." + player.getName())) {
                    this.a.G().a(player, WorldManager.a.FLAT, null, null, this.a.getConfig().getInt("Claim.Length"), null, true);
                } else {
                    this.a.e().a(player, WorldManager.a.FLAT, null, null);
                }
            } else {
                this.a.G().a(player, WorldManager.a.FLAT, null, null, null, null, false);
            }
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.BARRIER) {
            if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Empty") && !player.hasPermission("PlayerWorldsPro.createWorld.empty")) {
                player.closeInventory();
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Insufficient-Permission-Create-World.Type").replace("&", "§"));
                return;
            }
            if (this.a.G().b()) {
                if (this.a.getConfig().getBoolean("Claim.Enabled") && !this.a.E().a(ConfigManager.dataOrPlayers.PLAYERS).contains("Claim." + player.getName())) {
                    this.a.G().a(player, WorldManager.a.EMPTY, null, null, this.a.getConfig().getInt("Claim.Length"), null, true);
                } else {
                    this.a.e().a(player, WorldManager.a.EMPTY, null, null);
                }
            } else {
                this.a.G().a(player, WorldManager.a.EMPTY, null, null, null, null, false);
            }
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.BRICK) {
            if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Pre-Built-Maps") && !player.hasPermission("PlayerWorldsPro.createWorld.preBuiltMaps")) {
                player.closeInventory();
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Insufficient-Permission-Create-World.Type").replace("&", "§"));
                return;
            }
            this.a.j().a(player);
        } else if (inventoryClickEvent.getCurrentItem().getType() == this.a.F().a(MaterialManager.a.COMMAND_BLOCK)) {
            if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Custom-Generators") && !player.hasPermission("PlayerWorldsPro.createWorld.customGenerators")) {
                player.closeInventory();
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Insufficient-Permission-Create-World.Type").replace("&", "§"));
                return;
            }
            this.a.g().a(player);
        }
    }
}

