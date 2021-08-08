package nl.jasperjakobs.pluginjam.handler;

import nl.jasperjakobs.pluginjam.Pluginjam;
import nl.jasperjakobs.pluginjam.mob.MobTypes;
import nl.jasperjakobs.pluginjam.player.PlayerData;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EffectsHandler {

    private final Pluginjam plugin;

    public EffectsHandler (Pluginjam plugin) {
        this.plugin = plugin;
    }

    public void start() {
        this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    if (world.getTime() < 13000) {
                        for (Player player : world.getPlayers()) {
                            PlayerData playerData = plugin.getPlayerDataManager().getPlayerData(player.getUniqueId());
                            speedEffect(player, playerData);
                            flight(player, playerData);
                            waterDamage(player, playerData);
                            swimEffect(player, playerData);
                            jumpEffect(player, playerData);
                        }
                    }

                }
            }
        }, 0L, 10L);
    }

    private void swimEffect(Player player, PlayerData playerData) {
        if (playerData.getCurrentInheritedEntity().shouldSwimFast()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 20, 1));
        }
    }

    private void jumpEffect(Player player, PlayerData playerData) {
        if (playerData.getCurrentInheritedEntity().shouldJumpHigher()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, 2));
        }
    }

    private void speedEffect(Player player, PlayerData playerData) {
        if (playerData.getCurrentInheritedEntity().shouldWalkFast()) {
            player.setWalkSpeed(0.3f);
        } else if (playerData.getCurrentInheritedEntity().shouldWalkSlow()){
            player.setWalkSpeed(0.1f);
        } else player.setWalkSpeed(0.2f);
    }

    private void flight(Player player, PlayerData playerData) {
        if (!player.getGameMode().equals(GameMode.CREATIVE) && !player.getGameMode().equals(GameMode.SPECTATOR)) {
            player.setAllowFlight(playerData.getCurrentInheritedEntity().canFly());
        }
    }

    private void waterDamage(Player player, PlayerData playerData) {
        if (!player.getGameMode().equals(GameMode.CREATIVE) && !player.getGameMode().equals(GameMode.SPECTATOR)) {

            if (playerData.getCurrentInheritedEntity().equals(MobTypes.ENDERMAN)) {
                Location location = player.getLocation();

                if (location.getBlock().getType() == Material.WATER) {
                    player.damage(2);
                }
            }
        }
    }
}
