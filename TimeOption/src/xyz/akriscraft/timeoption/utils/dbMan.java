package xyz.akriscraft.timeoption.utils;
import java.sql.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class dbMan {

    public static boolean playerExist(Connection connection, String player) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM nightMode WHERE (playerName=?)");
            statement.setString(1, player);
            ResultSet result = statement.executeQuery();

            if(result.next()) {
                return true;
            }
        }catch (SQLException e) {

        }
        return false;
    }
    public static void createPlayer(Connection connection, String player){
        try {
            if(!playerExist(connection,player)) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO nightMode VALUE (?,?)");
                statement.setString(1, player);
                statement.setBoolean(2, false);
                statement.executeUpdate();
            }
        }catch (SQLException e) {

        }
    }

    public static boolean getNightMode(Connection connection, Player player) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM nightMode WHERE (playerName=?)");
            statement.setString(1, player.toString());
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                boolean nightMode = result.getBoolean("enabled");
                return nightMode;
            }
        } catch (SQLException e) {

        }
        return false;
    }
    public static void setNightMode(Connection connection, Player player, boolean enabled) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE nightMode SET enabled=? WHERE (playerName=?)");
            statement.setBoolean(1, enabled);
            statement.setString(2, player.toString());
            statement.executeUpdate();

        }catch (SQLException e) {

        }
    }
}
