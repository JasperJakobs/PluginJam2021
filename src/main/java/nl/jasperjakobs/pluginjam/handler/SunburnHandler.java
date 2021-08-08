package nl.jasperjakobs.pluginjam.handler;

import nl.jasperjakobs.pluginjam.Pluginjam;
import nl.jasperjakobs.pluginjam.player.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SunburnHandler {

    private final Pluginjam plugin;

    public SunburnHandler (Pluginjam plugin) {
        this.plugin = plugin;
    }

    public void start() {
        this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    if (world.getTime() < 13000) {
                        for (Player player : world.getPlayers()) {
                            sunburn(player);
                        }
                    }

                }
            }
        }, 0L, 21L);
    }

    private void sunburn(Player player) {
        if (!player.getGameMode().equals(GameMode.CREATIVE) && !player.getGameMode().equals(GameMode.SPECTATOR)) {
            PlayerData playerData = this.plugin.getPlayerDataManager().getPlayerData(player.getUniqueId());

            // Only applies to a few mob types.
            if (!playerData.getCurrentInheritedEntity().shouldBurn()) return;

            // A helmet prevents sunburn
            if (player.getInventory().getHelmet() != null) return;

            // If player is underneath a block it prevents sunburn
            if (player.getLocation().getBlock().getLightFromSky() == 15) {
                player.setFireTicks(25);
            } else {
                player.setFireTicks(0);
            }
        }
    }

}
