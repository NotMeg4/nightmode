package github.notmeg4.nightmode.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;
import github.notmeg4.nightmode.NightMode;

public class reloadCMD implements CommandExecutor {

    private NightMode plugin;

    public reloadCMD(NightMode plugin) {
        this.plugin = plugin;
    }
    FileConfiguration config;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        config = plugin.getConfig();
        String prefix = "lang.prefix";
        if (!(sender instanceof Player)) {
            Bukkit.getPluginManager().disablePlugin(plugin);
            Bukkit.getPluginManager().getPlugin(plugin.getName()).reloadConfig();
            Bukkit.getPluginManager().enablePlugin(plugin);
            String message = "lang.configReloaded";
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(message).replace("{prefix}", config.getString(prefix))));
            return true;
        }
        Player player = (Player) sender;
        if(!(player.hasPermission("nightmode.reload"))){
            String noperm = "lang.noPermission";
            player.sendMessage(config.getString(noperm).replace("{prefix}", config.getString(prefix)));
        } else {
            Bukkit.getPluginManager().disablePlugin(plugin);
            Bukkit.getPluginManager().getPlugin(plugin.getName()).reloadConfig();
            Bukkit.getPluginManager().enablePlugin(plugin);
            String message = "lang.configReloaded";
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(message).replace("{prefix}", config.getString(prefix))));
        }
        return true;
    }
}