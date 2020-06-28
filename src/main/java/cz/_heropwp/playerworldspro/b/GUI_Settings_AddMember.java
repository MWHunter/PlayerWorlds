/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.SkullType
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
package cz._heropwp.playerworldspro.b;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.d.ConfigManager;
import cz._heropwp.playerworldspro.d.MaterialManager;
import java.util.ArrayList;
import java.util.List;
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

public class GUI_Settings_AddMember
implements Listener {
    private final Main a;

    public GUI_Settings_AddMember(Main main) {
        this.a = main;
    }

    public void a(Player player) {
        if (!this.a.k().b().containsKey(player.getName())) {
            player.closeInventory();
            return;
        }
        if (this.a.getConfig().getBoolean("Permissions.Access") && !player.hasPermission("PlayerWorldsPro.access")) {
            player.closeInventory();
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Access.Insufficient-Permission").replace("&", "§"));
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, (int)54, (String)this.a.getConfig().getString("GUI.Add-Member.Title").replace("&", "§"));
        player.openInventory(inventory);
        this.a.i().c().put(player.getName(), 0);
        this.a(player, inventory);
    }

    private void a(Player player, Inventory inventory) {
        this.a.i().b().put(player.getName(), Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin)this.a, () -> this.c(player, inventory), 0L, 10L));
    }

    private void b(Player player, Inventory inventory) {
        this.a.i().a(player.getName(), false);
        this.a.i().a(9, inventory);
        this.a(player, inventory);
    }

    private void c(Player player, Inventory inventory) {
        inventory.setItem(0, this.a(player.getName()));
        inventory.setItem(8, this.b(player.getName()));
        int n = 9;
        int n2 = 1;
        if (this.a.k().b().containsKey(player.getName()) && this.a.i().c().containsKey(player.getName())) {
            String string = this.a.k().b().get(player.getName());
            for (Player player2 : Bukkit.getOnlinePlayers()) {
                if (player2.getName().equals(string) || this.a.E().a(ConfigManager.dataOrPlayers.DATA).getStringList("Worlds." + string + ".Members").contains(player2.getName())) continue;
                if (n >= inventory.getSize()) break;
                if (this.a.i().c().containsKey(player.getName())) {
                    if (n2 > this.a.i().c().get(player.getName()) * 45) {
                        ItemStack itemStack = new ItemStack(this.a.F().a(MaterialManager.a.PLAYER_HEAD), 1, (short)SkullType.PLAYER.ordinal());
                        SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
                        if (this.a.i().d().contains(player.getName())) {
                            skullMeta.setOwner(player2.getName());
                        }
                        skullMeta.setDisplayName(this.a.getConfig().getString("GUI.Add-Member.Items.Player.Displayname").replace("&", "§").replace("%player%", player2.getName()));
                        ArrayList<String> arrayList = new ArrayList<String>();
                        for (String string2 : this.a.getConfig().getStringList("GUI.Add-Member.Items.Player.Lore")) {
                            string2 = string2.replace("&", "§");
                            arrayList.add(string2);
                        }
                        skullMeta.setLore(arrayList);
                        itemStack.setItemMeta((ItemMeta)skullMeta);
                        inventory.setItem(n, itemStack);
                        ++n;
                    }
                } else {
                    this.a.i().a(player.getName(), true);
                    return;
                }
                ++n2;
            }
        }
        this.a.i().d().add(player.getName());
        this.a.i().a(n, inventory);
    }

    private ItemStack a(String string) {
        if (this.a.i().c().get(string) > 0) {
            ItemStack itemStack = new ItemStack(Material.ARROW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Add-Member.Items.Previous").replace("&", "§"));
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        return new ItemStack(Material.AIR);
    }

    private ItemStack b(String string) {
        String string2;
        //ItemStack string2;
        int n = 0;
        if (this.a.k().b().containsKey(string)) {
            string2 = this.a.k().b().get(string);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getName().equals(string2) || this.a.E().a(ConfigManager.dataOrPlayers.DATA).getStringList("Worlds." + string2 + ".Members").contains(player.getName())) continue;
                ++n;
            }
        }
        if (n > (this.a.i().c().get(string) + 1) * 45) {
            ItemStack stringStack = new ItemStack(Material.ARROW);
            ItemMeta itemMeta = stringStack.getItemMeta();
            itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Add-Member.Items.Next").replace("&", "§"));
            stringStack.setItemMeta(itemMeta);
            return stringStack;
        }
        return new ItemStack(Material.AIR);
    }

    @EventHandler
    public void a(InventoryClickEvent inventoryClickEvent) {
        Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.Add-Member.Title").replace("&", "§"))) {
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
        if (!this.a.k().b().containsKey(player.getName())) {
            player.closeInventory();
            return;
        }
        String string = inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName();
        if (inventoryClickEvent.getCurrentItem().getType() == Material.ARROW && this.a.getConfig().getString("GUI.Add-Member.Items.Previous").replace("&", "§").contains(string)) {
            this.a.i().c().put(player.getName(), this.a.i().c().get(player.getName()) - 1);
            this.b(player, player.getOpenInventory().getTopInventory());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.ARROW && this.a.getConfig().getString("GUI.Add-Member.Items.Next").replace("&", "§").contains(string)) {
            this.a.i().c().put(player.getName(), this.a.i().c().get(player.getName()) + 1);
            this.b(player, player.getOpenInventory().getTopInventory());
        } else if (inventoryClickEvent.getCurrentItem().getType() == this.a.F().a(MaterialManager.a.PLAYER_HEAD)) {
            Player player2 = Bukkit.getPlayer((String)this.a.i().a("GUI.Add-Member.Items.Player.Displayname", string));
            String string2 = this.a.k().b().get(player.getName());
            if (player2 == null || !player2.isOnline()) {
                player.closeInventory();
                return;
            }
            List<String> list = this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds." + string2 + ".Members") ? this.a.E().a(ConfigManager.dataOrPlayers.DATA).getStringList("Worlds." + string2 + ".Members") : new ArrayList<String>();
            list.add(player2.getName());
            this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string2 + ".Members", list);
            this.a.E().b(ConfigManager.dataOrPlayers.DATA);
            this.a.E().c(ConfigManager.dataOrPlayers.DATA);
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Access.Add-Member").replace("&", "§").replace("%player%", player2.getName()));
            player.closeInventory();
            this.a.k().b().remove(player.getName());
        }
    }

    @EventHandler
    public void a(InventoryCloseEvent inventoryCloseEvent) {
        Player player = (Player)inventoryCloseEvent.getPlayer();
        if (inventoryCloseEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.Add-Member.Title").replace("&", "§"))) {
            this.a.i().a(player.getName(), true);
        }
    }
}

