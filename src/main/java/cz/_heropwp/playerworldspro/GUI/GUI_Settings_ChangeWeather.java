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

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.CoreManagers.ConfigManager;
import cz._heropwp.playerworldspro.CoreManagers.MaterialManager;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI_Settings_ChangeWeather
implements Listener {
    private final Main a;

    public GUI_Settings_ChangeWeather(Main main) {
        this.a = main;
    }

    public void a(Player player) {
        if (!this.a.k().b().containsKey(player.getName()) || Bukkit.getWorld((String)(this.a.getConfig().getString("Basic.World-Prefix") + this.a.k().b().get(player.getName()))) == null) {
            player.closeInventory();
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Unloaded-World").replace("&", "§"));
            return;
        }
        if (this.a.getConfig().getBoolean("Permissions.Change-Weather") && !player.hasPermission("PlayerWorldsPro.changeWeather")) {
            player.closeInventory();
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Change-Weather.Insufficient-Permission").replace("&", "§"));
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, (int)27, (String)this.a.getConfig().getString("GUI.Change-Weather.Title").replace("&", "§"));
        player.openInventory(inventory);
        inventory.setItem(10, this.a());
        inventory.setItem(11, this.b());
        inventory.setItem(12, this.c());
        inventory.setItem(16, this.a(player.getName()));
    }

    private ItemStack a() {
        ItemStack itemStack = new ItemStack(Material.DEAD_BUSH);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Change-Weather.Items.Clear.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Change-Weather.Items.Clear.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack b() {
        ItemStack itemStack = new ItemStack(Material.WATER_BUCKET);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Change-Weather.Items.Rain.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Change-Weather.Items.Rain.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack c() {
        ItemStack itemStack = new ItemStack(Material.WATER_BUCKET);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Change-Weather.Items.Thunder.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Change-Weather.Items.Thunder.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack a(String string) {
        String string2 = "Unlock";
        if (this.a.E().a(ConfigManager.dataOrPlayers.DATA).getBoolean("Worlds." + string + ".WeatherCycle")) {
            string2 = "Lock";
        }
        ItemStack itemStack = new ItemStack(this.a.F().a(MaterialManager.a.DEAD_BUSH));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Change-Weather.Items.Weather-Cycle-" + string2 + ".Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string3 : this.a.getConfig().getStringList("GUI.Change-Weather.Items.Weather-Cycle-" + string2 + ".Lore")) {
            string3 = string3.replace("&", "§");
            arrayList.add(string3);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void a(InventoryClickEvent inventoryClickEvent) {
        Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.Change-Weather.Title").replace("&", "§"))) {
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
            boolean bl = this.a.G().e(string2);
            player.closeInventory();
            if (string.equals(this.a.getConfig().getString("GUI.Change-Weather.Items.Clear.Displayname").replace("&", "§"))) {
                if (!bl) {
                    this.a.G().b(string2, true);
                }
                world.setThundering(false);
                world.setStorm(false);
                if (!bl) {
                    this.a.G().b(string2, false);
                }
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Change-Weather.Clear").replace("&", "§"));
            } else if (string.equals(this.a.getConfig().getString("GUI.Change-Weather.Items.Rain.Displayname").replace("&", "§"))) {
                if (!bl) {
                    this.a.G().b(string2, true);
                }
                world.setThundering(false);
                world.setStorm(true);
                if (!bl) {
                    this.a.G().b(string2, false);
                }
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Change-Weather.Rain").replace("&", "§"));
            } else if (string.equals(this.a.getConfig().getString("GUI.Change-Weather.Items.Thunder.Displayname").replace("&", "§"))) {
                if (!bl) {
                    this.a.G().b(string2, true);
                }
                world.setThundering(true);
                world.setStorm(true);
                if (!bl) {
                    this.a.G().b(string2, false);
                }
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Change-Weather.Thunder").replace("&", "§"));
            } else if (string.equals(this.a.getConfig().getString("GUI.Change-Weather.Items.Weather-Cycle-Lock.Displayname").replace("&", "§")) && this.a.G().e(string2)) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".WeatherCycle", (Object)false);
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Weather-Cycle.Lock").replace("&", "§"));
            } else if (string.equals(this.a.getConfig().getString("GUI.Change-Weather.Items.Weather-Cycle-Unlock.Displayname").replace("&", "§")) && !this.a.G().e(string2)) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".WeatherCycle", (Object)true);
                this.a.E().b(ConfigManager.dataOrPlayers.DATA);
                this.a.E().c(ConfigManager.dataOrPlayers.DATA);
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Weather-Cycle.Unlock").replace("&", "§"));
            }
        } else {
            player.closeInventory();
        }
        this.a.k().b().remove(player.getName());
    }
}

