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
 *  org.bukkit.event.inventory.InventoryCloseEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package cz._heropwp.playerworldspro.GUI;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.CoreManagers.WorldManager;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI_Buy_PlayerWorld
implements Listener {
    private final Main a;
    public final HashMap<String, WorldManager.a> b = new HashMap<>();
    public final HashMap<String, String> c = new HashMap<>();
    public final HashMap<String, String> d = new HashMap<>();

    public GUI_Buy_PlayerWorld(Main main) {
        this.a = main;
    }

    public void a(Player player, WorldManager.a a2, String string, String string2) {
        this.b.put(player.getName(), a2);
        this.c.put(player.getName(), string);
        this.d.put(player.getName(), string2);
        Inventory inventory = Bukkit.createInventory(null, 27, this.a.getConfig().getString("GUI.Buy-Player-World.Title").replace("&", "§"));
        player.openInventory(inventory);
        inventory.setItem(11, this.a("First"));
        inventory.setItem(13, this.a("Second"));
        inventory.setItem(15, this.a("Third"));
    }

    private ItemStack a(String string) {
        ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Buy-Player-World.Items." + string + ".Displayname").replace("&", "§").replace("%length%", this.a.getConfig().getString("GUI.Buy-Player-World.Items." + string + ".Length")));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string2 : this.a.getConfig().getStringList("GUI.Buy-Player-World.Items." + string + ".Lore")) {
            string2 = string2.replace("&", "§");
            string2 = string2.replace("%price%", this.a.getConfig().getString("GUI.Buy-Player-World.Items." + string + ".Price"));
            arrayList.add(string2);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void a(InventoryClickEvent inventoryClickEvent) {
        Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.Buy-Player-World.Title").replace("&", "§"))) {
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
        if (this.b.containsKey(player.getName())) {
            if (inventoryClickEvent.getSlot() == 11) {
                int n = this.a.getConfig().getInt("GUI.Buy-Player-World.Items.First.Length");
                int n2 = this.a.getConfig().getInt("GUI.Buy-Player-World.Items.First.Price");
                this.a.G().a(player, this.b.get(player.getName()), this.c.get(player.getName()), this.d.get(player.getName()), n, n2, false);
            } else if (inventoryClickEvent.getSlot() == 13) {
                int n = this.a.getConfig().getInt("GUI.Buy-Player-World.Items.Second.Length");
                int n3 = this.a.getConfig().getInt("GUI.Buy-Player-World.Items.Second.Price");
                this.a.G().a(player, this.b.get(player.getName()), this.c.get(player.getName()), this.d.get(player.getName()), n, n3, false);
            } else if (inventoryClickEvent.getSlot() == 15) {
                int n = this.a.getConfig().getInt("GUI.Buy-Player-World.Items.Third.Length");
                int n4 = this.a.getConfig().getInt("GUI.Buy-Player-World.Items.Third.Price");
                this.a.G().a(player, this.b.get(player.getName()), this.c.get(player.getName()), this.d.get(player.getName()), n, n4, false);
            }
        }
    }

    @EventHandler
    public void a(InventoryCloseEvent inventoryCloseEvent) {
        Player player = (Player)inventoryCloseEvent.getPlayer();
        if (inventoryCloseEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.Buy-Player-World.Title").replace("&", "§"))) {
            this.b.remove(player.getName());
            this.c.remove(player.getName());
            this.d.remove(player.getName());
        }
    }
}

