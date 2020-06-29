/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.SkullType
 *  org.bukkit.World
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
import cz._heropwp.playerworldspro.CoreManagers.MaterialManager;
import java.util.ArrayList;

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

public class GUI_Settings_KickPlayer
implements Listener {
    private final Main a;

    public GUI_Settings_KickPlayer(Main main) {
        this.a = main;
    }

    public void a(Player player) {
        if (this.a.getConfig().getBoolean("Permissions.Kick") && !player.hasPermission("PlayerWorldsPro.kick")) {
            player.closeInventory();
            player.sendMessage(this.a.D().getPluginPrefix() + this.a.getConfig().getString("Messages.Insufficient-Permission").replace("&", "§"));
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, (int)54, (String)this.a.getConfig().getString("GUI.Kick-Player.Title").replace("&", "§"));
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
            String string = this.a.getConfig().getString("Basic.World-Prefix");
            String string2 = this.a.k().b().get(player.getName());
            for (Player player2 : Bukkit.getOnlinePlayers()) {
                if (player2.getName().equals(player.getName()) || player2.getName().equals(string2) || !player2.getWorld().getName().equals(string + string2) || player2.hasPermission("PlayerWorldsPro.bypass.kick")) continue;
                if (n >= inventory.getSize()) break;
                if (this.a.i().c().containsKey(player.getName())) {
                    if (n2 > this.a.i().c().get(player.getName()) * 45) {
                        ItemStack itemStack = new ItemStack(this.a.F().a(MaterialManager.a.PLAYER_HEAD), 1, (short)SkullType.PLAYER.ordinal());
                        SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
                        if (this.a.i().d().contains(player.getName())) {
                            skullMeta.setOwner(player2.getName());
                        }
                        skullMeta.setDisplayName(this.a.getConfig().getString("GUI.Kick-Player.Items.Player.Displayname").replace("&", "§").replace("%player%", player2.getName()));
                        ArrayList<String> arrayList = new ArrayList<String>();
                        for (String string3 : this.a.getConfig().getStringList("GUI.Kick-Player.Items.Player.Lore")) {
                            string3 = string3.replace("&", "§");
                            arrayList.add(string3);
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
            itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Kick-Player.Items.Previous").replace("&", "§"));
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        return new ItemStack(Material.AIR);
    }

    private ItemStack b(String string) {
        int n = 0;
        String string2 = this.a.getConfig().getString("Basic.World-Prefix");
        String string3 = this.a.k().b().get(string);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equals(string) || player.getName().equals(string3) || !player.getWorld().getName().equals(string2 + string3) || player.hasPermission("PlayerWorldsPro.bypass.kick")) continue;
            ++n;
        }
        if (n > (this.a.i().c().get(string) + 1) * 45) {
            //Player player;
            ItemStack itemStack = new ItemStack(Material.ARROW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Kick-Player.Items.Next").replace("&", "§"));
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        return new ItemStack(Material.AIR);
    }

    @EventHandler
    public void a(InventoryClickEvent inventoryClickEvent) {
        Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.Kick-Player.Title").replace("&", "§"))) {
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
        if (inventoryClickEvent.getCurrentItem().getType() == Material.ARROW && this.a.getConfig().getString("GUI.Kick-Player.Items.Previous").replace("&", "§").contains(string)) {
            this.a.i().c().put(player.getName(), this.a.i().c().get(player.getName()) - 1);
            this.b(player, player.getOpenInventory().getTopInventory());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.ARROW && this.a.getConfig().getString("GUI.Kick-Player.Items.Next").replace("&", "§").contains(string)) {
            this.a.i().c().put(player.getName(), this.a.i().c().get(player.getName()) + 1);
            this.b(player, player.getOpenInventory().getTopInventory());
        } else if (inventoryClickEvent.getCurrentItem().getType() == this.a.F().a(MaterialManager.a.PLAYER_HEAD)) {
            Player player2 = Bukkit.getPlayer((String)this.a.i().a("GUI.Kick-Player.Items.Player.Displayname", string));
            if (player2 == null || !player2.isOnline()) {
                player.closeInventory();
                return;
            }
            if (this.a.D().b()) {
                this.a.D().a(player2, this.a.D().c());
                player2.sendMessage(this.a.D().getPluginPrefix() + this.a.getConfig().getString("Messages.Kicked").replace("&", "§"));
            } else {
                player2.kickPlayer(this.a.D().getPluginPrefix() + this.a.getConfig().getString("Messages.Lobby-Is-Not-Configured").replace("&", "§"));
            }
            String string2 = this.a.getConfig().getString("Basic.World-Prefix") + this.a.k().b().get(player.getName());
            for (Player player3 : Bukkit.getOnlinePlayers()) {
                if (!player3.getWorld().getName().equals(string2)) continue;
                String string3 = this.a.getConfig().getString("Messages.Broadcast.Kick");
                string3 = string3.replace("&", "§");
                string3 = string3.replace("%player%", player2.getName());
                string3 = string3.replace("%executor%", player.getName());
                player3.sendMessage(this.a.D().getPluginPrefix() + string3);
            }
            player.sendMessage(this.a.D().getPluginPrefix() + this.a.getConfig().getString("Messages.Kick-Player").replace("&", "§").replace("%player%", player2.getName()));
            player.closeInventory();
            this.a.k().b().remove(player.getName());
        }
    }

    @EventHandler
    public void a(InventoryCloseEvent inventoryCloseEvent) {
        Player player = (Player)inventoryCloseEvent.getPlayer();
        if (inventoryCloseEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.Kick-Player.Title").replace("&", "§"))) {
            this.a.i().a(player.getName(), true);
        }
    }
}

