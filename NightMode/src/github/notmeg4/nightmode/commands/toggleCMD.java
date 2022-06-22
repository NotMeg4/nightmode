package github.notmeg4.nightmode.commands;

import github.notmeg4.nightmode.NightMode;
import github.notmeg4.nightmode.utils.dbMan;
import github.notmeg4.nightmode.utils.timeUpdate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class toggleCMD implements CommandExecutor {
    private NightMode plugin;

    FileConfiguration config;

    public toggleCMD(NightMode plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        String prefix = "lang.prefix";
        if (!(sender instanceof Player)) {
            String onlyP = "lang.onlyPlayers";
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', this.config.getString(onlyP).replace("{prefix}", this.config.getString(prefix))));
            return true;
        }
        Player player = (Player)sender;
        String mysqlEnabled = "mysql_db.enabled";
        String toggleMessage = "lang.toggle";
        if (config.getBoolean(mysqlEnabled)) {
            if (dbMan.getNightMode(this.plugin.getMySQL(), player.getName())) {
                dbMan.setNightMode(this.plugin.getMySQL(), player.getName(), false);
                String disabled = "lang.disabled";
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.config.getString(toggleMessage).replace("{prefix}", this.config.getString(prefix)).replace("{action}", this.config.getString(disabled))));
            } else {
                dbMan.setNightMode(this.plugin.getMySQL(), player.getName(), true);
                String enabled = "lang.enabled";
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.config.getString(toggleMessage).replace("{prefix}", this.config.getString(prefix)).replace("{action}", this.config.getString(enabled))));
            }
        } else {
            String nmppath = "players";
            List<String> nmPlayers = this.config.getStringList(nmppath);
            if (nmPlayers.contains(player.getName())) {
                String disabled = "lang.disabled";
                nmPlayers.remove(player.getName());
                config.set(nmppath, nmPlayers);
                plugin.saveConfig();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.config.getString(toggleMessage).replace("{prefix}", this.config.getString(prefix)).replace("{action}", this.config.getString(disabled))));

            } else {
                String enabled = "lang.enabled";
                nmPlayers.add(player.getName());
                config.set(nmppath, nmPlayers);
                plugin.saveConfig();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.config.getString(toggleMessage).replace("{prefix}", this.config.getString(prefix)).replace("{action}", this.config.getString(enabled))));
            }
        }
        new timeUpdate(player, this.plugin);
        return true;
    }
}
