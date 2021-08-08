package nl.jasperjakobs.pluginjam;

import nl.jasperjakobs.pluginjam.handler.EffectsHandler;
import nl.jasperjakobs.pluginjam.handler.SunburnHandler;
import nl.jasperjakobs.pluginjam.listener.*;
import nl.jasperjakobs.pluginjam.handler.OxygenHandler;
import nl.jasperjakobs.pluginjam.oxygen.manager.OxygenDataManager;
import nl.jasperjakobs.pluginjam.player.manager.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class Pluginjam extends JavaPlugin {

    private PlayerDataManager playerDataManager;

    private OxygenDataManager oxygenDataManager;

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

    public OxygenDataManager getOxygenDataManager() {
        return oxygenDataManager;
    }

    @Override
    public void onEnable() {
        this.initializeEvents();
        this.initializeManagers();

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer("Server reloaded!");
        }

        new OxygenHandler(this).start();
        new SunburnHandler(this).start();
        new EffectsHandler(this).start();
    }

    @Override
    public void onDisable() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.execute(() -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                getServer().getPluginManager().callEvent(new PlayerQuitEvent(player, ""));
            }
        });
        scheduler.shutdown();
        Bukkit.getScheduler().cancelTasks(this);
    }

    public void initializeManagers() {
        this.playerDataManager = new PlayerDataManager(this);
        this.oxygenDataManager = new OxygenDataManager(this);
    }

    public void initializeEvents() {
        getServer().getPluginManager().registerEvents(new EntityKillListener(this), this);
        getServer().getPluginManager().registerEvents(new JoinQuitListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(this), this);
        getServer().getPluginManager().registerEvents(new OffHandSlotSwitchListener(this), this);
        getServer().getPluginManager().registerEvents(new RightClickPlayerListener(this), this);
    }
}
