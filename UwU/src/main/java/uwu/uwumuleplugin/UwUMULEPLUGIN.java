package uwu.uwumuleplugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class UwUMULEPLUGIN extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println("uhkapelaus plugin on startattu");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_BLOCK && item != null && item.getType() == Material.PAPER && item.getItemMeta().getDisplayName().equals("Ticket")) {
            if (event.getClickedBlock().getType() == Material.GOLD_BLOCK) {
                if (player.getDisplayName().equals("bonanzafin")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 20, 1));
                } else {
                    int random = (int) (Math.random() * 2);
                    if (random == 0) {
                        player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
                    } else {
                        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                            if (!onlinePlayer.getDisplayName().equals("bonanzafin")) {
                                onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 20, 1));
                            }
                        }
                    }
                }
                int amount = item.getAmount();
                if (amount == 1) {
                    player.getInventory().remove(item);
                } else {
                    item.setAmount(amount - 1);
                    player.getInventory().setItemInMainHand(item);
                }
            }
        }
    }
}
