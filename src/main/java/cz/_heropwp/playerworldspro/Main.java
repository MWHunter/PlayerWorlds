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

import cz._heropwp.playerworldspro.a.PlayerWorldsPro;
import cz._heropwp.playerworldspro.api.API;
import cz._heropwp.playerworldspro.b.*;
import cz._heropwp.playerworldspro.c.BasicEvents;
import cz._heropwp.playerworldspro.c.SettingsEvents;
import cz._heropwp.playerworldspro.d.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Economy G = null;
    private API a;
    private PlayerWorldsPro b;
    private GUI_Buy_PlayerWorld c;
    private GUI_CreateWorld d;
    private GUI_CustomGenerators e;
    private GUI_Extend_PlayerWorld f;
    private GUI_Main g;
    private GUI_PreBuiltMaps h;
    private GUI_Settings i;
    private GUI_Settings_AddMember j;
    private GUI_Settings_BanPlayer k;
    private GUI_Settings_ChangeDefaultGameMode l;
    private GUI_Settings_ChangeDifficulty m;
    private GUI_Settings_ChangeGameMode n;
    private GUI_Settings_ChangeGameMode_SelectPlayer o;
    private GUI_Settings_ChangeTime p;
    private GUI_Settings_ChangeWeather q;
    private GUI_Settings_Fly r;
    private GUI_Settings_KickPlayer s;
    private GUI_Settings_RemoveMember t;
    private GUI_Settings_Teleport u;
    private GUI_Settings_TeleportHere v;
    private GUI_Settings_UnbanPlayer w;
    private GUI_Settings_WorldBorder x;
    private BasicEvents y;
    private SettingsEvents z;
    private BasicManager B;
    private ConfigManager C;
    private MaterialManager D;
    private WorldManager E;
    private boolean isFAWEEnabled;

    public static Economy b() {
        return G;
    }

    public void onEnable() {
        this.saveDefaultConfig();
        this.a = new API(this);
        this.b = new PlayerWorldsPro(this);
        this.c = new GUI_Buy_PlayerWorld(this);
        this.d = new GUI_CreateWorld(this);
        this.e = new GUI_CustomGenerators(this);
        this.f = new GUI_Extend_PlayerWorld(this);
        this.g = new GUI_Main(this);
        this.h = new GUI_PreBuiltMaps(this);
        this.i = new GUI_Settings(this);
        this.j = new GUI_Settings_AddMember(this);
        this.k = new GUI_Settings_BanPlayer(this);
        this.l = new GUI_Settings_ChangeDefaultGameMode(this);
        this.m = new GUI_Settings_ChangeDifficulty(this);
        this.n = new GUI_Settings_ChangeGameMode(this);
        this.o = new GUI_Settings_ChangeGameMode_SelectPlayer(this);
        this.p = new GUI_Settings_ChangeTime(this);
        this.q = new GUI_Settings_ChangeWeather(this);
        this.r = new GUI_Settings_Fly(this);
        this.s = new GUI_Settings_KickPlayer(this);
        this.t = new GUI_Settings_RemoveMember(this);
        this.u = new GUI_Settings_Teleport(this);
        this.v = new GUI_Settings_TeleportHere(this);
        this.w = new GUI_Settings_UnbanPlayer(this);
        this.x = new GUI_Settings_WorldBorder(this);
        this.y = new BasicEvents(this);
        this.z = new SettingsEvents(this);
        this.B = new BasicManager(this);
        this.C = new ConfigManager(this);
        this.C.createFoldersAndLoadFiles();
        this.D = new MaterialManager();
        this.E = new WorldManager(this);
        this.setCommandExecutor();
        this.registerEvents();
        this.checkFAWEEnabled();
        this.E.a();
        this.isEconomyEnabled();
        this.E.c();
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
        this.getCommand("PlayerWorldsPro").setExecutor(this.b);
    }

    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(this.c, this);
        pluginManager.registerEvents(this.d, this);
        pluginManager.registerEvents(this.e, this);
        pluginManager.registerEvents(this.f, this);
        pluginManager.registerEvents(this.g, this);
        pluginManager.registerEvents(this.h, this);
        pluginManager.registerEvents(this.i, this);
        pluginManager.registerEvents(this.j, this);
        pluginManager.registerEvents(this.k, this);
        pluginManager.registerEvents(this.l, this);
        pluginManager.registerEvents(this.m, this);
        pluginManager.registerEvents(this.n, this);
        pluginManager.registerEvents(this.o, this);
        pluginManager.registerEvents(this.p, this);
        pluginManager.registerEvents(this.q, this);
        pluginManager.registerEvents(this.r, this);
        pluginManager.registerEvents(this.s, this);
        pluginManager.registerEvents(this.t, this);
        pluginManager.registerEvents(this.u, this);
        pluginManager.registerEvents(this.v, this);
        pluginManager.registerEvents(this.w, this);
        pluginManager.registerEvents(this.x, this);
        pluginManager.registerEvents(this.y, this);
        pluginManager.registerEvents(this.z, this);
    }

    private boolean isEconomyEnabled() {
        if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider registeredServiceProvider = this.getServer().getServicesManager().getRegistration(Economy.class);
        if (registeredServiceProvider == null) {
            return false;
        }
        G = (Economy) registeredServiceProvider.getProvider();
        return G != null;
    }

    public API c() {
        return this.a;
    }

    public PlayerWorldsPro d() {
        return this.b;
    }

    public GUI_Buy_PlayerWorld e() {
        return this.c;
    }

    public GUI_CreateWorld f() {
        return this.d;
    }

    public GUI_CustomGenerators g() {
        return this.e;
    }

    public GUI_Extend_PlayerWorld h() {
        return this.f;
    }

    public GUI_Main i() {
        return this.g;
    }

    public GUI_PreBuiltMaps j() {
        return this.h;
    }

    public GUI_Settings k() {
        return this.i;
    }

    public GUI_Settings_AddMember l() {
        return this.j;
    }

    public GUI_Settings_BanPlayer m() {
        return this.k;
    }

    public GUI_Settings_ChangeDefaultGameMode n() {
        return this.l;
    }

    public GUI_Settings_ChangeDifficulty o() {
        return this.m;
    }

    public GUI_Settings_ChangeGameMode p() {
        return this.n;
    }

    public GUI_Settings_ChangeGameMode_SelectPlayer q() {
        return this.o;
    }

    public GUI_Settings_ChangeTime r() {
        return this.p;
    }

    public GUI_Settings_ChangeWeather s() {
        return this.q;
    }

    public GUI_Settings_Fly t() {
        return this.r;
    }

    public GUI_Settings_KickPlayer u() {
        return this.s;
    }

    public GUI_Settings_RemoveMember v() {
        return this.t;
    }

    public GUI_Settings_Teleport w() {
        return this.u;
    }

    public GUI_Settings_TeleportHere x() {
        return this.v;
    }

    public GUI_Settings_UnbanPlayer y() {
        return this.w;
    }

    public GUI_Settings_WorldBorder z() {
        return this.x;
    }

    public BasicEvents A() {
        return this.y;
    }

    public SettingsEvents B() {
        return this.z;
    }

    public BasicManager D() {
        return this.B;
    }

    public ConfigManager E() {
        return this.C;
    }

    public MaterialManager F() {
        return this.D;
    }

    public WorldManager G() {
        return this.E;
    }

    public boolean H() {
        return this.isFAWEEnabled;
    }
}

