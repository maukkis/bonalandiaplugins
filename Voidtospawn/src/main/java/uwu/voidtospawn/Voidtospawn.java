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

public final class Voidtospawn extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("voidtospawn has started");
        getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        System.out.println("voidtospawn has stopped");
    }


    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getCause() == DamageCause.VOID) {
            Player player = (Player) event.getEntity();
            World world = player.getWorld();
            Location spawnLocation = world.getSpawnLocation();
            player.teleport(spawnLocation);
            event.setCancelled(true);
        }
    }

}

