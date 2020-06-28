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
package cz._heropwp.playerworldspro.d;

import com.boydti.fawe.bukkit.wrapper.AsyncWorld;
import com.boydti.fawe.util.TaskManager;
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
    private final Main a;
    private final HashMap<String, Long> b;

    public WorldManager(Main main) {
        this.a = main;
        this.b = new HashMap();
    }

    public int a(String string, boolean bl) {
        int n = Bukkit.getWorld(string) != null ? Bukkit.getWorld(string).getPlayers().size() : 0;
        if (bl && n < 1) {
            return 1;
        }
        return n;
    }

    public void a() {
        if (/*this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds") &&*/ this.a.getConfig().getBoolean("Basic.Load-On-Startup")) {
            Bukkit.getConsoleSender().sendMessage(this.a.D().f() + "§eLoading player worlds...");
            for (String string : this.a.E().a(ConfigManager.dataOrPlayers.DATA).getConfigurationSection("Worlds").getKeys(false)) {
                this.c(string, false);
                Bukkit.getConsoleSender().sendMessage(this.a.D().f() + "§aPlayer world of player " + string + " has been successfully loaded!");
            }
        }
    }

    private void c(String string, boolean bl) {
        Object object;
        String string2 = this.a.getConfig().getString("Basic.World-Prefix") + string;
        String a2 = this.a.E().a(ConfigManager.dataOrPlayers.DATA).getString("Worlds." + string + ".Type");
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
                if (!this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds." + string + ".Generator")) break;
                object = this.a.E().a(ConfigManager.dataOrPlayers.DATA).getString("Worlds." + string + ".Generator");
                worldCreator = new WorldCreator(string2).generator((String)object);
            }
        }
        if (worldCreator != null) {
            if (this.a.H()) {
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

    public void a(Player player, a a2, String string, String string2, Integer n, Integer n2, boolean bl) {
        File file;
        boolean bl2;
        long cooldown;
        File file2;
        long l;
        player.closeInventory();
        if (this.c(player.getName())) {
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Already-Have").replace("&", "§"));
            return;
        }
        if (n2 != null) {
            if (Main.b().getBalance((OfflinePlayer)player) < (double)n2.intValue()) {
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Not-Enough-Money").replace("&", "§"));
                return;
            }
            Main.b().withdrawPlayer((OfflinePlayer)player, (double)n2.intValue());
        }
        if (this.a.getConfig().getBoolean("Cooldown.Enabled") && this.b.containsKey(player.getName())) {
            l = System.currentTimeMillis() - this.b.get(player.getName());
            cooldown = this.a.getConfig().getInt("Cooldown.Interval");
            if (TimeUnit.MILLISECONDS.toSeconds(l) < cooldown) {
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Create-Cooldown").replace("&", "§").replace("%time%", String.valueOf((long)cooldown - TimeUnit.MILLISECONDS.toSeconds(l))));
                return;
            }
        }
        this.b.put(player.getName(), System.currentTimeMillis());
        if (n != null) {
            l = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(n.intValue());
            this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + player.getName() + ".Expiration", (Object)l);
        }
        if (bl) {
            this.a.E().a(ConfigManager.dataOrPlayers.PLAYERS).set("Claim." + player.getName(), (Object)"");
            this.a.E().b(ConfigManager.dataOrPlayers.PLAYERS);
            this.a.E().c(ConfigManager.dataOrPlayers.PLAYERS);
        }
        this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + player.getName() + ".Type", (Object)a2.toString());
        if (string2 != null) {
            this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + player.getName() + ".Generator", (Object)string2);
        }
        this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + player.getName() + ".WeatherCycle", (Object)this.a.getConfig().getBoolean("Default-Settings.WeatherCycle"));
        this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + player.getName() + ".Difficulty", (Object)this.a.getConfig().getString("Default-Settings.Difficulty"));
        this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + player.getName() + ".Block-Breaking", (Object)this.a.getConfig().getBoolean("Default-Settings.Block-Breaking"));
        this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + player.getName() + ".Block-Placing", (Object)this.a.getConfig().getBoolean("Default-Settings.Block-Placing"));
        this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + player.getName() + ".PvP", (Object)this.a.getConfig().getBoolean("Default-Settings.PvP"));
        this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + player.getName() + ".Pickup", (Object)this.a.getConfig().getBoolean("Default-Settings.Pickup"));
        this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + player.getName() + ".Drop", (Object)this.a.getConfig().getBoolean("Default-Settings.Drop"));
        this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + player.getName() + ".Damage", (Object)this.a.getConfig().getBoolean("Default-Settings.Damage"));
        this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + player.getName() + ".Hunger", (Object)this.a.getConfig().getBoolean("Default-Settings.Hunger"));
        this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + player.getName() + ".Bucket", (Object)this.a.getConfig().getBoolean("Default-Settings.Hunger"));
        this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + player.getName() + ".GameMode", (Object)this.a.getConfig().getString("Default-Settings.GameMode"));
        this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + player.getName() + ".Access", (Object)this.a.getConfig().getString("Default-Settings.Access"));
        this.a.E().b(ConfigManager.dataOrPlayers.DATA);
        this.a.E().c(ConfigManager.dataOrPlayers.DATA);
        String string3 = this.a.getConfig().getString("Basic.World-Prefix") + player.getName();
        if (Bukkit.getWorld((String)string3) != null) {
            this.b(string3);
            Bukkit.unloadWorld((String)string3, (boolean)true);
        }
        if ((file2 = new File(Bukkit.getWorldContainer(), string3)).exists()) {
            try {
                FileUtils.deleteDirectory(file2);
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
        if (string != null && (file = new File(this.a.getDataFolder() + "/maps/" + string)).exists() && file.isDirectory()) {
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
        player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Creating-World").replace("&", "§"));
        //bl2 = a2 == a.EMPTY && string == null;
        //this.c(player.getName());
        player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.World-Created").replace("&", "§"));
        this.a.k().b().remove(player.getName());
        Bukkit.getScheduler().runTaskLater((Plugin)this.a, () -> {
            World world;
            int m;
            if (this.a.getConfig().getBoolean("Basic.Teleport-On-Create")) {
                this.a(player, player.getName());
            }
            if ((world = Bukkit.getWorld((String)string3)) != null && (m = this.a.getConfig().getInt("Default-Settings.World-Border")) > 0) {
                world.getWorldBorder().setSize((double)m);
            }
            if (this.a.getConfig().getBoolean("Commands.Create.Enabled")) {
                for (String string4 : this.a.getConfig().getStringList("Commands.Create.List")) {
                    string4 = string4.replace("%owner%", player.getName());
                    string4 = string4.replace("%world%", string3);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), string4);
                }
            }
        }, 20L);
    }

    public void a(CommandSender commandSender, String string, String string2, boolean bl) {
        if (!this.b()) {
            commandSender.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Expiration-Disabled").replace("&", "§"));
            return;
        }
        if (!this.c(string)) {
            commandSender.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.World-NotFound").replace("&", "§").replace("%player%", string));
            return;
        }
        Integer n = null;
        if (string2 != null) {
            if (commandSender instanceof Player && bl) {
                Player player = (Player)commandSender;
                int n2 = this.a.getConfig().getInt("GUI.Extend-Player-World.Items." + string2 + ".Price");
                if (Main.b().getBalance((OfflinePlayer)player) < (double)n2) {
                    commandSender.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Not-Enough-Money").replace("&", "§"));
                    return;
                }
                Main.b().withdrawPlayer((OfflinePlayer)player, (double)n2);
                n = this.a.getConfig().getInt("GUI.Extend-Player-World.Items." + string2 + ".Length");
            } else {
                n = Integer.valueOf(string2);
            }
        }
        if (n != null) {
            long l = this.q(string) + TimeUnit.DAYS.toMillis(n.intValue());
            this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string + ".Expiration", (Object)l);
            this.a.E().b(ConfigManager.dataOrPlayers.DATA);
            this.a.E().c(ConfigManager.dataOrPlayers.DATA);
            commandSender.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Successfully-Extended").replace("&", "§").replace("%length%", String.valueOf(n)));
        }
    }

    public void a(CommandSender commandSender, String string) {
        if (this.c(string)) {
            String string2 = this.a.getConfig().getString("Basic.World-Prefix") + string;
            if (commandSender != null && commandSender instanceof Player) {
                this.a.k().b().remove(commandSender.getName());
            }
            if (Bukkit.getWorld((String)string2) != null) {
                this.b(string2);
            }
            this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string, null);
            this.a.E().b(ConfigManager.dataOrPlayers.DATA);
            this.a.E().c(ConfigManager.dataOrPlayers.DATA);
            Bukkit.getScheduler().runTaskLater((Plugin)this.a, () -> {
                if (Bukkit.getWorld((String)string2) != null && !Bukkit.unloadWorld((String)string2, (boolean)true)) {
                    if (commandSender != null) {
                        commandSender.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.World-Delete-Error").replace("&", "§"));
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
                            commandSender.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.World-Delete-Error").replace("&", "§"));
                        }
                        iOException.printStackTrace();
                        return;
                    }
                }
                if (commandSender != null) {
                    commandSender.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.World-Deleted").replace("&", "§"));
                }
                if (this.a.getConfig().getBoolean("Commands.Delete.Enabled")) {
                    for (String string3 : this.a.getConfig().getStringList("Commands.Delete.List")) {
                        string3 = string3.replace("%owner%", string);
                        string3 = string3.replace("%world%", string2);
                        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)string3);
                    }
                }
            }, 3L);
        } else if (commandSender != null) {
            commandSender.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.World-NotFound").replace("&", "§").replace("%player%", string));
        }
    }

    public void a(Player player, String string) {
        String string2 = this.a.getConfig().getString("Basic.World-Prefix") + string;
        if (!this.c(string)) {
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Doesnt-Have").replace("&", "§").replace("%player%", string));
            return;
        }
        if (this.d(player, string)) {
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Banned-Teleport").replace("&", "§"));
            return;
        }
        if (this.p(string).equals("PRIVATE") && !this.c(player, string)) {
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Access.Only-For-Members").replace("&", "§"));
            return;
        }
        if (Bukkit.getWorld((String)string2) == null) {
            this.c(string, false);
            Bukkit.getScheduler().runTaskLater((Plugin)this.a, () -> this.a(player, Bukkit.getWorld((String)string2), string), 20L);
        } else {
            this.a(player, Bukkit.getWorld((String)string2), string);
        }
    }

    private void a(Player player, World world, String string) {
        if (world != null) {
            this.a.D().a(player, this.a(string));
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Teleported").replace("&", "§"));
            player.setGameMode(GameMode.valueOf((String)this.j(string)));
        } else {
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Unloaded-World").replace("&", "§"));
        }
    }

    public void a(Player player, String string, boolean bl) {
        String string2 = this.a.getConfig().getString("Basic.World-Prefix") + string;
        if (string2.equals(player.getWorld().getName())) {
            double d2 = player.getLocation().getX();
            double d3 = player.getLocation().getY();
            double d4 = player.getLocation().getZ();
            float f2 = player.getLocation().getYaw();
            float f3 = player.getLocation().getPitch();
            this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string + ".Spawn", (Object)(d2 + ";" + d3 + ";" + d4 + ";" + f2 + ";" + f3));
            this.a.E().b(ConfigManager.dataOrPlayers.DATA);
            this.a.E().c(ConfigManager.dataOrPlayers.DATA);
            if (bl) {
                player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Spawn-Setup").replace("&", "§"));
            }
        } else if (bl) {
            player.sendMessage(this.a.D().e() + this.a.getConfig().getString("Messages.Same-World").replace("&", "§"));
        }
    }

    public Location a(String string) {
        String string2;
        World world;
        if (this.c(string) && (world = Bukkit.getWorld((String)((string2 = this.a.getConfig().getString("Basic.World-Prefix")) + string))) != null) {
            if (this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds." + string + ".Spawn")) {
                String string3 = this.a.E().a(ConfigManager.dataOrPlayers.DATA).getString("Worlds." + string + ".Spawn");
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

    public void b(String string) {
        if (Bukkit.getWorld((String)string) != null) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!player.getWorld().getName().equals(string)) continue;
                if (this.a.D().b()) {
                    this.a.D().a(player, this.a.D().c());
                    continue;
                }
                player.kickPlayer(this.a.D().e() + this.a.getConfig().getString("Messages.Lobby-Is-Not-Configured").replace("&", "§"));
            }
        }
    }

    public boolean c(String string) {
        return this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds." + string);
    }

    public String d(String string) {
        String string2;
        String string3 = this.a.getConfig().getString("Basic.World-Prefix");
        if (string.startsWith(string3) && this.c(string2 = string.split(string3)[1])) {
            return string2;
        }
        return null;
    }

    public boolean b(Player player, String string) {
        if (this.c(string)) {
            if (player.getName().equals(string)) {
                return true;
            }
            return player.hasPermission("PlayerWorldsPro.other");
        }
        return false;
    }

    public boolean c(Player player, String string) {
        if (this.b(player, string)) {
            return true;
        }
        if (this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds." + string + ".Members")) {
            return this.a.E().a(ConfigManager.dataOrPlayers.DATA).getStringList("Worlds." + string + ".Members").contains(player.getName());
        }
        return false;
    }

    public boolean b() {
        return this.a.getConfig().getBoolean("Expiration.Enabled") && Main.b() != null;
    }

    public boolean e(String string) {
        if (this.c(string) && this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds." + string + ".WeatherCycle")) {
            return this.a.E().a(ConfigManager.dataOrPlayers.DATA).getBoolean("Worlds." + string + ".WeatherCycle");
        }
        return true;
    }

    public void b(String string, boolean bl) {
        if (this.c(string)) {
            this.a.E().a(ConfigManager.dataOrPlayers.DATA).set("Worlds." + string + ".WeatherCycle", (Object)bl);
            this.a.E().b(ConfigManager.dataOrPlayers.DATA);
            this.a.E().c(ConfigManager.dataOrPlayers.DATA);
        }
    }

    public String f(String string) {
        if (this.c(string) && this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds." + string + ".Difficulty")) {
            return this.a.E().a(ConfigManager.dataOrPlayers.DATA).getString("Worlds." + string + ".Difficulty");
        }
        return "NORMAL";
    }

    public boolean g(String string) {
        if (this.c(string) && this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds." + string + ".Block-Breaking")) {
            return this.a.E().a(ConfigManager.dataOrPlayers.DATA).getBoolean("Worlds." + string + ".Block-Breaking");
        }
        return true;
    }

    public boolean h(String string) {
        if (this.c(string) && this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds." + string + ".Block-Placing")) {
            return this.a.E().a(ConfigManager.dataOrPlayers.DATA).getBoolean("Worlds." + string + ".Block-Placing");
        }
        return true;
    }

    public boolean i(String string) {
        if (this.c(string) && this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds." + string + ".PvP")) {
            return this.a.E().a(ConfigManager.dataOrPlayers.DATA).getBoolean("Worlds." + string + ".PvP");
        }
        return true;
    }

    public String j(String string) {
        if (this.c(string) && this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds." + string + ".GameMode")) {
            return this.a.E().a(ConfigManager.dataOrPlayers.DATA).getString("Worlds." + string + ".GameMode");
        }
        return "SURVIVAL";
    }

    public boolean k(String string) {
        if (this.c(string) && this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds." + string + ".Pickup")) {
            return this.a.E().a(ConfigManager.dataOrPlayers.DATA).getBoolean("Worlds." + string + ".Pickup");
        }
        return true;
    }

    public boolean l(String string) {
        if (this.c(string) && this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds." + string + ".Drop")) {
            return this.a.E().a(ConfigManager.dataOrPlayers.DATA).getBoolean("Worlds." + string + ".Drop");
        }
        return true;
    }

    public boolean m(String string) {
        if (this.c(string) && this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds." + string + ".Damage")) {
            return this.a.E().a(ConfigManager.dataOrPlayers.DATA).getBoolean("Worlds." + string + ".Damage");
        }
        return true;
    }

    public boolean n(String string) {
        if (this.c(string) && this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds." + string + ".Hunger")) {
            return this.a.E().a(ConfigManager.dataOrPlayers.DATA).getBoolean("Worlds." + string + ".Hunger");
        }
        return true;
    }

    public boolean o(String string) {
        if (this.c(string) && this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds." + string + ".Bucket")) {
            return this.a.E().a(ConfigManager.dataOrPlayers.DATA).getBoolean("Worlds." + string + ".Bucket");
        }
        return true;
    }

    public String p(String string) {
        if (this.c(string) && this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds." + string + ".Access")) {
            return this.a.E().a(ConfigManager.dataOrPlayers.DATA).getString("Worlds." + string + ".Access");
        }
        return "PUBLIC";
    }

    public Long q(String string) {
        if (this.c(string) && this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds." + string + ".Expiration")) {
            return this.a.E().a(ConfigManager.dataOrPlayers.DATA).getLong("Worlds." + string + ".Expiration");
        }
        return 0L;
    }

    public String r(String string) {
        Long l = this.q(string);
        if (l > 0L) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.a.getConfig().getString("Expiration.Format"));
                Date date = new Date(l);
                return simpleDateFormat.format(date);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                return "Error - Invalid Expiration Format";
            }
        }
        return "-";
    }

    public void c() {
        Bukkit.getScheduler().runTaskTimer((Plugin)this.a, () -> {
            if (this.b() && this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds")) {
                for (String string : this.a.E().a(ConfigManager.dataOrPlayers.DATA).getConfigurationSection("Worlds").getKeys(false)) {
                    if (this.q(string) >= System.currentTimeMillis()) continue;
                    this.a((CommandSender)null, string);
                }
            }
        }, 60L, 1200L);
    }

    public boolean d(Player player, String string) {
        if (!this.b(player, string)) {
            return this.a.E().a(ConfigManager.dataOrPlayers.DATA).getStringList("Worlds." + string + ".Banned").contains(player.getName());
        }
        return false;
    }

    public int d() {
        int n = 0;
        if (this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds")) {
            n = this.a.E().a(ConfigManager.dataOrPlayers.DATA).getConfigurationSection("Worlds").getKeys(false).size();
        }
        return n;
    }

    public int e() {
        int n = 0;
        if (this.a.E().a(ConfigManager.dataOrPlayers.DATA).contains("Worlds")) {
            for (String string : this.a.E().a(ConfigManager.dataOrPlayers.DATA).getConfigurationSection("Worlds").getKeys(false)) {
                n += this.a.G().a(this.a.getConfig().getString("Basic.World-Prefix") + string, false);
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

