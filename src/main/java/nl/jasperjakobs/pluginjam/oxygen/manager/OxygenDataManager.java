package nl.jasperjakobs.pluginjam.oxygen.manager;

import nl.jasperjakobs.pluginjam.Pluginjam;
import nl.jasperjakobs.pluginjam.oxygen.OxygenData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class OxygenDataManager {

    private final Pluginjam plugin;

    private HashMap<UUID, OxygenData> onlinePlayers;

    public OxygenDataManager(Pluginjam plugin) {
        this.plugin = plugin;

        onlinePlayers = new HashMap<>();
    }

    public OxygenData getOxygenData (UUID uuid) {
        return this.onlinePlayers.get(uuid);
    }

    public boolean isOnline(UUID uuid) {
        return this.onlinePlayers.containsKey(uuid);
    }

    public void addOnlinePlayer(UUID uuid, OxygenData oxygenData) {
        this.onlinePlayers.put(uuid, oxygenData);
    }

    public void onPlayerQuit(Player player) {
        UUID uuid = player.getUniqueId();
        if (this.isOnline(uuid)) {
            this.onlinePlayers.remove(uuid);
        }
    }
}
