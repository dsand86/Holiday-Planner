package com.andreiciubotaru.holidayplanner.controller.DataLayer;

import com.andreiciubotaru.holidayplanner.model.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Simulations {
    public static TreeMap<Integer, String> getSimulationsByUserId(int userId) {
        TreeMap<Integer, String> simulations = new TreeMap<>();
        Connection connection = Database.getConnection();

        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT SimulationID, SimulationDates FROM Simulations WHERE UserID = ?;");
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    simulations.put(rs.getInt("SimulationID"), rs.getString("SimulationDates"));
                }
                ps.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("Couldn't retrieve user's simulations!\nReason: " + e.getMessage());
            }
        } else {
            System.out.println("Connection to database was not established!");
        }
        return simulations;
    }

    public static TreeSet<String> getSimulationsDatesByUserIdSimulationId(int userId, int simulationId) {
        TreeMap<Integer, String> simulations = getSimulationsByUserId(userId);
        Connection connection = Database.getConnection();
        TreeSet<String> ts = new TreeSet<>();
        try {
            StringTokenizer st = new StringTokenizer(simulations.get(simulationId), ", ");
            while (st.hasMoreTokens()) {
                ts.add(st.nextToken());
            }
        } catch (NullPointerException e) {
            System.out.println("Couldn't retrieve user specific simulation!\nReason: " + e.getMessage());
        }
        return ts;
    }

    public static void addNewSimulationDates(int UserId, TreeSet<String> newSimulationsDates) {
        Connection connection = Database.getConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO Simulations (UserID, SimulationDates) VALUES (?, ?);");
                ps.setInt(1, UserId);
                ps.setString(2, newSimulationsDates.toString().substring(1, newSimulationsDates.toString().length() - 1));
                ps.execute();
                ps.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("Couldn't add new simulation dates!\nReason: " + e.getMessage());
            }
        } else {
            System.out.println("Connection to database was not established!");
        }
    }

    public static void updateSimulationDates(int UserId, int SimulationId, TreeSet<String> newSimulationsDates) {
        Connection connection = Database.getConnection();
        if (connection != null) {
            try {
                TreeSet<String> ts = getSimulationsDatesByUserIdSimulationId(UserId, SimulationId);
                for (String item : newSimulationsDates) {
                    ts.add(item);
                }
                PreparedStatement ps = connection.prepareStatement("UPDATE Simulations SET SimulationDates = ? \n" +
                        "WHERE SimulationID = ? AND UserId = ?;");
                ps.setString(1, ts.toString().substring(1, ts.toString().length() - 1));
                ps.setInt(2, SimulationId);
                ps.setInt(3, UserId);
                ps.execute();
                ps.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("Couldn't update simulation dates!\nReason: " + e.getMessage());
            }
        } else {
            System.out.println("Connection to database was not established!");
        }
    }
}

