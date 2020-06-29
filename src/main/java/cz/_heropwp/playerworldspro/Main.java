/*
 * Decompiled with CFR 0.145.
 *
 * Could not load the following classes:
 *  net.milkbowl.vault.economy.Economy
 *  org.bukkit.Bukkit
 *  org.bukkit.Server
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.PluginCommand
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.event.Listener
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginDescriptionFile
 *  org.bukkit.plugin.PluginManager
 *  org.bukkit.plugin.RegisteredServiceProvider
 *  org.bukkit.plugin.ServicesManager
 *  org.bukkit.plugin.java.JavaPlugin
 *  org.bukkit.scheduler.BukkitTask
 */
package cz._heropwp.playerworldspro;

import cz._heropwp.playerworldspro.CommandExecutor.PlayerWorldsPro;
import cz._heropwp.playerworldspro.api.API;
import cz._heropwp.playerworldspro.GUI.*;
import cz._heropwp.playerworldspro.EventsListener.BasicEvents;
import cz._heropwp.playerworldspro.EventsListener.SettingsEvents;
import cz._heropwp.playerworldspro.CoreManagers.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Economy economy = null;
    private API a;
    private static PlayerWorldsPro b;
    private static GUI_Buy_PlayerWorld c;
    private static GUI_CreateWorld d;
    private static GUI_CustomGenerators e;
    private static GUI_Extend_PlayerWorld f;
    private static GUI_Main g;
    private static GUI_PreBuiltMaps h;
    private static GUI_Settings i;
    private static GUI_Settings_AddMember j;
    private static GUI_Settings_BanPlayer k;
    private static GUI_Settings_ChangeDefaultGameMode l;
    private static GUI_Settings_ChangeDifficulty m;
    private static GUI_Settings_ChangeGameMode n;
    private static GUI_Settings_ChangeGameMode_SelectPlayer o;
    private static GUI_Settings_ChangeTime p;
    private static GUI_Settings_ChangeWeather q;
    private static GUI_Settings_Fly r;
    private static GUI_Settings_KickPlayer s;
    private static GUI_Settings_RemoveMember t;
    private static GUI_Settings_Teleport u;
    private static GUI_Settings_TeleportHere v;
    private static GUI_Settings_UnbanPlayer w;
    private static GUI_Settings_WorldBorder x;
    private static BasicEvents y;
    private static SettingsEvents z;
    private static BasicManager B;
    private static ConfigManager C;
    private static MaterialManager D;
    private static WorldManager E;
    private static boolean isFAWEEnabled;
    private static Plugin plugin;

    public static Economy b() {
        return economy;
    }

    public void onEnable() {
        plugin = this;

        this.saveDefaultConfig();
        this.a = new API(this);
        b = new PlayerWorldsPro(this);
        c = new GUI_Buy_PlayerWorld();
        d = new GUI_CreateWorld(this);
        e = new GUI_CustomGenerators(this);
        f = new GUI_Extend_PlayerWorld(this);
        g = new GUI_Main(this);
        h = new GUI_PreBuiltMaps(this);
        i = new GUI_Settings(this);
        j = new GUI_Settings_AddMember(this);
        k = new GUI_Settings_BanPlayer(this);
        l = new GUI_Settings_ChangeDefaultGameMode(this);
        m = new GUI_Settings_ChangeDifficulty(this);
        n = new GUI_Settings_ChangeGameMode(this);
        o = new GUI_Settings_ChangeGameMode_SelectPlayer(this);
        p = new GUI_Settings_ChangeTime(this);
        q = new GUI_Settings_ChangeWeather(this);
        r = new GUI_Settings_Fly(this);
        s = new GUI_Settings_KickPlayer(this);
        t = new GUI_Settings_RemoveMember(this);
        u = new GUI_Settings_Teleport(this);
        v = new GUI_Settings_TeleportHere(this);
        w = new GUI_Settings_UnbanPlayer(this);
        x = new GUI_Settings_WorldBorder(this);
        y = new BasicEvents(this);
        z = new SettingsEvents(this);
        B = new BasicManager();
        C = new ConfigManager();
        C.createFoldersAndLoadFiles();
        D = new MaterialManager();
        E = new WorldManager();
        this.setCommandExecutor();
        this.registerEvents();
        this.checkFAWEEnabled();
        E.a();
        this.isEconomyEnabled();
        E.c();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderHook(this).register();
        }
    }

    public void checkFAWEEnabled() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        if (this.getConfig().getBoolean("Basic.Async.FAWE") && pluginManager.getPlugin("FastAsyncWorldEdit") != null && pluginManager.getPlugin("FastAsyncWorldEdit").isEnabled()) {
            this.isFAWEEnabled = true;
            return;
        }
        this.isFAWEEnabled = false;
    }

    private void setCommandExecutor() {
        this.getCommand("PlayerWorldsPro").setExecutor(b);
    }

    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(c, this);
        pluginManager.registerEvents(d, this);
        pluginManager.registerEvents(e, this);
        pluginManager.registerEvents(f, this);
        pluginManager.registerEvents(g, this);
        pluginManager.registerEvents(h, this);
        pluginManager.registerEvents(i, this);
        pluginManager.registerEvents(j, this);
        pluginManager.registerEvents(k, this);
        pluginManager.registerEvents(l, this);
        pluginManager.registerEvents(m, this);
        pluginManager.registerEvents(n, this);
        pluginManager.registerEvents(o, this);
        pluginManager.registerEvents(p, this);
        pluginManager.registerEvents(q, this);
        pluginManager.registerEvents(r, this);
        pluginManager.registerEvents(s, this);
        pluginManager.registerEvents(t, this);
        pluginManager.registerEvents(u, this);
        pluginManager.registerEvents(v, this);
        pluginManager.registerEvents(w, this);
        pluginManager.registerEvents(x, this);
        pluginManager.registerEvents(y, this);
        pluginManager.registerEvents(z, this);
    }

    private boolean isEconomyEnabled() {
        if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider registeredServiceProvider = this.getServer().getServicesManager().getRegistration(Economy.class);
        if (registeredServiceProvider == null) {
            return false;
        }
        economy = (Economy) registeredServiceProvider.getProvider();
        return economy != null;
    }

    public API c() {
        return this.a;
    }

    public PlayerWorldsPro d() {
        return b;
    }

    public GUI_Buy_PlayerWorld e() {
        return c;
    }

    public GUI_CreateWorld f() {
        return d;
    }

    public GUI_CustomGenerators g() {
        return e;
    }

    public GUI_Extend_PlayerWorld h() {
        return f;
    }

    public GUI_Main i() {
        return g;
    }

    public GUI_PreBuiltMaps j() {
        return h;
    }

    public static GUI_Settings k() {
        return i;
    }

    public GUI_Settings_AddMember l() {
        return j;
    }

    public GUI_Settings_BanPlayer m() {
        return k;
    }

    public GUI_Settings_ChangeDefaultGameMode n() {
        return l;
    }

    public GUI_Settings_ChangeDifficulty o() {
        return m;
    }

    public GUI_Settings_ChangeGameMode p() {
        return n;
    }

    public GUI_Settings_ChangeGameMode_SelectPlayer q() {
        return o;
    }

    public GUI_Settings_ChangeTime r() {
        return p;
    }

    public GUI_Settings_ChangeWeather s() {
        return q;
    }

    public GUI_Settings_Fly t() {
        return r;
    }

    public GUI_Settings_KickPlayer u() {
        return s;
    }

    public GUI_Settings_RemoveMember v() {
        return t;
    }

    public GUI_Settings_Teleport w() {
        return u;
    }

    public GUI_Settings_TeleportHere x() {
        return v;
    }

    public GUI_Settings_UnbanPlayer y() {
        return w;
    }

    public GUI_Settings_WorldBorder z() {
        return x;
    }

    public BasicEvents A() {
        return y;
    }

    public SettingsEvents B() {
        return z;
    }

    public static BasicManager D() {
        return B;
    }

    public ConfigManager E() {
        return C;
    }

    public MaterialManager F() {
        return D;
    }

    public static WorldManager G() {
        return E;
    }

    public static boolean H() {
        return isFAWEEnabled;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static Economy getEconomy() {
        return economy;
    }
}

