package nl.jasperjakobs.pluginjam.listener;

import nl.jasperjakobs.pluginjam.Pluginjam;
import nl.jasperjakobs.pluginjam.mob.MobTypes;
import nl.jasperjakobs.pluginjam.player.PlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class OffHandSlotSwitchListener implements Listener {

    private final Pluginjam plugin;

    public OffHandSlotSwitchListener (Pluginjam plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSwitch(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = this.plugin.getPlayerDataManager().getPlayerData(player.getUniqueId());
        Location location = player.getLocation();

        if (playerData.getCurrentInheritedEntity().equals(MobTypes.CREEPER)) {


            player.getWorld().createExplosion(location, 3, false, true);
        }

        if (playerData.getCurrentInheritedEntity().equals(MobTypes.ENDERMAN)) {
            Location targetLocation = player.getTargetBlock(null, 32).getLocation();

            targetLocation.setPitch(location.getPitch());
            targetLocation.setYaw(location.getYaw());

            player.teleport(targetLocation);
        }

        event.setCancelled(true);
    }
}
