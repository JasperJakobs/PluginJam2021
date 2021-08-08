package nl.jasperjakobs.pluginjam.player.manager;

import nl.jasperjakobs.pluginjam.Pluginjam;
import nl.jasperjakobs.pluginjam.player.PlayerData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataManager {

    private final Pluginjam plugin;

    private HashMap<UUID, PlayerData> onlinePlayers;

    public PlayerDataManager(Pluginjam plugin) {
        this.plugin = plugin;

        onlinePlayers = new HashMap<>();
    }

    public PlayerData getPlayerData(UUID uuid) {
        return this.onlinePlayers.get(uuid);
    }

    public boolean isOnline(UUID uuid) {
        return this.onlinePlayers.containsKey(uuid);
    }

    public void addOnlinePlayer(UUID uuid, PlayerData playerData) {
        this.onlinePlayers.put(uuid, playerData);
    }

    public void onPlayerQuit(Player player) {
        UUID uuid = player.getUniqueId();
        if (this.isOnline(uuid)) {
            this.onlinePlayers.remove(uuid);
        }
    }
}