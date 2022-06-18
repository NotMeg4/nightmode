package xyz.akriscraft.timeoption.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;
import xyz.akriscraft.timeoption.TimeOption;

public class reloadCMD implements CommandExecutor {

    private TimeOption plugin;

    public reloadCMD(TimeOption plugin) {
        this.plugin = plugin;
    }
    FileConfiguration config;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            plugin.reloadConfig();
            String message = "lang.configReloaded";
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(message)));
            return true;
        }
        Player player = (Player) sender;
        if(!(player.hasPermission("nightmode.reload"))){
            String noperm = "lang.noPermission";
            player.sendMessage(config.getString(noperm));
        } else {
            plugin.reloadConfig();
            String message = "lang.configReloaded";
            player.sendMessage(config.getString(message));
        }
        return true;
    }
}