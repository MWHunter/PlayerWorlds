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
    private static Main main;

    public API(Main main) {
        API.main = main;
    }

    public static boolean hasPlayerWorld(String string) {
        return main.G().c(string);
    }

    public static String getPlayerWorldOwner(String string) {
        return main.G().d(string);
    }

    public static boolean isMember(Player player, String string) {
        return main.G().c(player, string);
    }

    public static boolean isExpirationEnabled() {
        return main.G().b();
    }

    public static boolean isWeatherCycle(String string) {
        return main.G().e(string);
    }

    public static String getDifficulty(String string) {
        return main.G().f(string);
    }

    public static boolean isBlockBreaking(String string) {
        return main.G().g(string);
    }

    public static boolean isBlockPlacing(String string) {
        return main.G().h(string);
    }

    public static boolean isPvP(String string) {
        return main.G().i(string);
    }

    public static String getGameMode(String string) {
        return main.G().j(string);
    }

    public static boolean isPickup(String string) {
        return main.G().k(string);
    }

    public static boolean isDrop(String string) {
        return main.G().l(string);
    }

    public static boolean isDamage(String string) {
        return main.G().m(string);
    }

    public static boolean isHunger(String string) {
        return main.G().n(string);
    }

    public static String getAccess(String string) {
        return main.G().p(string);
    }

    public static Long getExpiration(String string) {
        return main.G().q(string);
    }

    public static String getExpirationDate(String string) {
        return main.G().r(string);
    }

    public static void deletePlayerWorld(String string) {
        main.G().a((CommandSender)null, string);
    }
}

