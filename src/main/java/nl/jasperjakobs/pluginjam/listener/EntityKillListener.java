package nl.jasperjakobs.pluginjam.listener;

import net.minecraft.network.protocol.game.PacketPlayOutEntityStatus;
import net.minecraft.server.level.EntityPlayer;
import nl.jasperjakobs.pluginjam.Pluginjam;
import nl.jasperjakobs.pluginjam.mob.MobTypes;
import nl.jasperjakobs.pluginjam.player.PlayerData;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

        try {
            mobType = MobTypes.valueOf(killedEntity.getName().toUpperCase().replace(' ', '_'));
        } catch (IllegalArgumentException exception) {
            return;
        }

        player.sendMessage("§7You killed a §6" + event.getEntity().getName() + "§7. You have inherited all its abilities!");

        ItemStack oldOffHandItem = player.getInventory().getItemInOffHand();

        ItemStack icon = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta iconMeta = icon.getItemMeta();
        iconMeta.setDisplayName("§f");
        iconMeta.setCustomModelData(1);
        icon.setItemMeta(iconMeta);

        player.getInventory().setItemInOffHand(icon);
        player.sendTitle("§a§l" + mobType.getEntityNameString(), mobType.getAbilities(), 20, 60, 20);

        EntityPlayer ep = ((CraftPlayer)player).getHandle();
        PacketPlayOutEntityStatus status = new PacketPlayOutEntityStatus(ep, (byte) 35);
        ep.b.sendPacket(status);

        player.getInventory().setItemInOffHand(oldOffHandItem);

        PlayerData playerData = this.plugin.getPlayerDataManager().getPlayerData(player.getUniqueId());

        playerData.setCurrentInheritedEntity(mobType);
    }
}
