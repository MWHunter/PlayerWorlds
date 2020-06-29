/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  com.boydti.fawe.bukkit.wrapper.AsyncWorld
 *  com.boydti.fawe.util.TaskManager
 *  com.sk89q.worldedit.function.operation.Operation
 *  net.milkbowl.vault.economy.EconomyResponse
 *  org.bukkit.Bukkit
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.World
 *  org.bukkit.WorldBorder
 *  org.bukkit.WorldCreator
 *  org.bukkit.WorldType
 *  org.bukkit.block.Block
 *  org.bukkit.command.CommandSender
 *  org.bukkit.configuration.ConfigurationSection
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.generator.ChunkGenerator
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitTask
 */
package cz._heropwp.playerworldspro.CoreManagers;

import com.boydti.fawe.bukkit.wrapper.AsyncWorld;
import com.boydti.fawe.util.TaskManager;
import cz._heropwp.playerworldspro.GUI.GUI_Settings;
import cz._heropwp.playerworldspro.Main;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class WorldManager {
    private static final HashMap<String, Long> b = new HashMap<>();

    public static int a(String string, boolean bl) {
        int n = Bukkit.getWorld(string) != null ? Bukkit.getWorld(string).getPlayers().size() : 0;
        if (bl && n < 1) {
            return 1;
        }
        return n;
    }

    public static void a() {
        if (ConfigManager.getDataConfig().contains("Worlds") && Main.getPlugin().getConfig().getBoolean("Basic.Load-On-Startup")) {
            Bukkit.getConsoleSender().sendMessage(BasicManager.f() + "§eLoading player worlds...");
            for (String string : ConfigManager.getDataConfig().getConfigurationSection("Worlds").getKeys(false)) {
                c(string, false);
                Bukkit.getConsoleSender().sendMessage(BasicManager.f() + "§aPlayer world of player " + string + " has been successfully loaded!");
            }
        }
    }

    private static void c(String string, boolean bl) {
        Object object;
        String string2 = Main.getPlugin().getConfig().getString("Basic.World-Prefix") + string;
        String a2 = ConfigManager.getDataConfig().getString("Worlds." + string + ".Type");
        WorldCreator worldCreator = null;
        switch (a2) {
            case "NORMAL": {
                worldCreator = new WorldCreator(string2).type(WorldType.NORMAL).generateStructures(true);
                break;
            }
            case "FLAT": {
                worldCreator = new WorldCreator(string2).type(WorldType.FLAT).generateStructures(false);
                break;
            }
            case "EMPTY": {
                worldCreator = new WorldCreator(string2).generator(new EmptyChunkGenerator());
                break;
            }
            default: {
                if (!ConfigManager.getDataConfig().contains("Worlds." + string + ".Generator")) break;
                object = ConfigManager.getDataConfig().getString("Worlds." + string + ".Generator");
                worldCreator = new WorldCreator(string2).generator((String)object);
            }
        }
        if (worldCreator != null) {
            if (Main.H()) {
                WorldCreator finalObject = worldCreator;
                TaskManager.IMP.async(() -> WorldManager.a(finalObject));
            } else {
                Bukkit.createWorld(worldCreator); // TODO: Make this async
            }
        }
        if (bl) {
            World world = Bukkit.getWorld(string2);
            world.getBlockAt(world.getSpawnLocation()).setType(Material.BEDROCK);
            //object.getBlockAt(object.getSpawnLocation()).setType(Material.BEDROCK);
        }
    }

    public static void a(Player player, a a2, String string, String string2, Integer n, Integer n2, boolean bl) {
        File file;
        boolean bl2;
        long cooldown;
        File file2;
        long l;
        player.closeInventory();
        if (c(player.getName())) {
            player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Already-Have").replace("&", "§"));
            return;
        }
        if (n2 != null) {
            if (Main.b().getBalance((OfflinePlayer)player) < (double)n2.intValue()) {
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Not-Enough-Money").replace("&", "§"));
                return;
            }
            Main.b().withdrawPlayer((OfflinePlayer)player, (double)n2.intValue());
        }
        if (Main.getPlugin().getConfig().getBoolean("Cooldown.Enabled") && b.containsKey(player.getName())) {
            l = System.currentTimeMillis() - b.get(player.getName());
            cooldown = Main.getPlugin().getConfig().getInt("Cooldown.Interval");
            if (TimeUnit.MILLISECONDS.toSeconds(l) < cooldown) {
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Create-Cooldown").replace("&", "§").replace("%time%", String.valueOf((long)cooldown - TimeUnit.MILLISECONDS.toSeconds(l))));
                return;
            }
        }
        b.put(player.getName(), System.currentTimeMillis());
        if (n != null) {
            l = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(n.intValue());
            ConfigManager.getDataConfig().set("Worlds." + player.getName() + ".Expiration", (Object)l);
        }
        if (bl) {
            ConfigManager.getPlayersConfig().set("Claim." + player.getName(), (Object)"");
            ConfigManager.saveConfig(ConfigManager.dataOrPlayers.PLAYERS);
            ConfigManager.saveFile(ConfigManager.dataOrPlayers.PLAYERS);
        }
        ConfigManager.getDataConfig().set("Worlds." + player.getName() + ".Type", a2.toString());
        if (string2 != null) {
            ConfigManager.getDataConfig().set("Worlds." + player.getName() + ".Generator", string2);
        }
        ConfigManager.getDataConfig().set("Worlds." + player.getName() + ".WeatherCycle", Main.getPlugin().getConfig().getBoolean("Default-Settings.WeatherCycle"));
        ConfigManager.getDataConfig().set("Worlds." + player.getName() + ".Difficulty", Main.getPlugin().getConfig().getString("Default-Settings.Difficulty"));
        ConfigManager.getDataConfig().set("Worlds." + player.getName() + ".Block-Breaking", Main.getPlugin().getConfig().getBoolean("Default-Settings.Block-Breaking"));
        ConfigManager.getDataConfig().set("Worlds." + player.getName() + ".Block-Placing", Main.getPlugin().getConfig().getBoolean("Default-Settings.Block-Placing"));
        ConfigManager.getDataConfig().set("Worlds." + player.getName() + ".PvP", Main.getPlugin().getConfig().getBoolean("Default-Settings.PvP"));
        ConfigManager.getDataConfig().set("Worlds." + player.getName() + ".Pickup", Main.getPlugin().getConfig().getBoolean("Default-Settings.Pickup"));
        ConfigManager.getDataConfig().set("Worlds." + player.getName() + ".Drop", Main.getPlugin().getConfig().getBoolean("Default-Settings.Drop"));
        ConfigManager.getDataConfig().set("Worlds." + player.getName() + ".Damage", Main.getPlugin().getConfig().getBoolean("Default-Settings.Damage"));
        ConfigManager.getDataConfig().set("Worlds." + player.getName() + ".Hunger", Main.getPlugin().getConfig().getBoolean("Default-Settings.Hunger"));
        ConfigManager.getDataConfig().set("Worlds." + player.getName() + ".Bucket", Main.getPlugin().getConfig().getBoolean("Default-Settings.Hunger"));
        ConfigManager.getDataConfig().set("Worlds." + player.getName() + ".GameMode", Main.getPlugin().getConfig().getString("Default-Settings.GameMode"));
        ConfigManager.getDataConfig().set("Worlds." + player.getName() + ".Access", Main.getPlugin().getConfig().getString("Default-Settings.Access"));
        ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
        ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
        String string3 = Main.getPlugin().getConfig().getString("Basic.World-Prefix") + player.getName();
        if (Bukkit.getWorld(string3) != null) {
            b(string3);
            Bukkit.unloadWorld(string3, true);
        }
        if ((file2 = new File(Bukkit.getWorldContainer(), string3)).exists()) {
            try {
                FileUtils.deleteDirectory(file2);
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
        if (string != null && (file = new File(Main.getPlugin().getDataFolder() + "/maps/" + string)).exists() && file.isDirectory()) {
            for (File file3 : file.listFiles()) {
                if (!file3.getName().equals("uid.dat") && !file3.getName().equals("session.dat") && !file3.getName().equals("session.lock")) continue;
                file3.delete();
            }
            try {
                FileUtils.copyDirectory(file, file2);
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
        player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Creating-World").replace("&", "§"));
        //bl2 = a2 == a.EMPTY && string == null;
        //c(player.getName());
        player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.World-Created").replace("&", "§"));
        GUI_Settings.b().remove(player.getName());
        Bukkit.getScheduler().runTaskLater(Main.getPlugin(), () -> {
            World world;
            int m;
            if (Main.getPlugin().getConfig().getBoolean("Basic.Teleport-On-Create")) {
                a(player, player.getName());
            }
            if ((world = Bukkit.getWorld(string3)) != null && (m = Main.getPlugin().getConfig().getInt("Default-Settings.World-Border")) > 0) {
                world.getWorldBorder().setSize(m);
            }
            if (Main.getPlugin().getConfig().getBoolean("Commands.Create.Enabled")) {
                for (String string4 : Main.getPlugin().getConfig().getStringList("Commands.Create.List")) {
                    string4 = string4.replace("%owner%", player.getName());
                    string4 = string4.replace("%world%", string3);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), string4);
                }
            }
        }, 20L);
    }

    public static void a(CommandSender commandSender, String string, String string2, boolean bl) {
        if (!b()) {
            commandSender.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Expiration-Disabled").replace("&", "§"));
            return;
        }
        if (!c(string)) {
            commandSender.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.World-NotFound").replace("&", "§").replace("%player%", string));
            return;
        }
        Integer n = null;
        if (string2 != null) {
            if (commandSender instanceof Player && bl) {
                Player player = (Player)commandSender;
                int n2 = Main.getPlugin().getConfig().getInt("GUI.Extend-Player-World.Items." + string2 + ".Price");
                if (Main.b().getBalance(player) < (double)n2) {
                    commandSender.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Not-Enough-Money").replace("&", "§"));
                    return;
                }
                Main.b().withdrawPlayer(player, n2);
                n = Main.getPlugin().getConfig().getInt("GUI.Extend-Player-World.Items." + string2 + ".Length");
            } else {
                n = Integer.valueOf(string2);
            }
        }
        if (n != null) {
            long l = q(string) + TimeUnit.DAYS.toMillis(n.intValue());
            ConfigManager.getDataConfig().set("Worlds." + string + ".Expiration", (Object)l);
            ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
            ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
            commandSender.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Successfully-Extended").replace("&", "§").replace("%length%", String.valueOf(n)));
        }
    }

    public static void a(CommandSender commandSender, String string) {
        if (c(string)) {
            String string2 = Main.getPlugin().getConfig().getString("Basic.World-Prefix") + string;
            if (commandSender instanceof Player) {
                GUI_Settings.b().remove(commandSender.getName());
            }
            if (Bukkit.getWorld(string2) != null) {
                b(string2);
            }
            ConfigManager.getDataConfig().set("Worlds." + string, null);
            ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
            ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
            Bukkit.getScheduler().runTaskLater(Main.getPlugin(), () -> {
                if (Bukkit.getWorld(string2) != null && !Bukkit.unloadWorld(string2, true)) {
                    if (commandSender != null) {
                        commandSender.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.World-Delete-Error").replace("&", "§"));
                    }
                    return;
                }
                File file = new File(Bukkit.getWorldContainer(), string2);
                if (file.exists()) {
                    try {
                        FileUtils.deleteDirectory(file);
                    }
                    catch (IOException iOException) {
                        if (commandSender != null) {
                            commandSender.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.World-Delete-Error").replace("&", "§"));
                        }
                        iOException.printStackTrace();
                        return;
                    }
                }
                if (commandSender != null) {
                    commandSender.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.World-Deleted").replace("&", "§"));
                }
                if (Main.getPlugin().getConfig().getBoolean("Commands.Delete.Enabled")) {
                    for (String string3 : Main.getPlugin().getConfig().getStringList("Commands.Delete.List")) {
                        string3 = string3.replace("%owner%", string);
                        string3 = string3.replace("%world%", string2);
                        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)string3);
                    }
                }
            }, 3L);
        } else if (commandSender != null) {
            commandSender.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.World-NotFound").replace("&", "§").replace("%player%", string));
        }
    }

    public static void a(Player player, String string) {
        String string2 = Main.getPlugin().getConfig().getString("Basic.World-Prefix") + string;
        if (!c(string)) {
            player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Doesnt-Have").replace("&", "§").replace("%player%", string));
            return;
        }
        if (d(player, string)) {
            player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Banned-Teleport").replace("&", "§"));
            return;
        }
        if (p(string).equals("PRIVATE") && !c(player, string)) {
            player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Access.Only-For-Members").replace("&", "§"));
            return;
        }
        if (Bukkit.getWorld(string2) == null) {
            c(string, false);
            Bukkit.getScheduler().runTaskLater(Main.getPlugin(), () -> a(player, Bukkit.getWorld(string2), string), 20L);
        } else {
            a(player, Bukkit.getWorld(string2), string);
        }
    }

    private static void a(Player player, World world, String string) {
        if (world != null) {
            BasicManager.a(player, a(string));
            player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Teleported").replace("&", "§"));
            player.setGameMode(GameMode.valueOf(j(string)));
        } else {
            player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Unloaded-World").replace("&", "§"));
        }
    }

    public static void a(Player player, String string, boolean bl) {
        String string2 = Main.getPlugin().getConfig().getString("Basic.World-Prefix") + string;
        if (string2.equals(player.getWorld().getName())) {
            double d2 = player.getLocation().getX();
            double d3 = player.getLocation().getY();
            double d4 = player.getLocation().getZ();
            float f2 = player.getLocation().getYaw();
            float f3 = player.getLocation().getPitch();
            ConfigManager.getDataConfig().set("Worlds." + string + ".Spawn", (Object)(d2 + ";" + d3 + ";" + d4 + ";" + f2 + ";" + f3));
            ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
            ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
            if (bl) {
                player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Spawn-Setup").replace("&", "§"));
            }
        } else if (bl) {
            player.sendMessage(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Same-World").replace("&", "§"));
        }
    }

    public static Location a(String string) {
        String string2;
        World world;
        if (c(string) && (world = Bukkit.getWorld((String)((string2 = Main.getPlugin().getConfig().getString("Basic.World-Prefix")) + string))) != null) {
            if (ConfigManager.getDataConfig().contains("Worlds." + string + ".Spawn")) {
                String string3 = ConfigManager.getDataConfig().getString("Worlds." + string + ".Spawn");
                String[] arrstring = string3.split(";");
                double d2 = Double.parseDouble(arrstring[0]);
                double d3 = Double.parseDouble(arrstring[1]);
                double d4 = Double.parseDouble(arrstring[2]);
                float f2 = Float.parseFloat(arrstring[3]);
                float f3 = Float.parseFloat(arrstring[4]);
                return new Location(world, d2, d3, d4, f2, f3);
            }
            return world.getHighestBlockAt(world.getSpawnLocation()).getLocation();
        }
        return null;
    }

    public static void b(String string) {
        if (Bukkit.getWorld(string) != null) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!player.getWorld().getName().equals(string)) continue;
                if (BasicManager.b()) {
                    BasicManager.a(player, BasicManager.c());
                    continue;
                }
                player.kickPlayer(BasicManager.getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Lobby-Is-Not-Configured").replace("&", "§"));
            }
        }
    }

    public static boolean c(String string) {
        return ConfigManager.getDataConfig().contains("Worlds." + string);
    }

    public static String d(String string) {
        String string2;
        String string3 = Main.getPlugin().getConfig().getString("Basic.World-Prefix");
        if (string.startsWith(string3) && c(string2 = string.split(string3)[1])) {
            return string2;
        }
        return null;
    }

    public static boolean b(Player player, String string) {
        if (c(string)) {
            if (player.getName().equals(string)) {
                return true;
            }
            return player.hasPermission("PlayerWorldsPro.other");
        }
        return false;
    }

    public static boolean c(Player player, String string) {
        if (b(player, string)) {
            return true;
        }
        if (ConfigManager.getDataConfig().contains("Worlds." + string + ".Members")) {
            return ConfigManager.getDataConfig().getStringList("Worlds." + string + ".Members").contains(player.getName());
        }
        return false;
    }

    public static boolean b() {
        return Main.getPlugin().getConfig().getBoolean("Expiration.Enabled") && Main.b() != null;
    }

    public static boolean e(String string) {
        if (c(string) && ConfigManager.getDataConfig().contains("Worlds." + string + ".WeatherCycle")) {
            return ConfigManager.getDataConfig().getBoolean("Worlds." + string + ".WeatherCycle");
        }
        return true;
    }

    public static void b(String string, boolean bl) {
        if (c(string)) {
            ConfigManager.getDataConfig().set("Worlds." + string + ".WeatherCycle", (Object)bl);
            ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
            ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
        }
    }

    public static String f(String string) {
        if (c(string) && ConfigManager.getDataConfig().contains("Worlds." + string + ".Difficulty")) {
            return ConfigManager.getDataConfig().getString("Worlds." + string + ".Difficulty");
        }
        return "NORMAL";
    }

    public static boolean g(String string) {
        if (c(string) && ConfigManager.getDataConfig().contains("Worlds." + string + ".Block-Breaking")) {
            return ConfigManager.getDataConfig().getBoolean("Worlds." + string + ".Block-Breaking");
        }
        return true;
    }

    public static boolean h(String string) {
        if (c(string) && ConfigManager.getDataConfig().contains("Worlds." + string + ".Block-Placing")) {
            return ConfigManager.getDataConfig().getBoolean("Worlds." + string + ".Block-Placing");
        }
        return true;
    }

    public static boolean i(String string) {
        if (c(string) && ConfigManager.getDataConfig().contains("Worlds." + string + ".PvP")) {
            return ConfigManager.getDataConfig().getBoolean("Worlds." + string + ".PvP");
        }
        return true;
    }

    public static String j(String string) {
        if (c(string) && ConfigManager.getDataConfig().contains("Worlds." + string + ".GameMode")) {
            return ConfigManager.getDataConfig().getString("Worlds." + string + ".GameMode");
        }
        return "SURVIVAL";
    }

    public static boolean k(String string) {
        if (c(string) && ConfigManager.getDataConfig().contains("Worlds." + string + ".Pickup")) {
            return ConfigManager.getDataConfig().getBoolean("Worlds." + string + ".Pickup");
        }
        return true;
    }

    public static boolean l(String string) {
        if (c(string) && ConfigManager.getDataConfig().contains("Worlds." + string + ".Drop")) {
            return ConfigManager.getDataConfig().getBoolean("Worlds." + string + ".Drop");
        }
        return true;
    }

    public static boolean m(String string) {
        if (c(string) && ConfigManager.getDataConfig().contains("Worlds." + string + ".Damage")) {
            return ConfigManager.getDataConfig().getBoolean("Worlds." + string + ".Damage");
        }
        return true;
    }

    public static boolean n(String string) {
        if (c(string) && ConfigManager.getDataConfig().contains("Worlds." + string + ".Hunger")) {
            return ConfigManager.getDataConfig().getBoolean("Worlds." + string + ".Hunger");
        }
        return true;
    }

    public static boolean o(String string) {
        if (c(string) && ConfigManager.getDataConfig().contains("Worlds." + string + ".Bucket")) {
            return ConfigManager.getDataConfig().getBoolean("Worlds." + string + ".Bucket");
        }
        return true;
    }

    public static String p(String string) {
        if (c(string) && ConfigManager.getDataConfig().contains("Worlds." + string + ".Access")) {
            return ConfigManager.getDataConfig().getString("Worlds." + string + ".Access");
        }
        return "PUBLIC";
    }

    public static Long q(String string) {
        if (c(string) && ConfigManager.getDataConfig().contains("Worlds." + string + ".Expiration")) {
            return ConfigManager.getDataConfig().getLong("Worlds." + string + ".Expiration");
        }
        return 0L;
    }

    public static String r(String string) {
        Long l = q(string);
        if (l > 0L) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Main.getPlugin().getConfig().getString("Expiration.Format"));
                Date date = new Date(l);
                return simpleDateFormat.format(date);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                return "Error - Invalid Expiration Format";
            }
        }
        return "-";
    }

    public static void c() {
        Bukkit.getScheduler().runTaskTimer(Main.getPlugin(), () -> {
            if (b() && ConfigManager.getDataConfig().contains("Worlds")) {
                for (String string : ConfigManager.getDataConfig().getConfigurationSection("Worlds").getKeys(false)) {
                    if (q(string) >= System.currentTimeMillis()) continue;
                    a(null, string);
                }
            }
        }, 60L, 1200L);
    }

    public static boolean d(Player player, String string) {
        if (!b(player, string)) {
            return ConfigManager.getDataConfig().getStringList("Worlds." + string + ".Banned").contains(player.getName());
        }
        return false;
    }

    public static int d() {
        int n = 0;
        if (ConfigManager.getDataConfig().contains("Worlds")) {
            n = ConfigManager.getDataConfig().getConfigurationSection("Worlds").getKeys(false).size();
        }
        return n;
    }

    public static int e() {
        int n = 0;
        if (ConfigManager.getDataConfig().contains("Worlds")) {
            for (String string : ConfigManager.getDataConfig().getConfigurationSection("Worlds").getKeys(false)) {
                n += WorldManager.a(Main.getPlugin().getConfig().getString("Basic.World-Prefix") + string, false);
            }
        }
        return n;
    }

    private static /* synthetic */ void a(WorldCreator worldCreator) {
        AsyncWorld.create((WorldCreator)worldCreator).commit();
    }

    public static enum a {
        NORMAL,
        FLAT,
        EMPTY,
        CUSTOM;
        
    }

}

