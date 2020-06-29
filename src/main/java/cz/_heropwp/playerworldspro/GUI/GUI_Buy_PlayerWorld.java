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
    public static final HashMap<String, WorldManager.a> b = new HashMap<>();
    public static final HashMap<String, String> c = new HashMap<>();
    public static final HashMap<String, String> d = new HashMap<>();

    public static void a(Player player, WorldManager.a a2, String string, String string2) {
        b.put(player.getName(), a2);
        c.put(player.getName(), string);
        d.put(player.getName(), string2);
        Inventory inventory = Bukkit.createInventory(null, 27, Main.getPlugin().getConfig().getString("GUI.Buy-Player-World.Title").replace("&", "§"));
        player.openInventory(inventory);
        inventory.setItem(11, a("First"));
        inventory.setItem(13, a("Second"));
        inventory.setItem(15, a("Third"));
    }

    private static ItemStack a(String string) {
        ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Buy-Player-World.Items." + string + ".Displayname").replace("&", "§").replace("%length%", Main.getPlugin().getConfig().getString("GUI.Buy-Player-World.Items." + string + ".Length")));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string2 : Main.getPlugin().getConfig().getStringList("GUI.Buy-Player-World.Items." + string + ".Lore")) {
            string2 = string2.replace("&", "§");
            string2 = string2.replace("%price%", Main.getPlugin().getConfig().getString("GUI.Buy-Player-World.Items." + string + ".Price"));
            arrayList.add(string2);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void a(InventoryClickEvent inventoryClickEvent) {
        Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(Main.getPlugin().getConfig().getString("GUI.Buy-Player-World.Title").replace("&", "§"))) {
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
        if (b.containsKey(player.getName())) {
            if (inventoryClickEvent.getSlot() == 11) {
                int n = Main.getPlugin().getConfig().getInt("GUI.Buy-Player-World.Items.First.Length");
                int n2 = Main.getPlugin().getConfig().getInt("GUI.Buy-Player-World.Items.First.Price");
                WorldManager.a(player, b.get(player.getName()), c.get(player.getName()), d.get(player.getName()), n, n2, false);
            } else if (inventoryClickEvent.getSlot() == 13) {
                int n = Main.getPlugin().getConfig().getInt("GUI.Buy-Player-World.Items.Second.Length");
                int n3 = Main.getPlugin().getConfig().getInt("GUI.Buy-Player-World.Items.Second.Price");
                WorldManager.a(player, b.get(player.getName()), c.get(player.getName()), d.get(player.getName()), n, n3, false);
            } else if (inventoryClickEvent.getSlot() == 15) {
                int n = Main.getPlugin().getConfig().getInt("GUI.Buy-Player-World.Items.Third.Length");
                int n4 = Main.getPlugin().getConfig().getInt("GUI.Buy-Player-World.Items.Third.Price");
                WorldManager.a(player, b.get(player.getName()), c.get(player.getName()), d.get(player.getName()), n, n4, false);
            }
        }
    }

    @EventHandler
    public void a(InventoryCloseEvent inventoryCloseEvent) {
        Player player = (Player)inventoryCloseEvent.getPlayer();
        if (inventoryCloseEvent.getView().getTitle().equals(Main.getPlugin().getConfig().getString("GUI.Buy-Player-World.Title").replace("&", "§"))) {
            b.remove(player.getName());
            c.remove(player.getName());
            d.remove(player.getName());
        }
    }
}

