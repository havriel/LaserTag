package com.example.demo;

import java.sql.*;

public class DataBaseHandler extends Configs {
    public Connection dbConnection;

    public Connection getDbConnection() throws SQLException, ClassNotFoundException {
        String connectionString = "jdbc:mysql://localhost/lasertag";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException("unhandled", e);
        }
        return dbConnection;
    }

    public void addPlayer(Player player) {
        try {
            dbConnection = getDbConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String insert = "INSERT INTO players (name,command,weapon,vest,kills,deaths,place) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(insert);
            prSt.setString(1, player.getName());
            prSt.setString(2, player.getCommand());
            prSt.setInt(3, player.getWeaponNum());
            prSt.setInt(4, player.getVestNum());
            prSt.setInt(5, player.getKills());
            prSt.setInt(6, player.getDeaths());
            prSt.setInt(7, player.getPlace());
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("unhandled1", e);
        }
    }

    public void clearBase() {
        try {
            dbConnection = getDbConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String clear = "TRUNCATE TABLE players";
        try {
            Statement statement = dbConnection.createStatement();
            statement.execute(clear);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDeaths(int vest) {
        try {
            dbConnection = getDbConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String uDeath = "UPDATE players SET deaths=deaths+1 WHERE vest=" + vest;
        try {
            Statement statement = dbConnection.createStatement();
            statement.execute(uDeath);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateKills(int weapon) {
        try {
            dbConnection = getDbConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String uKills = "UPDATE players SET kills=kills+1 WHERE weapon=" + weapon;
        try {
            Statement statement = dbConnection.createStatement();
            statement.execute(uKills);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
