package xyz.akriscraft.timeoption.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;
import xyz.akriscraft.timeoption.TimeOption;
import xyz.akriscraft.timeoption.utils.dbMan;
import xyz.akriscraft.timeoption.utils.timeUpdate;

public class toggleCMD implements CommandExecutor {

    private TimeOption plugin;

    public toggleCMD(TimeOption plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
    }
    FileConfiguration config;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            String message = "lang.onlyPlayers";
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(message)));
            return true;
        }
        Player player = (Player) sender;

        String toggleMessage = "lang.toggled";
        if(dbMan.getNightMode(plugin.getMySQL(), player.getName())){
            //Disable
            dbMan.setNightMode(plugin.getMySQL(), player.getName(), false);
            String disabled = "lang.disabled";
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString(toggleMessage) + " " + config.getString(disabled)));
            new timeUpdate(player, plugin);
            return true;
        } else {
            dbMan.setNightMode(plugin.getMySQL(), player.getName(), true);
            String enabled = "lang.enabled";
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(toggleMessage) + " " + config.getString(enabled)));
            new timeUpdate(player, plugin);
            return true;
        }
    }
}