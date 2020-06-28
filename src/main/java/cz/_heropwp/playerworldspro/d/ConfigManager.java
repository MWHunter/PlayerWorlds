/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.configuration.file.YamlConfiguration
 */
package cz._heropwp.playerworldspro.d;

import cz._heropwp.playerworldspro.Main;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
    private final Main main;
    private final HashMap<dataOrPlayers, File> fileHashMap;
    private final HashMap<dataOrPlayers, FileConfiguration> fileConfigurations;

    public ConfigManager(Main main) {
        this.main = main;
        this.fileHashMap = new HashMap<>();
        this.fileConfigurations = new HashMap<>();
    }

    private String getLowerCaseYMLName(dataOrPlayers a2) {
        return a2.toString().toLowerCase() + ".yml";
    }

    public void createFoldersAndLoadFiles() {
        File file;
        if (!this.main.getDataFolder().exists()) {
            this.main.getDataFolder().mkdir();
        }
        if (!(file = new File(this.main.getDataFolder(), "maps")).exists()) {
            file.mkdir();
        }
        /*for (dataOrPlayers a2 : ) {*/ // TODO: Does this work?
        for (dataOrPlayers a2 : dataOrPlayers.values()) {
            File file2 = new File(this.main.getDataFolder(), this.getLowerCaseYMLName(a2));
            if (!file2.exists()) {
                this.main.saveResource(this.getLowerCaseYMLName(a2), false);
            }
            this.fileHashMap.put(a2, file2);
            this.c(a2);
        }
        main.getLogger().log(Level.WARNING, "We have attempted to load some unknown yml files");
    }

    public FileConfiguration a(dataOrPlayers a2) {
        return this.fileConfigurations.get(a2);
    }

    public void b(dataOrPlayers a2) {
        try {
            this.fileConfigurations.get(a2).save(this.fileHashMap.get(a2));
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void c(dataOrPlayers a2) {
        this.fileConfigurations.put(a2, YamlConfiguration.loadConfiguration(this.fileHashMap.get(a2)));
    }

    public enum dataOrPlayers {
        DATA,
        PLAYERS
    }
}

