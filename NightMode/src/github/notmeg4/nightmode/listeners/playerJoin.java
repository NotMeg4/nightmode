package github.notmeg4.nightmode.listeners;

import github.notmeg4.nightmode.NightMode;
import github.notmeg4.nightmode.utils.dbMan;
import github.notmeg4.nightmode.utils.timeUpdate;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class playerJoin implements Listener {
    private NightMode plugin;

    FileConfiguration config;
    public playerJoin(NightMode plugin) {

        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    @EventHandler
    public void onplayerjoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String path = "mysql_db.enabled";
        if(config.getBoolean(path)) {
            if (!dbMan.playerExist(this.plugin.getMySQL(), p.getName())) {
                dbMan.createPlayer(this.plugin.getMySQL(), p.getName());
            }
        }
        new timeUpdate(e.getPlayer(), plugin);
    }
}
