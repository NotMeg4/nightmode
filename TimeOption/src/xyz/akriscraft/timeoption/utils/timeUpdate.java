package xyz.akriscraft.timeoption.utils;

import org.bukkit.entity.Player;
import xyz.akriscraft.timeoption.TimeOption;


public class timeUpdate {
    private Player p;
    private TimeOption plugin;
    public timeUpdate(Player p, TimeOption plugin){
        this.plugin = plugin;
        if(dbMan.getNightMode(this.plugin.getMySQL(), p.getName())){
            p.setPlayerTime(18000, false);
        } else {
            p.setPlayerTime(6000, false);
        }
    }
}
