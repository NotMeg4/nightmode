package github.notmeg4.nightmode.listeners;

import github.notmeg4.nightmode.NightMode;
import github.notmeg4.nightmode.utils.dbMan;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class placeholderRequest extends PlaceholderExpansion {
    private NightMode plugin;

    public String getIdentifier() {
        return "nightmode";
    }

    public String getAuthor() {
        return "NotMeg4_";
    }

    public String getVersion() {
        return "1.0";
    }

    public boolean canRegister() {
        return true;
    }

    public boolean persist() {
        return true;
    }

    FileConfiguration config;
    public placeholderRequest(NightMode plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public String onPlaceholderRequest(Player p, String params) {
        if (p == null)
            return "nullPlayer";
        if (params.equalsIgnoreCase("isenabled")) {
            if(config.getBoolean("mysql_db.enabled")) {
                if(dbMan.getNightMode(this.plugin.getMySQL(), p.getName())){
                    return "true";
                }
                return "false";
            } else {
                String nmppath = "players";
                List<String> nmPlayers = this.config.getStringList(nmppath);
                if(nmPlayers.contains(p.getName())) {
                    return "true";
                }
                return "false";
            }
        }
        return "null";
    }
}
