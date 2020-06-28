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
    private final Main a;
    private final HashMap<String, BukkitTask> b;
    private final HashMap<String, Integer> c;
    private final LinkedHashSet<String> d;

    public GUI_Main(Main main) {
        this.a = main;
        this.b = new HashMap();
        this.c = new HashMap();
        this.d = new LinkedHashSet();
    }

    public void a(Player player) {
        Inventory inventory = Bukkit.createInventory(null, (int)54, (String)this.a.getConfig().getString("GUI.Main.Title").replace("&", "§"));
        player.openInventory(inventory);
        this.c.put(player.getName(), 0);
        inventory.setItem(6, this.b(player));
        this.a(player, inventory);
    }

    private void a(Player player, Inventory inventory) {
        this.b.put(player.getName(), Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin)this.a, () -> this.c(player, inventory), 0L, 10L));
    }

    private void b(Player player, Inventory inventory) {
        this.a(player.getName(), false);
        this.a(9, inventory);
        this.a(player, inventory);
    }

    private ItemStack a(String string) {
        //ItemStack itemStack = new ItemStack(this.a.F().a(MaterialManager.a.PLAYER_HEAD), 1, 3);
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        UUID uUID = new UUID(string.hashCode(), string.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(itemStack, "{SkullOwner:{Id:\"" + uUID + "\",Properties:{textures:[{Value:\"" + string + "\"}]}}}");
    }

    private void c(Player player, Inventory inventory) {
        inventory.setItem(0, this.b(player.getName()));
        inventory.setItem(8, this.c(player.getName()));
        inventory.setItem(2, this.e());
        inventory.setItem(4, this.d(player.getName()));
        int n = 9;
        int n2 = 1;
        if (this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds")) {
            for (String string : this.a.E().a(ConfigManager.dataOrPlayers.DATA).getConfigurationSection("Worlds").getKeys(false)) {
                if (this.c.containsKey(player.getName())) {
                    if (n >= inventory.getSize()) break;
                    if (n2 > this.c.get(player.getName()) * 45) {
                        ItemStack itemStack = new ItemStack(/*this.a.F().a(MaterialManager.a.PLAYER_HEAD)*/ Material.PLAYER_HEAD, this.a.G().a(this.a.getConfig().getString("Basic.World-Prefix") + string, true), (short) 3);
                        SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
                        if (this.d.contains(player.getName())) {
                            skullMeta.setOwner(string);
                        }
                        String string2 = "Player-World";
                        if (this.a.G().b(player, string)) {
                            string2 = string2 + "-Own";
                        }
                        skullMeta.setDisplayName(this.a.getConfig().getString("GUI.Main.Items." + string2 + ".Displayname").replace("&", "§").replace("%player%", string));
                        ArrayList<String> arrayList = new ArrayList<String>();
                        for (String string3 : this.a.getConfig().getStringList("GUI.Main.Items." + string2 + ".Lore")) {
                            if (!this.a.G().b() && string3.contains("%expiration%")) continue;
                            string3 = string3.replace("&", "§");
                            string3 = string3.replace("%players%", String.valueOf(this.a.G().a(this.a.getConfig().getString("Basic.World-Prefix") + string, false)));
                            string3 = string3.replace("%expiration%", this.a.G().r(string));
                            string3 = string3.replace("%access%", this.a.getConfig().getString("Variables." + this.a.G().p(string)).replace("&", "§"));
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
                this.a(player.getName(), true);
                return;
            }
        }
        this.d.add(player.getName());
        this.a(n, inventory);
    }

    public void a(int n, Inventory inventory) {
        Bukkit.getScheduler().runTask((Plugin)this.a, () -> {
            for (int i = n; i < inventory.getSize(); ++i) {
                inventory.setItem(i, new ItemStack(Material.AIR));
            }
        });
    }

    private ItemStack b(String string) {
        if (this.c.get(string) > 0) {
            ItemStack itemStack = new ItemStack(Material.ARROW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Main.Items.Previous").replace("&", "§"));
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        return new ItemStack(Material.AIR);
    }

    private ItemStack c(String string) {
        int n = 0;
        if (this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds")) {
            n = this.a.E().a(ConfigManager.dataOrPlayers.DATA).getConfigurationSection("Worlds").getKeys(false).size();
        }
        if (n > (this.c.get(string) + 1) * 45) {
            ItemStack itemStack = new ItemStack(Material.ARROW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Main.Items.Next").replace("&", "§"));
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        return new ItemStack(Material.AIR);
    }

    private ItemStack e() {
        ItemStack itemStack = new ItemStack(Material.EMERALD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Main.Items.Statistics.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Main.Items.Statistics.Lore")) {
            string = string.replace("&", "§");
            string = string.replace("%total_player_worlds%", String.valueOf(this.a.G().d()));
            string = string.replace("%total_players%", String.valueOf(this.a.G().e()));
            arrayList.add(string);
        }
        itemMeta.setLore(arrayList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack d(String string) {
        ItemStack itemStack = new ItemStack(this.a.F().a(MaterialManager.a.PLAYER_HEAD), this.a.G().a(this.a.getConfig().getString("Basic.World-Prefix") + string, true), (short)SkullType.PLAYER.ordinal());
        SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
        skullMeta.setOwner(string);
        String string2 = "My-World";
        if (!this.a.G().c(string)) {
            string2 = string2 + "-Dont-Have";
        }
        skullMeta.setDisplayName(this.a.getConfig().getString("GUI.Main.Items." + string2 + ".Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string3 : this.a.getConfig().getStringList("GUI.Main.Items." + string2 + ".Lore")) {
            if (!this.a.G().b() && string3.contains("%expiration%")) continue;
            string3 = string3.replace("&", "§");
            string3 = string3.replace("%players%", String.valueOf(this.a.G().a(this.a.getConfig().getString("Basic.World-Prefix") + string, false)));
            string3 = string3.replace("%expiration%", this.a.G().r(string));
            arrayList.add(string3);
        }
        skullMeta.setLore(arrayList);
        itemStack.setItemMeta((ItemMeta)skullMeta);
        return itemStack;
    }

    private ItemStack b(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Create-World") && !player.hasPermission("PlayerWorldsPro.createWorld") && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(this.a.F().a(MaterialManager.a.COMMAND_BLOCK));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Main.Items.Create-World.Displayname").replace("&", "§"));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : this.a.getConfig().getStringList("GUI.Main.Items.Create-World.Lore")) {
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
        if (!inventoryClickEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.Main.Title").replace("&", "§"))) {
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
        String string = inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName();
        if (inventoryClickEvent.getCurrentItem().getType() == Material.ARROW && this.a.getConfig().getString("GUI.Main.Items.Previous").replace("&", "§").contains(string)) {
            this.c.put(player.getName(), this.c.get(player.getName()) - 1);
            this.b(player, player.getOpenInventory().getTopInventory());
            return;
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.ARROW && this.a.getConfig().getString("GUI.Main.Items.Next").replace("&", "§").contains(string)) {
            this.c.put(player.getName(), this.c.get(player.getName()) + 1);
            this.b(player, player.getOpenInventory().getTopInventory());
            return;
        } else if (inventoryClickEvent.getCurrentItem().getType() == this.a.F().a(MaterialManager.a.COMMAND_BLOCK)) {
            this.a.f().a(player);
            return;
        } else {
            String string2;
            if (inventoryClickEvent.getCurrentItem().getType() != this.a.F().a(MaterialManager.a.PLAYER_HEAD)) return;
            boolean bl = this.a.G().c(player.getName());
            String string3 = "My-World";
            if (!bl) {
                string3 = string3 + "-Dont-Have";
            }
            if (string.equals(this.a.getConfig().getString("GUI.Main.Items." + string3 + ".Displayname").replace("&", "§"))) {
                if (!bl) return;
                string2 = player.getName();
            } else {
                string3 = "GUI.Main.Items.Player-World.Displayname";
                string2 = this.a(string3, string);
                if (!this.a.G().c(string2)) {
                    string3 = "GUI.Main.Items.Player-World-Own.Displayname";
                }
                string2 = this.a(string3, string);
            }
            if (inventoryClickEvent.isRightClick() && this.a.G().b(player, string2)) {
                this.a.k().a(player, string2);
                return;
            }
            player.closeInventory();
            this.a.G().a(player, string2);
        }
    }

    public String a(String string, String string2) {
        String string3;
        String string4 = this.a.getConfig().getString(string).replace("&", "§");
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
        if (inventoryCloseEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.Main.Title").replace("&", "§"))) {
            this.a(player.getName(), true);
        }
    }

    public void a(String string, boolean bl) {
        if (this.b.containsKey(string)) {
            this.b.get(string).cancel();
            this.d.remove(string);
        }
        if (bl) {
            this.b.remove(string);
            this.c.remove(string);
        }
    }

    public Main a() {
        return this.a;
    }

    public HashMap<String, BukkitTask> b() {
        return this.b;
    }

    public HashMap<String, Integer> c() {
        return this.c;
    }

    public LinkedHashSet<String> d() {
        return this.d;
    }
}

