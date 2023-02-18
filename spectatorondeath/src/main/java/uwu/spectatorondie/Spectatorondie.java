package uwu.spectatorondie;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.Location;

public final class Spectatorondie extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("spectatorondeath has started succesfully");
        getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        System.out.println("spectatorondeath has stopped succesfully");
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Location deathLocation = event.getEntity().getLocation();
        event.getEntity().setGameMode(GameMode.SPECTATOR);
        event.getEntity().teleport(deathLocation);
        event.setKeepInventory(true);
        event.setKeepLevel(true);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SPECTATOR) {
            event.setRespawnLocation(event.getPlayer().getLocation());
            event.getPlayer().setGameMode(GameMode.SURVIVAL);
        }
    }
}
