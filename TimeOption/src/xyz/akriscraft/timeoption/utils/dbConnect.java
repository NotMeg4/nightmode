package xyz.akriscraft.timeoption.utils;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import java.sql.*;

public class dbConnect {
    private Connection connection;
    private String host;
    private int port;
    private String pass;
    private String user;
    private String database;

    public dbConnect(String host, int port, String pass, String user, String database) {
        this.host = host;
        this.port = port;
        this.pass = pass;
        this.user = user;
        this.database = database;
        try {
            synchronized (this) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&5Connecting to the database..."));
                if(connection != null && !connection.isClosed()){
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&4There was an error connecting to the database"));
                    return;
                }
                Class.forName("com.mysql.jdbc.Driver");
                this.connection = DriverManager.getConnection("jdbc:mysql://"+this.host+":"+this.port+"/"+this.database,this.user,this.pass);
                PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `nightMode` (`playerName` varchar(255), `enabled` boolean)");
                statement.executeUpdate();
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aConnected to the database"));
            }

        }catch (SQLException e) {

        }catch (ClassNotFoundException e){

        }

    }

    public Connection getConnection() {
        return connection;
    }
}
