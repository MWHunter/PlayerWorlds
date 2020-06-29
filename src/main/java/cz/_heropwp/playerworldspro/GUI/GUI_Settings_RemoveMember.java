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
package cz._heropwp.playerworldspro.GUI;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.CoreManagers.ConfigManager;
import cz._heropwp.playerworldspro.CoreManagers.MaterialManager;
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

public class GUI_Settings_RemoveMember
implements Listener {
    private final Main a;

    public GUI_Settings_RemoveMember(Main main) {
        this.a = main;
    }

    public void a(Player player) {
        if (!this.a.k().b().containsKey(player.getName())) {
            player.closeInventory();
            return;
        }
        if (this.a.getConfig().getBoolean("Permissions.Access") && !player.hasPermission("PlayerWorldsPro.access")) {
            player.closeInventory();
            player.sendMessage(this.a.D().getPluginPrefix() + this.a.getConfig().getString("Messages.Access.Insufficient-Permission").replace("&", "§"));
            return;
        }
        Inventory inventory = Bukkit.createInventory(null, (int)54, (String)this.a.getConfig().getString("GUI.Remove-Member.Title").replace("&", "§"));
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
            if (ConfigManager.getDataConfig().contains("Worlds." + string + ".Members")) {
                for (String string2 : ConfigManager.getDataConfig().getStringList("Worlds." + string + ".Members")) {
                    if (n >= inventory.getSize()) break;
                    if (this.a.i().c().containsKey(player.getName())) {
                        if (n2 > this.a.i().c().get(player.getName()) * 45) {
                            ItemStack itemStack = new ItemStack(this.a.F().a(MaterialManager.a.PLAYER_HEAD), 1, (short)SkullType.PLAYER.ordinal());
                            SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
                            if (this.a.i().d().contains(player.getName())) {
                                skullMeta.setOwner(string2);
                            }
                            skullMeta.setDisplayName(this.a.getConfig().getString("GUI.Remove-Member.Items.Player.Displayname").replace("&", "§").replace("%player%", string2));
                            ArrayList<String> arrayList = new ArrayList<String>();
                            for (String string3 : this.a.getConfig().getStringList("GUI.Remove-Member.Items.Player.Lore")) {
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
        }
        this.a.i().d().add(player.getName());
        this.a.i().a(n, inventory);
    }

    private ItemStack a(String string) {
        if (this.a.i().c().get(string) > 0) {
            ItemStack itemStack = new ItemStack(Material.ARROW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Remove-Member.Items.Previous").replace("&", "§"));
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        return new ItemStack(Material.AIR);
    }

    private ItemStack b(String string) {
        String string2;
        int n = 0;
        if (this.a.k().b().containsKey(string)) {
            string2 = this.a.k().b().get(string);
            if (ConfigManager.getDataConfig().contains("Worlds." + string2 + ".Members")) {
                n = ConfigManager.getDataConfig().getStringList("Worlds." + string2 + ".Members").size();
            }
        }
        if (n > (this.a.i().c().get(string) + 1) * 45) {
            ItemStack itemStack = new ItemStack(Material.ARROW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(this.a.getConfig().getString("GUI.Remove-Member.Items.Next").replace("&", "§"));
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        return new ItemStack(Material.AIR);
    }

    @EventHandler
    public void a(InventoryClickEvent inventoryClickEvent) {
        Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.Remove-Member.Title").replace("&", "§"))) {
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
        if (inventoryClickEvent.getCurrentItem().getType() == Material.ARROW && this.a.getConfig().getString("GUI.Remove-Member.Items.Previous").replace("&", "§").contains(string)) {
            this.a.i().c().put(player.getName(), this.a.i().c().get(player.getName()) - 1);
            this.b(player, player.getOpenInventory().getTopInventory());
        } else if (inventoryClickEvent.getCurrentItem().getType() == Material.ARROW && this.a.getConfig().getString("GUI.Remove-Member.Items.Next").replace("&", "§").contains(string)) {
            this.a.i().c().put(player.getName(), this.a.i().c().get(player.getName()) + 1);
            this.b(player, player.getOpenInventory().getTopInventory());
        } else if (inventoryClickEvent.getCurrentItem().getType() == this.a.F().a(MaterialManager.a.PLAYER_HEAD)) {
            String string2 = this.a.i().a("GUI.Remove-Member.Items.Player.Displayname", string);
            String string3 = this.a.k().b().get(player.getName());
            List list = ConfigManager.getDataConfig().getStringList("Worlds." + string3 + ".Members");
            list.remove(string2);
            ConfigManager.getDataConfig().set("Worlds." + string3 + ".Members", (Object)list);
            ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
            ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
            player.sendMessage(this.a.D().getPluginPrefix() + this.a.getConfig().getString("Messages.Access.Remove-Member").replace("&", "§").replace("%player%", string2));
            player.closeInventory();
            this.a.k().b().remove(player.getName());
        }
    }

    @EventHandler
    public void a(InventoryCloseEvent inventoryCloseEvent) {
        Player player = (Player)inventoryCloseEvent.getPlayer();
        if (inventoryCloseEvent.getView().getTitle().equals(this.a.getConfig().getString("GUI.Remove-Member.Title").replace("&", "§"))) {
            this.a.i().a(player.getName(), true);
        }
    }
}

