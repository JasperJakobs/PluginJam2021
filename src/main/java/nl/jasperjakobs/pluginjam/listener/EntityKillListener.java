package nl.jasperjakobs.pluginjam.listener;

import nl.jasperjakobs.pluginjam.Pluginjam;
import nl.jasperjakobs.pluginjam.mob.MobTypes;
import nl.jasperjakobs.pluginjam.player.PlayerData;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityKillListener implements Listener {

    private Pluginjam plugin;

    public EntityKillListener (Pluginjam plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityKill(EntityDeathEvent event) {
        Player player = event.getEntity().getKiller();
        Entity killedEntity = event.getEntity();
        MobTypes mobType;

        if (player == null) return;

        player.sendMessage(event.getEntity().getName());

        try {
            mobType = MobTypes.valueOf(killedEntity.getName().toUpperCase().replace(' ', '_'));
        } catch (IllegalArgumentException exception) {
            return;
        }

        player.sendMessage("§7You killed a §6" + event.getEntity().getName() + "§7. You have inherited all its abilities!");

        PlayerData playerData = this.plugin.getPlayerDataManager().getPlayerData(player.getUniqueId());

        playerData.setCurrentInheritedEntity(mobType);
    }
}
