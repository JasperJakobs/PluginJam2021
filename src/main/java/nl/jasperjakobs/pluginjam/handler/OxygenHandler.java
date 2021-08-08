package nl.jasperjakobs.pluginjam.handler;

import nl.jasperjakobs.pluginjam.Pluginjam;
import nl.jasperjakobs.pluginjam.oxygen.OxygenData;
import nl.jasperjakobs.pluginjam.player.PlayerData;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class OxygenHandler {

    private final Pluginjam plugin;

    public OxygenHandler (Pluginjam plugin) {
        this.plugin = plugin;
    }

    public void start() {
        this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            int tick = 0;
            @Override
            public void run() {
                tick += 1;
                for (World world : Bukkit.getWorlds()) {
                    for (Player player : world.getPlayers()) {
                        waterBreather(player, tick);
                    }
                }
            }
        }, 0L, 1L);
    }

    private Boolean isWaterSource(Block block) {
        if(block.getType() == Material.WATER) {
            BlockData blockData = block.getBlockData();
            if(blockData instanceof Levelled){
                Levelled lv = (Levelled)blockData;
                if(lv.getLevel() == lv.getMaximumLevel()) {
                    return true;
                }
            }
        }
        return false;
    }


    private Boolean inWaterColumn(Player player) {
        Location location = player.getEyeLocation();
        if(isWaterSource(location.getBlock())) {
            while(location.getBlockY()>0) {
                location.add(0, -1, 0);
                if(location.getBlock().getType().equals(Material.SOUL_SAND)) {
                    return true;
                }
                if(!isWaterSource(location.getBlock())) {
                    return false;
                }
            }
        }
        return false;
    }

    private void waterBreather(Player player, int tick) {
        if(!player.getGameMode().equals(GameMode.CREATIVE) && !player.getGameMode().equals(GameMode.SPECTATOR)) {
            PlayerData playerData = this.plugin.getPlayerDataManager().getPlayerData(player.getUniqueId());
            OxygenData oxygenData = this.plugin.getOxygenDataManager().getOxygenData(player.getUniqueId());

            // Only applies to a few mob types
            if (!playerData.getCurrentInheritedEntity().revertDrown()) return;

            if(oxygenData == null) {
                this.plugin.getOxygenDataManager().addOnlinePlayer(player.getUniqueId(), new OxygenData(this.plugin, player));
                oxygenData = this.plugin.getOxygenDataManager().getOxygenData(player.getUniqueId());
            }

            //When the player is no longer submerged in water or is in a bubble column
            if(!player.getEyeLocation().getBlock().getType().equals(Material.WATER) || inWaterColumn(player)) {
                //the air supply value decreases each tick.
                if(!player.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
                    oxygenData.removeOxygen(1);
                }
                //Respiration gives a chance for air supply to not decrease itself per tick.
                //Chance is x/(x + 1), where x is the level of enchantment.
                if(player.getInventory().getHelmet()!=null) {
                    ItemStack helmet = player.getInventory().getHelmet();
                    if(helmet.getEnchantments().containsKey(Enchantment.OXYGEN)) {
                        int respirationLevel = helmet.getEnchantments().get(Enchantment.OXYGEN);
                        if(Math.random()>(respirationLevel/(respirationLevel+1))) {
                            oxygenData.addOxygen(1);
                        }
                    }
                }
                //when the air supply value reaches -20.
                if(oxygenData.getOxygenAmount() <= -20) {
                    //2 Damage is taken
                    player.damage(2);
                    //Air resets to 0 after damaging.
                    oxygenData.setOxygenAmount(0);
                }
            } else {
                //every 0.2 seconds (4 game ticks)
                if(tick % 4 == 0) {
                    //their oxygen regenerates by 1 bubble (30 air)
                    oxygenData.addOxygen(30);
                    if(oxygenData.getOxygenAmount() > 300) {
                        oxygenData.resetOxygenAmount();
                    }
                }
            };
            player.setRemainingAir(oxygenData.getOxygenAmount());
        }
    }
}
