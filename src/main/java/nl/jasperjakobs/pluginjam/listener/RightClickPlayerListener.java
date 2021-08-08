package nl.jasperjakobs.pluginjam.listener;

import nl.jasperjakobs.pluginjam.Pluginjam;
import nl.jasperjakobs.pluginjam.player.PlayerData;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class RightClickPlayerListener implements Listener {

    private final Pluginjam plugin;

    public RightClickPlayerListener (Pluginjam plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightClick (PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        Entity targetEntity = event.getRightClicked();

        if (!(targetEntity instanceof Player)) return;

        if (player.getInventory().getItemInMainHand().getType() != Material.BUCKET) return;

        Player targetPlayer = (Player) targetEntity;
        PlayerData targetPlayerData = this.plugin.getPlayerDataManager().getPlayerData(targetPlayer.getUniqueId());

        if (!targetPlayerData.getCurrentInheritedEntity().milkable()) return;

        player.getInventory().setItemInMainHand(new ItemStack(Material.MILK_BUCKET, 1));
    }
}
