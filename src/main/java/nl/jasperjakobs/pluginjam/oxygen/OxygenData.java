package nl.jasperjakobs.pluginjam.oxygen;

import nl.jasperjakobs.pluginjam.Pluginjam;
import org.bukkit.entity.Player;

public class OxygenData {

    private final Pluginjam plugin;

    private final Player player;

    private int oxygenAmount = 300;

    public OxygenData(Pluginjam plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getOxygenAmount() {
        return oxygenAmount;
    }

    public void setOxygenAmount(int oxygenAmount) {
        this.oxygenAmount = oxygenAmount;
    }

    public void removeOxygen(int amount) {
        this.oxygenAmount -= amount;
    }

    public void addOxygen(int amount) {
        this.oxygenAmount += amount;
    }

    public void resetOxygenAmount() {
        this.oxygenAmount = 300;
    }
}
