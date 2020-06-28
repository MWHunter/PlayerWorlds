/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.WorldBorder
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

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI_Settings_WorldBorder
implements Listener {
    private final Main a;

    public GUI_Settings_WorldBorder(Main main) {
        this.a = main;
    }

    public void a(Player player) {
        if (!this.a.k().b().containsKey(player.getName()) || Bukkit.getWorld((String)(this.a.getConfig().getString("Basic.World-Prefix") + this.a.k().b().get(player.getName()))) == null) {
            player.closeInventory();
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Unloaded-World").replace("&", "§"));
            return;
        }
        if (this.a.getConfig().getBoolean("Permissions.World-Border") && !player.hasPermission("PlayerWorldsPro.worldBorder")) {
            player.closeInventory();
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.World-Border.Insufficient-Permission").replace("&", "§"));
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, (int)27, (String)this.a.getConfig().getString("GUI.World-Border.Title").replace("&", "§"));
        World world = Bukkit.getWorld((String)(this.a.getConfig().getString("Basic.World-Prefix") + this.a.k().b().get(player.getName())));
        double d2 = world.getWorldBorder().getSize();
        player.openInventory(inventory);
        inventory.setItem(10, this.a(d2));
        inventory.setItem(12, this.a());
        inventory.setItem(14, this.b());
        inventory.setItem(16, this.c());
    }

    private ItemStack a(double d2) {
        ItemStack itemStack = new ItemStack(Material.ARROW);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.World-Border.Items.Change.Displayname").replace("&", "§").replace("%size%", String.valueOf(d2)));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.World-Border.Items.Change.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack a() {
        ItemStack itemStack = new ItemStack(Material.COMPASS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.World-Border.Items.Center.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.World-Border.Items.Center.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack b() {
        ItemStack itemStack = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.World-Border.Items.Set.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.World-Border.Items.Set.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack c() {
        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.World-Border.Items.Reset.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.World-Border.Items.Reset.Lore")) {
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
        if (!inventoryClickEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.World-Border.Title").replace("&", "§"))) {
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
            String string2 = this.a.k().b().get(player.getName());
            World world = Bukkit.getWorld((String)(this.a.getConfig().getString("Basic.World-Prefix") + string2));
            WorldBorder worldBorder = world.getWorldBorder();
            double d2 = worldBorder.getSize();
            if (string.equals(this.a.getConfig().getString("GUI.World-Border.Items.Change.Displayname").replace("&", "§").replace("%size%", String.valueOf(d2)))) {
                if (inventoryClickEvent.isLeftClick()) {
                    if (d2 > 100.0) {
                        worldBorder.setSize(d2 - 100.0);
                    } else {
                        worldBorder.setSize(1.0);
                    }
                } else if (inventoryClickEvent.isRightClick()) {
                    worldBorder.setSize(d2 + 100.0);
                }
                inventoryClickEvent.getView().getTopInventory().setItem(inventoryClickEvent.getSlot(), this.a(worldBorder.getSize()));
                return;
            }
            if (string.equals(this.a.getConfig().getString("GUI.World-Border.Items.Center.Displayname").replace("&", "§"))) {
                player.closeInventory();
                if (world.getName().equals(player.getWorld().getName())) {
                    worldBorder.setCenter(player.getLocation());
                    player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.World-Border.Center").replace("&", "§"));
                } else {
                    player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Same-World").replace("&", "§"));
                }
            } else if (string.equals(this.a.getConfig().getString("GUI.World-Border.Items.Set.Displayname").replace("&", "§"))) {
                worldBorder.setSize(100.0);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.World-Border.Set").replace("&", "§").replace("%size%", "100"));
            } else if (string.equals(this.a.getConfig().getString("GUI.World-Border.Items.Reset.Displayname").replace("&", "§"))) {
                worldBorder.setSize(2.9999984E7);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.World-Border.Reset").replace("&", "§"));
            }
        }
        player.closeInventory();
        this.a.k().b().remove(player.getName());
    }
}

