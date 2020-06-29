/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.WorldBorder
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.PluginDescriptionFile
 */
package cz._heropwp.playerworldspro.CommandExecutor;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.CoreManagers.ConfigManager;

import java.util.concurrent.TimeUnit;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerWorldsPro
implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] arrstring) {
        if (arrstring.length == 0) {
            commandSender.sendMessage(Main.D().getPluginPrefix() + "§2§lPlayerWorldsPro §cv" + Main.getPlugin().getDescription().getVersion());
            commandSender.sendMessage(Main.D().getPluginPrefix());
            commandSender.sendMessage(Main.D().getPluginPrefix() + "§6§lCommands:");
            commandSender.sendMessage(Main.D().getPluginPrefix() + "§a/PlayerWorldsPro menu §fOpen menu");
            commandSender.sendMessage(Main.D().getPluginPrefix() + "§a/PlayerWorldsPro join <player> §fTeleport to the player world of the player.");
            commandSender.sendMessage(Main.D().getPluginPrefix() + "§a/PlayerWorldsPro spawn §fTeleport to spawn in a player world.");
            commandSender.sendMessage(Main.D().getPluginPrefix() + "§a/PlayerWorldsPro settings §fOpen your player world settings.");
            commandSender.sendMessage(Main.D().getPluginPrefix() + "§a/PlayerWorldsPro setLobby §fSet Lobby location §c[PlayerWorldsPro.setLobby]");
            commandSender.sendMessage(Main.D().getPluginPrefix() + "§a/PlayerWorldsPro reload §fReload configuration file §c[PlayerWorldsPro.reload]");
            commandSender.sendMessage(Main.D().getPluginPrefix() + "§a/PlayerWorldsPro setWorldBorder <player> <size> §fSet the size of the World Border §c[PlayerWorldsPro.setWorldBorder]");
            commandSender.sendMessage(Main.D().getPluginPrefix() + "§a/PlayerWorldsPro expiration <set/extend> <player> <length_in_days> §fSet the expiration of the player world §c[PlayerWorldsPro.expiration.<set/extend>]");
            commandSender.sendMessage(Main.D().getPluginPrefix() + "§a/PlayerWorldsPro delete <player> §fDelete player world of the player §c[PlayerWorldsPro.delete]");
            commandSender.sendMessage(Main.D().getPluginPrefix());
            commandSender.sendMessage(Main.D().getPluginPrefix() + "§fOriginally created with §4\u2764 §fby §a_HeroPwP");
            commandSender.sendMessage(Main.D().getPluginPrefix() + "§fModified with §4\u2764 §fby DefineOutside");
        } else {
            if (arrstring[0].equalsIgnoreCase("menu")) {
                if (commandSender instanceof Player) {
                    Player player = (Player)commandSender;
                    Main.i().a(player);
                } else {
                    commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Send-From-Game").replace("&", "§"));
                }
                return false;
            }
            if (arrstring[0].equalsIgnoreCase("join")) {
                if (commandSender instanceof Player) {
                    Player player = (Player)commandSender;
                    if (arrstring.length >= 2) {
                        Main.G().a(player, arrstring[1]);
                    } else {
                        commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Usage.Join").replace("&", "§"));
                    }
                } else {
                    commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Send-From-Game").replace("&", "§"));
                }
                return false;
            }
            if (arrstring[0].equalsIgnoreCase("spawn")) {
                if (commandSender instanceof Player) {
                    Player player = (Player)commandSender;
                    World world = player.getWorld();
                    String string2 = Main.getPlugin().getConfig().getString("Basic.World-Prefix");
                    if (world.getName().startsWith(string2)) {
                        String string3 = world.getName().split(string2)[1];
                        if (Main.G().c(string3)) {
                            Main.D().a(player, Main.G().a(string3));
                            player.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Teleported").replace("&", "§"));
                            return false;
                        }
                    }
                    commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.In-Player-World").replace("&", "§"));
                } else {
                    commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Send-From-Game").replace("&", "§"));
                }
                return false;
            }
            if (arrstring[0].equalsIgnoreCase("settings")) {
                if (commandSender instanceof Player) {
                    Player player = (Player)commandSender;
                    if (Main.G().c(player.getName())) {
                        Main.k().a(player, player.getName());
                    } else {
                        player.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Dont-Have").replace("&", "§"));
                    }
                } else {
                    commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Send-From-Game").replace("&", "§"));
                }
                return false;
            }
            if (arrstring[0].equalsIgnoreCase("setLobby")) {
                if (commandSender instanceof Player) {
                    Player player = (Player)commandSender;
                    if (commandSender.hasPermission("PlayerWorldsPro.setLobby")) {
                        World world = Bukkit.getWorld((String)player.getWorld().getName());
                        double d2 = player.getLocation().getX();
                        double d3 = player.getLocation().getY();
                        double d4 = player.getLocation().getZ();
                        float f2 = player.getLocation().getYaw();
                        float f3 = player.getLocation().getPitch();
                        ConfigManager.getDataConfig().set("Lobby", (world.getName() + ";" + d2 + ";" + d3 + ";" + d4 + ";" + f2 + ";" + f3));
                        ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                        ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                        commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Lobby-Setup").replace("&", "§"));
                    } else {
                        commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Insufficient-Permission").replace("&", "§"));
                    }
                } else {
                    commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Send-From-Game").replace("&", "§"));
                }
                return false;
            }
            if (arrstring[0].equalsIgnoreCase("reload")) {
                if (commandSender.hasPermission("PlayerWorldsPro.reload")) {
                    Main.getPlugin().reloadConfig();
                    Main.D().a();
                    Main.checkFAWEEnabled();
                    commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Configuration-Reloaded").replace("&", "§"));
                } else {
                    commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Insufficient-Permission").replace("&", "§"));
                }
                return false;
            }
            if (arrstring[0].equalsIgnoreCase("setWorldBorder")) {
                block65 : {
                    if (commandSender.hasPermission("PlayerWorldsPro.setWorldBorder")) {
                        if (arrstring.length > 2) {
                            String string4 = arrstring[1];
                            if (Main.G().c(string4)) {
                                try {
                                    int n = Integer.parseInt(arrstring[2]);
                                    World world = Bukkit.getWorld((String)(Main.getPlugin().getConfig().getString("Basic.World-Prefix") + string4));
                                    if (world == null) {
                                        commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Unloaded-World").replace("&", "§"));
                                        break block65;
                                    }
                                    world.getWorldBorder().setSize((double)n);
                                    commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.World-Border.Set").replace("&", "§").replace("%size%", String.valueOf(n)));
                                }
                                catch (NumberFormatException numberFormatException) {
                                    commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Invalid-Number").replace("&", "§").replace("%number%", arrstring[2]));
                                }
                            } else {
                                commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Doesnt-Have").replace("&", "§").replace("%player%", string4));
                            }
                        } else {
                            commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Usage.SetWorldBorder").replace("&", "§"));
                        }
                    } else {
                        commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Insufficient-Permission").replace("&", "§"));
                    }
                }
                return false;
            }
            if (arrstring[0].equalsIgnoreCase("expiration")) {
                if (arrstring.length > 3) {
                    if (arrstring[1].equalsIgnoreCase("set")) {
                        block66 : {
                            if (commandSender.hasPermission("PlayerWorldsPro.expiration.set")) {
                                String string5 = arrstring[2];
                                if (Main.G().c(string5)) {
                                    try {
                                        int n = Integer.parseInt(arrstring[3]);
                                        if (n > 0) {
                                            if (Main.G().b()) {
                                                if (Main.G().c(string5)) {
                                                    long l = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(n);
                                                    ConfigManager.getDataConfig().set("Worlds." + string5 + ".Expiration", (Object)l);
                                                    ConfigManager.saveConfig(ConfigManager.dataOrPlayers.DATA);
                                                    ConfigManager.saveFile(ConfigManager.dataOrPlayers.DATA);
                                                    commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Successfully-Set-Expiration").replace("&", "§").replace("%length%", String.valueOf(n)).replace("%player%", string5));
                                                } else {
                                                    commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.World-NotFound").replace("&", "§").replace("%player%", string5));
                                                }
                                            } else {
                                                commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Expiration-Disabled").replace("&", "§"));
                                            }
                                            break block66;
                                        }
                                        commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Positive-Number").replace("&", "§").replace("%number%", arrstring[3]));
                                    }
                                    catch (NumberFormatException numberFormatException) {
                                        commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Invalid-Number").replace("&", "§").replace("%number%", arrstring[3]));
                                    }
                                } else {
                                    commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Doesnt-Have").replace("&", "§").replace("%player%", string5));
                                }
                            } else {
                                commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Insufficient-Permission").replace("&", "§"));
                            }
                        }
                        return false;
                    }
                    if (arrstring[1].equalsIgnoreCase("extend")) {
                        block67 : {
                            if (commandSender.hasPermission("PlayerWorldsPro.expiration.extend")) {
                                String string6 = arrstring[2];
                                if (Main.G().c(string6)) {
                                    try {
                                        int n = Integer.parseInt(arrstring[3]);
                                        if (n > 0) {
                                            Main.G().a(commandSender, string6, String.valueOf(n), false);
                                            break block67;
                                        }
                                        commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Positive-Number").replace("&", "§").replace("%number%", arrstring[3]));
                                    }
                                    catch (NumberFormatException numberFormatException) {
                                        commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Invalid-Number").replace("&", "§").replace("%number%", arrstring[3]));
                                    }
                                } else {
                                    commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Doesnt-Have").replace("&", "§").replace("%player%", string6));
                                }
                            } else {
                                commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Insufficient-Permission").replace("&", "§"));
                            }
                        }
                        return false;
                    }
                }
                commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Usage.Expiration").replace("&", "§"));
                return false;
            }
            if (arrstring[0].equalsIgnoreCase("delete")) {
                if (commandSender.hasPermission("PlayerWorldsPro.delete")) {
                    if (arrstring.length > 1) {
                        Main.G().a(commandSender, arrstring[1]);
                    } else {
                        commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Usage.Delete").replace("&", "§"));
                    }
                } else {
                    commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Insufficient-Permission").replace("&", "§"));
                }
                return false;
            }
            commandSender.sendMessage(Main.D().getPluginPrefix() + Main.getPlugin().getConfig().getString("Messages.Unknown-Argument").replace("&", "§").replace("%argument%", arrstring[0]));
        }
        return false;
    }
}

