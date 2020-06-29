/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package cz._heropwp.playerworldspro.api;

import cz._heropwp.playerworldspro.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class API {

    public static boolean hasPlayerWorld(String string) {
        return Main.G().c(string);
    }

    public static String getPlayerWorldOwner(String string) {
        return Main.G().d(string);
    }

    public static boolean isMember(Player player, String string) {
        return Main.G().c(player, string);
    }

    public static boolean isExpirationEnabled() {
        return Main.G().b();
    }

    public static boolean isWeatherCycle(String string) {
        return Main.G().e(string);
    }

    public static String getDifficulty(String string) {
        return Main.G().f(string);
    }

    public static boolean isBlockBreaking(String string) {
        return Main.G().g(string);
    }

    public static boolean isBlockPlacing(String string) {
        return Main.G().h(string);
    }

    public static boolean isPvP(String string) {
        return Main.G().i(string);
    }

    public static String getGameMode(String string) {
        return Main.G().j(string);
    }

    public static boolean isPickup(String string) {
        return Main.G().k(string);
    }

    public static boolean isDrop(String string) {
        return Main.G().l(string);
    }

    public static boolean isDamage(String string) {
        return Main.G().m(string);
    }

    public static boolean isHunger(String string) {
        return Main.G().n(string);
    }

    public static String getAccess(String string) {
        return Main.G().p(string);
    }

    public static Long getExpiration(String string) {
        return Main.G().q(string);
    }

    public static String getExpirationDate(String string) {
        return Main.G().r(string);
    }

    public static void deletePlayerWorld(String string) {
        Main.G().a((CommandSender)null, string);
    }
}

