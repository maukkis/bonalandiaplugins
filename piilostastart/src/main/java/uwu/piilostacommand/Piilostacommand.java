package uwu.piilostacommand;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.block.Block;



public final class Piilostacommand extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("piilostacommand has started");
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //kattoo tekeekö joku /start komennon ja onko se op
        if (cmd.getName().equalsIgnoreCase("start") && sender instanceof Player && ((Player) sender).isOp()) {
            World world = ((Player) sender).getWorld();
            if (!world.getName().equals("Piilosta2")) {
                sender.sendMessage(ChatColor.RED + "You can only use /start in the Piilosta2 world!");
                return true;
            }
            // vetää countdownin kaikille pelaajille
            new BukkitRunnable() {
                int count = 3;
                @Override
                public void run() {
                    if (count > 0) {
                        for (Player p : world.getPlayers()) {
                            String color = "";
                            if (count == 3) {
                                color = ChatColor.GREEN.toString();
                            } else if (count == 2) {
                                color = ChatColor.YELLOW.toString();
                            } else if (count == 1) {
                                color = ChatColor.RED.toString();
                            }
                            p.sendTitle("", color + Integer.toString(count), 0, 20, 0);
                        }
                        count--;
                    } else {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.sendTitle("", ChatColor.GREEN + "piiloutukaa", 0, 20, 10);
                        }
                        // fill the specified area with air blocks
                        Location corner1 = new Location(world, -41, 173, -10);
                        Location corner2 = new Location(world, -21, 173, 10);
                        fillWithAir(corner1, corner2);
                        cancel();


                       // antaa kaikille pelaajille paitsi bonanzafinille ticketin
                        for (Player p : world.getPlayers()) {
                            if (!p.getName().equals("bonanzafin")) {
                                ItemStack ticket = new ItemStack(Material.PAPER);
                                ItemMeta meta = ticket.getItemMeta();
                                meta.setDisplayName("Ticket");
                                ticket.setItemMeta(meta);
                                p.getInventory().addItem(ticket);
                            }
                        }
                        cancel();
                    }
                }
            }.runTaskTimer(this, 0L, 20L);
            return true;
        }
        return false;
    }
    // Fill the specified area with air
    public void fillWithAir(Location loc1, Location loc2) {
        int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
        int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
        int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
        int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
        int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
        int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Block block = loc1.getWorld().getBlockAt(x, y, z);
                    block.setType(Material.AIR);
                }
            }
        }
    }
    // antaa pelaajille ticketin jos ne on missannu start komennon
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        // Only give the player a ticket if they are in the world named "Piilosta2" and their name is not "bonanzafin"
        if (world.getName().equals("Piilosta2") && !player.getName().equals("bonanzafin")) {
            ItemStack ticket = new ItemStack(Material.PAPER);
            ItemMeta meta = ticket.getItemMeta();
            meta.setDisplayName("Ticket");
            ticket.setItemMeta(meta);
            player.getInventory().addItem(ticket);
        }
    }
}