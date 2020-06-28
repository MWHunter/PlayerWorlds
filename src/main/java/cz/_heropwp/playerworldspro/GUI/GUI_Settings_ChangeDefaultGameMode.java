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

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.CoreManagers.ConfigManager;
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

public class GUI_Settings_ChangeDefaultGameMode
implements Listener {
    private final Main a;

    public GUI_Settings_ChangeDefaultGameMode(Main main) {
        this.a = main;
    }

    public void a(Player player) {
        if (!this.a.k().b().containsKey(player.getName())) {
            player.closeInventory();
            return;
        }
        if (this.a.getConfig().getBoolean("Permissions.Change-Default-GameMode") && !player.hasPermission("PlayerWorldsPro.changeGameMode.default")) {
            player.closeInventory();
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Change-Default-GameMode.Insufficient-Permission").replace("&", "§"));
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, (int)27, (String)this.a.getConfig().getString("GUI.Change-Default-GameMode.Title").replace("&", "§"));
        player.openInventory(inventory);
        inventory.setItem(10, this.a());
        inventory.setItem(12, this.b());
        inventory.setItem(14, this.c());
        inventory.setItem(16, this.d());
    }

    private ItemStack a() {
        ItemStack itemStack = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Change-Default-GameMode.Items.Survival.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Change-Default-GameMode.Items.Survival.Lore")) {
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
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Change-Default-GameMode.Items.Creative.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Change-Default-GameMode.Items.Creative.Lore")) {
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
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Change-Default-GameMode.Items.Adventure.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Change-Default-GameMode.Items.Adventure.Lore")) {
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
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Change-Default-GameMode.Items.Spectator.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Change-Default-GameMode.Items.Spectator.Lore")) {
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
        if (!inventoryClickEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.Change-Default-GameMode.Title").replace("&", "§"))) {
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
        if (this.a.k().b().containsKey(player.getName())) {
            String string = inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName();
            String string2 = this.a.k().b().get(player.getName());
            if (string.equals(this.a.getConfig().getString("GUI.Change-Default-GameMode.Items.Survival.Displayname").replace("&", "§"))) {
                this.a(player, string2, "Survival");
            } else if (string.equals(this.a.getConfig().getString("GUI.Change-Default-GameMode.Items.Creative.Displayname").replace("&", "§"))) {
                this.a(player, string2, "Creative");
            } else if (string.equals(this.a.getConfig().getString("GUI.Change-Default-GameMode.Items.Adventure.Displayname").replace("&", "§"))) {
                this.a(player, string2, "Adventure");
            } else if (string.equals(this.a.getConfig().getString("GUI.Change-Default-GameMode.Items.Spectator.Displayname").replace("&", "§"))) {
                this.a(player, string2, "Spectator");
            }
        }
        player.closeInventory();
        this.a.k().b().remove(player.getName());
    }

    private void a(Player player, String string, String string2) {
        this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string + ".GameMode", (Object)string2.toUpperCase());
        this.a.E().b(ConfigManager.dataOrPlayers.DATA);
        this.a.E().c(ConfigManager.dataOrPlayers.DATA);
        player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Change-Default-GameMode." + string2).replace("&", "§"));
    }
}

