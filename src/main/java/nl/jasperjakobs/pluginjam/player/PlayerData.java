package nl.jasperjakobs.pluginjam.player;

import nl.jasperjakobs.pluginjam.Pluginjam;
import nl.jasperjakobs.pluginjam.mob.MobTypes;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class PlayerData {

    private final Pluginjam plugin;

    private final Player player;

    private MobTypes currentInheritedEntity = MobTypes.PLAYER;
    private BossBar bossBar = Bukkit.createBossBar("§7" + this.currentInheritedEntity.getEntityNameString(), BarColor.YELLOW, BarStyle.SOLID);

    public PlayerData(Pluginjam plugin, Player player) {
        this.plugin = plugin;
        this.player = player;

        this.bossBar.addPlayer(player);
    }

    public Player getPlayer() {
        return player;
    }

    public MobTypes getCurrentInheritedEntity() {
        return currentInheritedEntity;
    }

    public void setCurrentInheritedEntity(MobTypes currentInheritedEntity) {
        this.currentInheritedEntity = currentInheritedEntity;

        this.bossBar.setTitle("§7" + currentInheritedEntity.getEntityNameString());
    }

    public void resetCurrentInheritedEntity() {
        this.currentInheritedEntity = MobTypes.PLAYER;

        this.bossBar.setTitle("§7" + MobTypes.PLAYER.getEntityNameString());
    }

    public void removeBossBar() {
        this.bossBar.removePlayer(this.player);
    }
}
