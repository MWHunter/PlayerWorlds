/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
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

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI_Extend_PlayerWorld
implements Listener {
    private final Main a;

    public GUI_Extend_PlayerWorld(Main main) {
        this.a = main;
    }

    public void a(Player player) {
        if (!this.a.k().b().containsKey(player.getName())) {
            player.closeInventory();
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, (int)27, (String)this.a.getConfig().getString("GUI.Extend-Player-World.Title").replace("&", "§"));
        player.openInventory(inventory);
        inventory.setItem(11, this.a("First"));
        inventory.setItem(13, this.a("Second"));
        inventory.setItem(15, this.a("Third"));
    }

    private ItemStack a(String string) {
        ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Extend-Player-World.Items." + string + ".Displayname").replace("&", "§").replace("%length%", this.a.getConfig().getString("GUI.Extend-Player-World.Items." + string + ".Length")));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string2 : this.a.getConfig().getStringList("GUI.Extend-Player-World.Items." + string + ".Lore")) {
            string2 = string2.replace("&", "§");
            string2 = string2.replace("%price%", this.a.getConfig().getString("GUI.Extend-Player-World.Items." + string + ".Price"));
            arrayList.add(string2);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void a(InventoryClickEvent inventoryClickEvent) {
        Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.Extend-Player-World.Title").replace("&", "§"))) {
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
            switch (inventoryClickEvent.getSlot()) {
                case 11: {
                    this.a.G().a((CommandSender)player, this.a.k().b().get(player.getName()), "First", true);
                    break;
                }
                case 13: {
                    this.a.G().a((CommandSender)player, this.a.k().b().get(player.getName()), "Second", true);
                    break;
                }
                case 15: {
                    this.a.G().a((CommandSender)player, this.a.k().b().get(player.getName()), "Third", true);
                }
            }
        }
        player.closeInventory();
        this.a.k().b().remove(player.getName());
    }
}

