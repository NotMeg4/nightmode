package github.notmeg4.nightmode.utils;

import github.notmeg4.nightmode.NightMode;
import github.notmeg4.nightmode.utils.dbMan;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class timeUpdate {
    private NightMode plugin;
    FileConfiguration config;
    public timeUpdate(Player player, NightMode plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        String enpath = "mysql_db.enabled";
        if(this.config.getBoolean(enpath)){
            if (dbMan.getNightMode(this.plugin.getMySQL(), player.getName())) {
                player.setPlayerTime(18000L, false);
            } else {
                player.setPlayerTime(6000L, false);
            }
        } else {
            String nmppath = "players";
            List<String> nmPlayers = this.config.getStringList(nmppath);
            if(nmPlayers.contains(player.getName())) {
                player.setPlayerTime(18000L, false);
            } else {
                player.setPlayerTime(6000L, false);
            }
        }
    }
}
