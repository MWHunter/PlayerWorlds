/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.FishHook
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Snowball
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDamageEvent
 *  org.bukkit.event.entity.EntityDamageEvent$DamageCause
 *  org.bukkit.event.entity.FoodLevelChangeEvent
 *  org.bukkit.event.player.PlayerBucketEmptyEvent
 *  org.bukkit.event.player.PlayerBucketFillEvent
 *  org.bukkit.event.player.PlayerDropItemEvent
 *  org.bukkit.event.player.PlayerPickupItemEvent
 *  org.bukkit.event.weather.WeatherChangeEvent
 *  org.bukkit.projectiles.ProjectileSource
 */
package cz._heropwp.playerworldspro.EventsListener;

import cz._heropwp.playerworldspro.Main;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class SettingsEvents
implements Listener {
    private final Main a;

    public SettingsEvents(Main main) {
        this.a = main;
    }

    @EventHandler
    public void a(WeatherChangeEvent weatherChangeEvent) {
        World world = weatherChangeEvent.getWorld();
        String string = this.a.getConfig().getString("Basic.World-Prefix");
        if (world.getName().startsWith(string)) {
            String string2 = world.getName().split(string)[1];
            if (this.a.G().c(string2) && !this.a.G().e(string2)) {
                weatherChangeEvent.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void a(BlockBreakEvent blockBreakEvent) {
        Player player = blockBreakEvent.getPlayer();
        World world = player.getWorld();
        String string = this.a.getConfig().getString("Basic.World-Prefix");
        if (world.getName().startsWith(string)) {
            String string2 = world.getName().split(string)[1];
            if (this.a.G().c(string2) && !this.a.G().b(player, string2) && !this.a.G().g(string2)) {
                blockBreakEvent.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void a(BlockPlaceEvent blockPlaceEvent) {
        Player player = blockPlaceEvent.getPlayer();
        World world = player.getWorld();
        String string = this.a.getConfig().getString("Basic.World-Prefix");
        if (world.getName().startsWith(string)) {
            String string2 = world.getName().split(string)[1];
            if (this.a.G().c(string2) && !this.a.G().b(player, string2) && !this.a.G().h(string2)) {
                blockPlaceEvent.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void a(EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.getEntity() instanceof Player && entityDamageByEntityEvent.getDamager() instanceof Player) {
            Player player = (Player)entityDamageByEntityEvent.getEntity();
            World world = player.getWorld();
            String string = this.a.getConfig().getString("Basic.World-Prefix");
            if (world.getName().startsWith(string)) {
                String string2 = world.getName().split(string)[1];
                if (this.a.G().c(string2) && !this.a.G().i(string2)) {
                    entityDamageByEntityEvent.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void b(EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.getEntity() instanceof Player && entityDamageByEntityEvent.getDamager() instanceof Arrow && ((Arrow)entityDamageByEntityEvent.getDamager()).getShooter() instanceof Player) {
            Player player = (Player)entityDamageByEntityEvent.getEntity();
            World world = player.getWorld();
            String string = this.a.getConfig().getString("Basic.World-Prefix");
            if (world.getName().startsWith(string)) {
                String string2 = world.getName().split(string)[1];
                if (this.a.G().c(string2) && !this.a.G().i(string2)) {
                    entityDamageByEntityEvent.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void c(EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.getEntity() instanceof Player && entityDamageByEntityEvent.getDamager() instanceof FishHook && ((FishHook)entityDamageByEntityEvent.getDamager()).getShooter() instanceof Player) {
            Player player = (Player)entityDamageByEntityEvent.getEntity();
            World world = player.getWorld();
            String string = this.a.getConfig().getString("Basic.World-Prefix");
            if (world.getName().startsWith(string)) {
                String string2 = world.getName().split(string)[1];
                if (this.a.G().c(string2) && !this.a.G().i(string2)) {
                    entityDamageByEntityEvent.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void d(EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.getEntity() instanceof Player && entityDamageByEntityEvent.getDamager() instanceof Snowball && ((Snowball)entityDamageByEntityEvent.getDamager()).getShooter() instanceof Player) {
            Player player = (Player)entityDamageByEntityEvent.getEntity();
            World world = player.getWorld();
            String string = this.a.getConfig().getString("Basic.World-Prefix");
            if (world.getName().startsWith(string)) {
                String string2 = world.getName().split(string)[1];
                if (this.a.G().c(string2) && !this.a.G().i(string2)) {
                    entityDamageByEntityEvent.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void a(PlayerPickupItemEvent playerPickupItemEvent) {
        Player player = playerPickupItemEvent.getPlayer();
        World world = player.getWorld();
        String string = this.a.getConfig().getString("Basic.World-Prefix");
        if (world.getName().startsWith(string)) {
            String string2 = world.getName().split(string)[1];
            if (this.a.G().c(string2) && !this.a.G().b(player, string2) && !this.a.G().k(string2)) {
                playerPickupItemEvent.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void a(PlayerDropItemEvent playerDropItemEvent) {
        Player player = playerDropItemEvent.getPlayer();
        World world = player.getWorld();
        String string = this.a.getConfig().getString("Basic.World-Prefix");
        if (world.getName().startsWith(string)) {
            String string2 = world.getName().split(string)[1];
            if (this.a.G().c(string2) && !this.a.G().b(player, string2) && !this.a.G().l(string2)) {
                playerDropItemEvent.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void a(EntityDamageEvent entityDamageEvent) {
        if (entityDamageEvent.getEntity() instanceof Player) {
            Player player = (Player)entityDamageEvent.getEntity();
            World world = player.getWorld();
            String string = this.a.getConfig().getString("Basic.World-Prefix");
            if (world.getName().startsWith(string)) {
                String string2 = world.getName().split(string)[1];
                if (this.a.G().c(string2) && !this.a.G().m(string2)) {
                    entityDamageEvent.setCancelled(true);
                    if (entityDamageEvent.getCause() == EntityDamageEvent.DamageCause.VOID) {
                        this.a.D().a(player, this.a.G().a(string2));
                    }
                }
            }
        }
    }

    @EventHandler
    public void a(FoodLevelChangeEvent foodLevelChangeEvent) {
        if (foodLevelChangeEvent.getEntity() instanceof Player) {
            Player player = (Player)foodLevelChangeEvent.getEntity();
            World world = player.getWorld();
            String string = this.a.getConfig().getString("Basic.World-Prefix");
            if (world.getName().startsWith(string)) {
                String string2 = world.getName().split(string)[1];
                if (this.a.G().c(string2) && !this.a.G().n(string2)) {
                    foodLevelChangeEvent.setCancelled(true);
                    if (player.getFoodLevel() < 19) {
                        player.setFoodLevel(20);
                    }
                }
            }
        }
    }

    @EventHandler
    public void a(PlayerBucketFillEvent playerBucketFillEvent) {
        Player player = playerBucketFillEvent.getPlayer();
        World world = player.getWorld();
        String string = this.a.getConfig().getString("Basic.World-Prefix");
        if (world.getName().startsWith(string)) {
            String string2 = world.getName().split(string)[1];
            if (this.a.G().c(string2) && !this.a.G().b(player, string2) && !this.a.G().o(string2)) {
                playerBucketFillEvent.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void a(PlayerBucketEmptyEvent playerBucketEmptyEvent) {
        Player player = playerBucketEmptyEvent.getPlayer();
        World world = player.getWorld();
        String string = this.a.getConfig().getString("Basic.World-Prefix");
        if (world.getName().startsWith(string)) {
            String string2 = world.getName().split(string)[1];
            if (this.a.G().c(string2) && !this.a.G().b(player, string2) && !this.a.G().o(string2)) {
                playerBucketEmptyEvent.setCancelled(true);
            }
        }
    }
}

