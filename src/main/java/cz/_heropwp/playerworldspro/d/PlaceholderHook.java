/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  me.clip.placeholderapi.expansion.PlaceholderExpansion
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.plugin.PluginDescriptionFile
 */
package cz._heropwp.playerworldspro.d;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.d.WorldManager;
import java.util.List;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.PluginDescriptionFile;

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
        return this.a.getDescription().getAuthors().toString();
    }

    public String getVersion() {
        return this.a.getDescription().getVersion();
    }

    public String onRequest(OfflinePlayer offlinePlayer, String string) {
        if (offlinePlayer == null) {
            return "";
        }
        if (string.equals("worlds")) {
            return String.valueOf(this.a.G().d());
        }
        if (string.equals("online_players")) {
            return String.valueOf(this.a.G().e());
        }
        return null;
    }
}

