package github.notmeg4.nightmode.utils;

import org.bukkit.entity.Player;
import github.notmeg4.nightmode.NightMode;


public class timeUpdate {
    private Player p;
    private NightMode plugin;
    public timeUpdate(Player p, NightMode plugin){
        this.plugin = plugin;
        if(dbMan.getNightMode(this.plugin.getMySQL(), p.getName())){
            p.setPlayerTime(18000, false);
        } else {
            p.setPlayerTime(6000, false);
        }
    }
}
