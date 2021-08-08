package nl.jasperjakobs.pluginjam.listener;

import nl.jasperjakobs.pluginjam.Pluginjam;
import nl.jasperjakobs.pluginjam.oxygen.OxygenData;
import nl.jasperjakobs.pluginjam.player.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    private final Pluginjam plugin;

    public PlayerRespawnListener(Pluginjam plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRespawn (PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        PlayerData playerData = this.plugin.getPlayerDataManager().getPlayerData(player.getUniqueId());

        if (playerData.getCurrentInheritedEntity().revertDrown()) {
            OxygenData oxygenData = this.plugin.getOxygenDataManager().getOxygenData(player.getUniqueId());
            oxygenData.resetOxygenAmount();
        }
    }
}
