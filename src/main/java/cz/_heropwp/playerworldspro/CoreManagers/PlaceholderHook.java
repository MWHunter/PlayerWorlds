/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  me.clip.placeholderapi.expansion.PlaceholderExpansion
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.plugin.PluginDescriptionFile
 */
package cz._heropwp.playerworldspro.CoreManagers;

import cz._heropwp.playerworldspro.Main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class PlaceholderHook
extends PlaceholderExpansion {
    private final Main a;

    public PlaceholderHook(Main main) {
        this.a = main;
    }

    public boolean persist() {
        return true;
    }

    public boolean canRegister() {
        return true;
    }

    public String getIdentifier() {
        return "pwp";
    }

    public String getAuthor() {
        return Main.getPlugin().getDescription().getAuthors().toString();
    }

    public String getVersion() {
        return Main.getPlugin().getDescription().getVersion();
    }

    public String onRequest(OfflinePlayer offlinePlayer, String string) {
        if (offlinePlayer == null) {
            return "";
        }
        if (string.equals("worlds")) {
            return String.valueOf(Main.G().d());
        }
        if (string.equals("online_players")) {
            return String.valueOf(Main.G().e());
        }
        return null;
    }
}

