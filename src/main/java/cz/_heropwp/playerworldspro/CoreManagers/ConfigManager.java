/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.configuration.file.YamlConfiguration
 */
package cz._heropwp.playerworldspro.CoreManagers;

import cz._heropwp.playerworldspro.Main;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
    private static File playersFile = new File(Main.getPlugin().getDataFolder(), "players.yml");
    private static File dataFile = new File(Main.getPlugin().getDataFolder(), "data.yml");

    private static FileConfiguration playersConfig;
    private static FileConfiguration dataConfig;

    public static void createFoldersAndLoadFiles() {
        if (!(new File(Main.getPlugin().getDataFolder(),"maps")).exists()) new File(Main.getPlugin().getDataFolder(), "maps").mkdirs();
        if (!playersFile.exists()) Main.getPlugin().saveResource("players.yml", false);
        if (!dataFile.exists()) Main.getPlugin().saveResource("data.yml", false);

        playersConfig = YamlConfiguration.loadConfiguration(playersFile);
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    public static FileConfiguration getDataConfig() {
        return dataConfig;
    }

    public static FileConfiguration getPlayersConfig() {
        return playersConfig;
    }

    public static void saveConfig(dataOrPlayers a2) {
        try {
            if (a2 == dataOrPlayers.DATA) {
                dataConfig.save(dataFile);
            } else {
                playersConfig.save(playersFile);
            }
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public static void saveFile(dataOrPlayers a2) {
        if (a2 == dataOrPlayers.DATA) dataConfig = YamlConfiguration.loadConfiguration(dataFile);
        if (a2 == dataOrPlayers.PLAYERS) playersConfig = YamlConfiguration.loadConfiguration(playersFile);
    }

    public enum dataOrPlayers {
        DATA,
        PLAYERS
    }
}