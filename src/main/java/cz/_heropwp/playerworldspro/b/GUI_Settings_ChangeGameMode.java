/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.GameMode
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
package cz._heropwp.playerworldspro.b;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.b.GUI_Settings;
import cz._heropwp.playerworldspro.b.GUI_Settings_ChangeGameMode_SelectPlayer;
import cz._heropwp.playerworldspro.d.BasicManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI_Settings_ChangeGameMode
implements Listener {
    private final Main a;

    public GUI_Settings_ChangeGameMode(Main main) {
        this.a = main;
    }

    public void a(Player player) {
        if (!this.a.k().b().containsKey(player.getName()) || Bukkit.getWorld((String)(this.a.getConfig().getString("Basic.World-Prefix") + this.a.k().b().get(player.getName()))) == null) {
            player.closeInventory();
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Unloaded-World").replace("&", "§"));
            return;
        }
        if (this.a.getConfig().getBoolean("Permissions.Change-GameMode") && !player.hasPermission("PlayerWorldsPro.changeGameMode")) {
            player.closeInventory();
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Change-GameMode.Insufficient-Permission").replace("&", "§"));
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, (int)27, (String)this.a.getConfig().getString("GUI.Change-GameMode.Title").replace("&", "§"));
        player.openInventory(inventory);
        inventory.setItem(10, this.a());
        inventory.setItem(12, this.b());
        inventory.setItem(14, this.c());
        inventory.setItem(16, this.d());
    }

    private ItemStack a() {
        ItemStack itemStack = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Change-GameMode.Items.Survival.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Change-GameMode.Items.Survival.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack b() {
        ItemStack itemStack = new ItemStack(Material.BRICK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Change-GameMode.Items.Creative.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Change-GameMode.Items.Creative.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack c() {
        ItemStack itemStack = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Change-GameMode.Items.Adventure.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Change-GameMode.Items.Adventure.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack d() {
        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Change-GameMode.Items.Spectator.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Change-GameMode.Items.Spectator.Lore")) {
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
        if (!inventoryClickEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.Change-GameMode.Title").replace("&", "§"))) {
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
        if (this.a.k().b().containsKey(player.getName()) && Bukkit.getWorld((String)(this.a.getConfig().getString("Basic.World-Prefix") + this.a.k().b().get(player.getName()))) != null) {
            String string = inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName();
            if (string.equals(this.a.getConfig().getString("GUI.Change-GameMode.Items.Survival.Displayname").replace("&", "§"))) {
                if (inventoryClickEvent.isLeftClick()) {
                    if (player.getWorld().getName().equals(this.a.getConfig().getString("Basic.World-Prefix") + this.a.k().b().get(player.getName()))) {
                        player.closeInventory();
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Change-GameMode.Survival").replace("&", "§"));
                    } else {
                        player.closeInventory();
                        player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Same-World").replace("&", "§"));
                    }
                    this.a.k().b().remove(player.getName());
                } else if (inventoryClickEvent.isRightClick()) {
                    this.a.q().a(player, GameMode.SURVIVAL);
                }
            } else if (string.equals(this.a.getConfig().getString("GUI.Change-GameMode.Items.Creative.Displayname").replace("&", "§"))) {
                if (inventoryClickEvent.isLeftClick()) {
                    if (player.getWorld().getName().equals(this.a.getConfig().getString("Basic.World-Prefix") + this.a.k().b().get(player.getName()))) {
                        player.closeInventory();
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Change-GameMode.Creative").replace("&", "§"));
                    } else {
                        player.closeInventory();
                        player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Same-World").replace("&", "§"));
                    }
                    this.a.k().b().remove(player.getName());
                } else if (inventoryClickEvent.isRightClick()) {
                    this.a.q().a(player, GameMode.CREATIVE);
                }
            } else if (string.equals(this.a.getConfig().getString("GUI.Change-GameMode.Items.Adventure.Displayname").replace("&", "§"))) {
                if (inventoryClickEvent.isLeftClick()) {
                    if (player.getWorld().getName().equals(this.a.getConfig().getString("Basic.World-Prefix") + this.a.k().b().get(player.getName()))) {
                        player.closeInventory();
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Change-GameMode.Adventure").replace("&", "§"));
                    } else {
                        player.closeInventory();
                        player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Same-World").replace("&", "§"));
                    }
                    this.a.k().b().remove(player.getName());
                } else if (inventoryClickEvent.isRightClick()) {
                    this.a.q().a(player, GameMode.ADVENTURE);
                }
            } else if (string.equals(this.a.getConfig().getString("GUI.Change-GameMode.Items.Spectator.Displayname").replace("&", "§"))) {
                if (inventoryClickEvent.isLeftClick()) {
                    if (player.getWorld().getName().equals(this.a.getConfig().getString("Basic.World-Prefix") + this.a.k().b().get(player.getName()))) {
                        player.closeInventory();
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Change-GameMode.Spectator").replace("&", "§"));
                    } else {
                        player.closeInventory();
                        player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Same-World").replace("&", "§"));
                    }
                    this.a.k().b().remove(player.getName());
                } else if (inventoryClickEvent.isRightClick()) {
                    this.a.q().a(player, GameMode.SPECTATOR);
                }
            }
        } else {
            player.closeInventory();
            this.a.k().b().remove(player.getName());
        }
    }
}

