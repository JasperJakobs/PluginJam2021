package nl.jasperjakobs.pluginjam.listener;

import nl.jasperjakobs.pluginjam.Pluginjam;
import nl.jasperjakobs.pluginjam.oxygen.OxygenData;
import nl.jasperjakobs.pluginjam.player.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    private final Pluginjam plugin;

    public JoinQuitListener(Pluginjam plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        this.plugin.getPlayerDataManager().addOnlinePlayer(player.getUniqueId(), new PlayerData(this.plugin, player));
        this.plugin.getOxygenDataManager().addOnlinePlayer(player.getUniqueId(), new OxygenData(this.plugin, player));
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        this.plugin.getPlayerDataManager().onPlayerQuit(player);
        this.plugin.getOxygenDataManager().onPlayerQuit(player);
    }
}
