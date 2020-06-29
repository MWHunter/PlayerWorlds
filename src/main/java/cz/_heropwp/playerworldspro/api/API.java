/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package cz._heropwp.playerworldspro.api;

import cz._heropwp.playerworldspro.CoreManagers.WorldManager;
import cz._heropwp.playerworldspro.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class API {

    public static boolean hasPlayerWorld(String string) {
        return WorldManager.c(string);
    }

    public static String getPlayerWorldOwner(String string) {
        return WorldManager.d(string);
    }

    public static boolean isMember(Player player, String string) {
        return WorldManager.c(player, string);
    }

    public static boolean isExpirationEnabled() {
        return WorldManager.b();
    }

    public static boolean isWeatherCycle(String string) {
        return WorldManager.e(string);
    }

    public static String getDifficulty(String string) {
        return WorldManager.f(string);
    }

    public static boolean isBlockBreaking(String string) {
        return WorldManager.g(string);
    }

    public static boolean isBlockPlacing(String string) {
        return WorldManager.h(string);
    }

    public static boolean isPvP(String string) {
        return WorldManager.i(string);
    }

    public static String getGameMode(String string) {
        return WorldManager.j(string);
    }

    public static boolean isPickup(String string) {
        return WorldManager.k(string);
    }

    public static boolean isDrop(String string) {
        return WorldManager.l(string);
    }

    public static boolean isDamage(String string) {
        return WorldManager.m(string);
    }

    public static boolean isHunger(String string) {
        return WorldManager.n(string);
    }

    public static String getAccess(String string) {
        return WorldManager.p(string);
    }

    public static Long getExpiration(String string) {
        return WorldManager.q(string);
    }

    public static String getExpirationDate(String string) {
        return WorldManager.r(string);
    }

    public static void deletePlayerWorld(String string) {
        WorldManager.a((CommandSender)null, string);
    }
}

