/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Difficulty
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
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI_Settings_ChangeDifficulty
implements Listener {
    private final Main a;

    public GUI_Settings_ChangeDifficulty(Main main) {
        this.a = main;
    }

    public void a(Player player) {
        if (!this.a.k().b().containsKey(player.getName())) {
            player.closeInventory();
            return;
        }
        if (this.a.getConfig().getBoolean("Permissions.Change-Difficulty") && !player.hasPermission("PlayerWorldsPro.changeDifficulty")) {
            player.closeInventory();
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Change-Difficulty.Insufficient-Permission").replace("&", "§"));
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, (int)27, (String)this.a.getConfig().getString("GUI.Change-Difficulty.Title").replace("&", "§"));
        player.openInventory(inventory);
        inventory.setItem(10, this.a());
        inventory.setItem(12, this.b());
        inventory.setItem(14, this.c());
        inventory.setItem(16, this.d());
    }

    private ItemStack a() {
        ItemStack itemStack = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Change-Difficulty.Items.Peaceful.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Change-Difficulty.Items.Peaceful.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack b() {
        ItemStack itemStack = new ItemStack(this.a.F().a(MaterialManager.a.WOODEN_SWORD));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Change-Difficulty.Items.Easy.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Change-Difficulty.Items.Easy.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack c() {
        ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Change-Difficulty.Items.Normal.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Change-Difficulty.Items.Normal.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack d() {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Change-Difficulty.Items.Hard.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Change-Difficulty.Items.Hard.Lore")) {
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
        if (!inventoryClickEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.Change-Difficulty.Title").replace("&", "§"))) {
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
        player.closeInventory();
        if (this.a.k().b().containsKey(player.getName())) {
            String string = inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName();
            String string2 = this.a.k().b().get(player.getName());
            World world = Bukkit.getWorld((String)(this.a.getConfig().getString("Basic.World-Prefix") + string2));
            if (string.equals(this.a.getConfig().getString("GUI.Change-Difficulty.Items.Peaceful.Displayname").replace("&", "§"))) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Difficulty", (Object)"PEACEFUL");
                if (world != null) {
                    world.setDifficulty(Difficulty.PEACEFUL);
                }
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Change-Difficulty.Peaceful").replace("&", "§"));
            } else if (string.equals(this.a.getConfig().getString("GUI.Change-Difficulty.Items.Easy.Displayname").replace("&", "§"))) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Difficulty", (Object)"EASY");
                if (world != null) {
                    world.setDifficulty(Difficulty.EASY);
                }
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Change-Difficulty.Easy").replace("&", "§"));
            } else if (string.equals(this.a.getConfig().getString("GUI.Change-Difficulty.Items.Normal.Displayname").replace("&", "§"))) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Difficulty", (Object)"NORMAL");
                if (world != null) {
                    world.setDifficulty(Difficulty.NORMAL);
                }
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Change-Difficulty.Hard").replace("&", "§"));
            } else if (string.equals(this.a.getConfig().getString("GUI.Change-Difficulty.Items.Hard.Displayname").replace("&", "§"))) {
                this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Difficulty", (Object)"HARD");
                if (world != null) {
                    world.setDifficulty(Difficulty.HARD);
                }
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Change-Difficulty.Hard").replace("&", "§"));
            }
            this.a.E().b(ConfigManager.dataOrPlayers.DATA);
            this.a.E().c(ConfigManager.dataOrPlayers.DATA);
        }
        this.a.k().b().remove(player.getName());
    }
}

