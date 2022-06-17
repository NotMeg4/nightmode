package xyz.akriscraft.timeoption.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;
import xyz.akriscraft.timeoption.TimeOption;

import static java.lang.String.format;

public class toggleCMD implements CommandExecutor {

    private TimeOption plugin;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        FileConfiguration config = plugin.getConfig();
        if (!(sender instanceof Player)) {
            String message = "lang.onlyPlayers";
            sender.sendMessage(config.getString(message));
            return true;
        }
        Player player = (Player) sender;
        switch (args[0].toLowerCase()){
            case "day":
                player.setPlayerTime(6000, false);
                player.sendMessage("§cHas establecido tu tiempo a Dia");
                break;
            case "night":
                player.sendMessage("§cHas establecido tu tiempo a Noche");
                player.setPlayerTime(18000, false);
                break;
            default:
                player.sendMessage("Uso correcto: /changetime <day, night>");
                break;
        }
        return true;
    }

}
