package github.notmeg4.nightmode.commands;

import github.notmeg4.nightmode.NightMode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class reloadCMD implements CommandExecutor {
    private NightMode plugin;

    FileConfiguration config;

    public reloadCMD(NightMode plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        this.config = this.plugin.getConfig();
        String prefix = "lang.prefix";
        if (!(sender instanceof Player)) {
            Bukkit.getPluginManager().disablePlugin((Plugin)this.plugin);
            Bukkit.getPluginManager().getPlugin(this.plugin.getName()).reloadConfig();
            Bukkit.getPluginManager().enablePlugin((Plugin)this.plugin);
            String message = "lang.configReloaded";
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', this.config.getString(message).replace("{prefix}", this.config.getString(prefix))));
            return true;
        }
        Player player = (Player)sender;
        if (!player.hasPermission("nightmode.reload")) {
            String noperm = "lang.noPermission";
            player.sendMessage(this.config.getString(noperm).replace("{prefix}", this.config.getString(prefix)));
        } else {
            Bukkit.getPluginManager().disablePlugin((Plugin)this.plugin);
            Bukkit.getPluginManager().getPlugin(this.plugin.getName()).reloadConfig();
            Bukkit.getPluginManager().enablePlugin((Plugin)this.plugin);
            String message = "lang.configReloaded";
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.config.getString(message).replace("{prefix}", this.config.getString(prefix))));
        }
        return true;
    }
}
