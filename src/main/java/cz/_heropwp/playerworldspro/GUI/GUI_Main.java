/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.SkullType
 *  org.bukkit.configuration.ConfigurationSection
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
 *  org.bukkit.inventory.meta.SkullMeta
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitTask
 */
package cz._heropwp.playerworldspro.GUI;

import cz._heropwp.playerworldspro.CoreManagers.WorldManager;
import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.CoreManagers.ConfigManager;
import cz._heropwp.playerworldspro.CoreManagers.MaterialManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class GUI_Main
implements Listener {
    private static final HashMap<String, BukkitTask> b = new HashMap<>();
    private static final HashMap<String, Integer> c = new HashMap<>();
    private static final LinkedHashSet<String> d = new LinkedHashSet<>();

    public static void a(Player player) {
        Inventory inventory = Bukkit.createInventory(null, (int)54, (String)Main.getPlugin().getConfig().getString("GUI.Main.Title").replace("&", "§"));
        player.openInventory(inventory);
        c.put(player.getName(), 0);
        inventory.setItem(6, b(player));
        a(player, inventory);
    }

    private static void a(Player player, Inventory inventory) {
        b.put(player.getName(), Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getPlugin(), () -> c(player, inventory), 0L, 10L));
    }

    private static void b(Player player, Inventory inventory) {
        a(player.getName(), false);
        a(9, inventory);
        a(player, inventory);
    }

    private ItemStack a(String string) {
        //ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.PLAYER_HEAD), 1, 3);
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        UUID uUID = new UUID(string.hashCode(), string.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(itemStack, "{SkullOwner:{Id:\"" + uUID + "\",Properties:{textures:[{Value:\"" + string + "\"}]}}}");
    }

    private static void c(Player player, Inventory inventory) {
        inventory.setItem(0, b(player.getName()));
        inventory.setItem(8, c(player.getName()));
        inventory.setItem(2, e());
        inventory.setItem(4, d(player.getName()));
        int n = 9;
        int n2 = 1;
        if (ConfigManager.getDataConfig().contains("Worlds")) {
            for (String string : ConfigManager.getDataConfig().getConfigurationSection("Worlds").getKeys(false)) {
                if (c.containsKey(player.getName())) {
                    if (n >= inventory.getSize()) break;
                    if (n2 > c.get(player.getName()) * 45) {
                        ItemStack itemStack = new ItemStack(/*MaterialManager.a(MaterialManager.a.PLAYER_HEAD)*/ Material.PLAYER_HEAD, WorldManager.a(Main.getPlugin().getConfig().getString("Basic.World-Prefix") + string, true), (short) 3);
                        SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
                        if (d.contains(player.getName())) {
                            skullMeta.setOwner(string);
                        }
                        String string2 = "Player-World";
                        if (WorldManager.b(player, string)) {
                            string2 = string2 + "-Own";
                        }
                        skullMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Main.Items." + string2 + ".Displayname").replace("&", "§").replace("%player%", string));
                        ArrayList<String> arrayList = new ArrayList<String>();
                        for (String string3 : Main.getPlugin().getConfig().getStringList("GUI.Main.Items." + string2 + ".Lore")) {
                            if (!WorldManager.b() && string3.contains("%expiration%")) continue;
                            string3 = string3.replace("&", "§");
                            string3 = string3.replace("%players%", String.valueOf(WorldManager.a(Main.getPlugin().getConfig().getString("Basic.World-Prefix") + string, false)));
                            string3 = string3.replace("%expiration%", WorldManager.r(string));
                            string3 = string3.replace("%access%", Main.getPlugin().getConfig().getString("Variables." + WorldManager.p(string)).replace("&", "§"));
                            arrayList.add(string3);
                        }
                        skullMeta.setLore(arrayList);
                        itemStack.setItemMeta((ItemMeta)skullMeta);
                        inventory.setItem(n, itemStack);
                        ++n;
                        continue;
                    }
                    ++n2;
                    continue;
                }
                a(player.getName(), true);
                return;
            }
        }
        d.add(player.getName());
        a(n, inventory);
    }

    public static void a(int n, Inventory inventory) {
        Bukkit.getScheduler().runTask(Main.getPlugin(), () -> {
            for (int i = n; i < inventory.getSize(); ++i) {
                inventory.setItem(i, new ItemStack(Material.AIR));
            }
        });
    }

    private static ItemStack b(String string) {
        if (c.get(string) > 0) {
            ItemStack itemStack = new ItemStack(Material.ARROW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Main.Items.Previous").replace("&", "§"));
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        return new ItemStack(Material.AIR);
    }

    private static ItemStack c(String string) {
        int n = 0;
        if (ConfigManager.getDataConfig().contains("Worlds")) {
            n = ConfigManager.getDataConfig().getConfigurationSection("Worlds").getKeys(false).size();
        }
        if (n > (c.get(string) + 1) * 45) {
            ItemStack itemStack = new ItemStack(Material.ARROW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Main.Items.Next").replace("&", "§"));
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        return new ItemStack(Material.AIR);
    }

    private static ItemStack e() {
        ItemStack itemStack = new ItemStack(Material.EMERALD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Main.Items.Statistics.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Main.Items.Statistics.Lore")) {
            string = string.replace("&", "§");
            string = string.replace("%total_player_worlds%", String.valueOf(WorldManager.d()));
            string = string.replace("%total_players%", String.valueOf(WorldManager.e()));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack d(String string) {
        ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.PLAYER_HEAD), WorldManager.a(Main.getPlugin().getConfig().getString("Basic.World-Prefix") + string, true), (short)SkullType.PLAYER.ordinal());
        SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
        skullMeta.setOwner(string);
        String string2 = "My-World";
        if (!WorldManager.c(string)) {
            string2 = string2 + "-Dont-Have";
        }
        skullMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Main.Items." + string2 + ".Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string3 : Main.getPlugin().getConfig().getStringList("GUI.Main.Items." + string2 + ".Lore")) {
            if (!WorldManager.b() && string3.contains("%expiration%")) continue;
            string3 = string3.replace("&", "§");
            string3 = string3.replace("%players%", String.valueOf(WorldManager.a(Main.getPlugin().getConfig().getString("Basic.World-Prefix") + string, false)));
            string3 = string3.replace("%expiration%", WorldManager.r(string));
            arrayList.add(string3);
        }
        skullMeta.setLore(arrayList);
        itemStack.setItemMeta((ItemMeta)skullMeta);
        return itemStack;
    }

    private static ItemStack b(Player player) {
        if (Main.getPlugin().getConfig().getBoolean("Permissions.Create-World") && !player.hasPermission("PlayerWorldsPro.createWorld") && Main.getPlugin().getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(MaterialManager.a(MaterialManager.a.COMMAND_BLOCK));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getPlugin().getConfig().getString("GUI.Main.Items.Create-World.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : Main.getPlugin().getConfig().getStringList("GUI.Main.Items.Create-World.Lore")) {
            string = string.replace("&", "§");
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @EventHandler
    public void a(InventoryClickEvent inventoryClickEvent) {
        Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(Main.getPlugin().getConfig().getString("GUI.Main.Title").replace("&", "§"))) {
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
        String string = inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName();
        if (inventoryClickEvent.getCurrentItem().getType() == Material.ARROW && Main.getPlugin().getConfig().getString("GUI.Main.Items.Previous").replace("&", "§").contains(string)) {
            c.put(player.getName(), c.get(player.getName()) - 1);
            b(player, player.getOpenInventory().getTopInventory());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.ARROW && Main.getPlugin().getConfig().getString("GUI.Main.Items.Next").replace("&", "§").contains(string)) {
            c.put(player.getName(), c.get(player.getName()) + 1);
            b(player, player.getOpenInventory().getTopInventory());
        } else if (inventoryClickEvent.getCurrentItem().getType() == MaterialManager.a(MaterialManager.a.COMMAND_BLOCK)) {
            GUI_CreateWorld.a(player);
        } else {
            String string2;
            if (inventoryClickEvent.getCurrentItem().getType() != MaterialManager.a(MaterialManager.a.PLAYER_HEAD)) return;
            boolean bl = WorldManager.c(player.getName());
            String string3 = "My-World";
            if (!bl) {
                string3 = string3 + "-Dont-Have";
            }
            if (string.equals(Main.getPlugin().getConfig().getString("GUI.Main.Items." + string3 + ".Displayname").replace("&", "§"))) {
                if (!bl) return;
                string2 = player.getName();
            } else {
                string3 = "GUI.Main.Items.Player-World.Displayname";
                string2 = this.a(string3, string);
                if (!WorldManager.c(string2)) {
                    string3 = "GUI.Main.Items.Player-World-Own.Displayname";
                }
                string2 = this.a(string3, string);
            }
            if (inventoryClickEvent.isRightClick() && WorldManager.b(player, string2)) {
                GUI_Settings.a(player, string2);
                return;
            }
            player.closeInventory();
            WorldManager.a(player, string2);
        }
    }

    public static String a(String string, String string2) {
        String string3;
        String string4 = Main.getPlugin().getConfig().getString(string).replace("&", "§");
        String[] arrstring = string4.split("%player%");
        if (arrstring.length > 0) {
            String string5 = arrstring[0];
            string3 = string2.replace(string5, "");
            if (arrstring.length == 2) {
                String string6 = arrstring[1];
                string3.replace(string6, "");
            }
        } else {
            string3 = string2;
        }
        return string3;
    }

    @EventHandler
    public void a(InventoryCloseEvent inventoryCloseEvent) {
        Player player = (Player)inventoryCloseEvent.getPlayer();
        if (inventoryCloseEvent.getView().getTitle().equals(Main.getPlugin().getConfig().getString("GUI.Main.Title").replace("&", "§"))) {
            a(player.getName(), true);
        }
    }

    public static void a(String string, boolean bl) {
        if (b.containsKey(string)) {
            b.get(string).cancel();
            d.remove(string);
        }
        if (bl) {
            b.remove(string);
            c.remove(string);
        }
    }

    public static HashMap<String, BukkitTask> b() {
        return b;
    }

    public static HashMap<String, Integer> c() {
        return c;
    }

    public static LinkedHashSet<String> d() {
        return d;
    }
}

