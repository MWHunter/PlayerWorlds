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
import cz._heropwp.playerworldspro.CoreManagers.WorldManager;
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

public class GUI_CustomGenerators
implements Listener {
    private final Main a;

    public GUI_CustomGenerators(Main main) {
        this.a = main;
    }

    public void a(Player player) {
        if (this.a.G().c(player.getName())) {
            player.closeInventory();
            player.sendMessage(this.a.D().getPluginPrefix() + this.a.getConfig().getString("Messages.Already-Have").replace("&", "§"));
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, (int)54, (String)this.a.getConfig().getString("GUI.Custom-Generators.Title").replace("&", "§"));
        player.openInventory(inventory);
        int n = 0;
        if (this.a.getConfig().contains("Custom-Generators")) {
            for (String string : this.a.getConfig().getStringList("Custom-Generators")) {
                if (n >= inventory.getSize()) break;
                inventory.setItem(n, this.a(string));
                ++n;
            }
        }
    }

    private ItemStack a(String string) {
        ItemStack itemStack = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Custom-Generators.Items.Generator.Displayname").replace("&", "§").replace("%name%", string));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string2 : this.a.getConfig().getStringList("GUI.Custom-Generators.Items.Generator.Lore")) {
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
        if (!inventoryClickEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.Custom-Generators.Title").replace("&", "§"))) {
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
        if (inventoryClickEvent.getCurrentItem().getType() == Material.PAPER) {
            String string = (String)this.a.getConfig().getStringList("Custom-Generators").get(inventoryClickEvent.getSlot());
            if (this.a.getConfig().getStringList("Custom-Generators").contains(string)) {
                if (this.a.G().b()) {
                    if (this.a.getConfig().getBoolean("Claim.Enabled") && !ConfigManager.getPlayersConfig().contains("Claim." + player.getName())) {
                        this.a.G().a(player, WorldManager.a.CUSTOM, null, string, this.a.getConfig().getInt("Claim.Length"), null, true);
                    } else {
                        GUI_Buy_PlayerWorld.a(player, WorldManager.a.CUSTOM, null, string);
                    }
                } else {
                    this.a.G().a(player, WorldManager.a.CUSTOM, null, string, null, null, false);
                }
            }
        }
    }
}

