package uwu.voidtospawn;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.plugin.java.JavaPlugin;

public final class Voidtospawn extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        // Check if entity is a player and damage cause is falling
        if (event.getEntity() instanceof Player && event.getCause() == DamageCause.VOID) {
            Player player = (Player) event.getEntity();
            // Get world and teleport player to surface
            World world = player.getWorld();
            Location spawnLocation = world.getSpawnLocation();
            player.teleport(spawnLocation);
            // Cancel the damage event to prevent player from dying
            event.setCancelled(true);
        }
    }

}

