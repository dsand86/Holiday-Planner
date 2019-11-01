package com.andreiciubotaru.holidayplanner.controller.DataLayer;

import com.andreiciubotaru.holidayplanner.model.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.TreeMap;

public class Regions {
    public static TreeMap<Integer, String> getRegionsByCountryId(int countryId) {
        TreeMap<Integer, String> regions = new TreeMap<>();
        Connection connection = Database.getConnection();

        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT RegionId, RegionName FROM Regions WHERE CountryID = ?;");
                ps.setInt(1, countryId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    regions.put(rs.getInt("RegionId"), rs.getString("RegionName"));
                }
                ps.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("Couldn't retrieve regions!\nReason: " + e.getMessage());
            }
        } else {
            System.out.println("Connection to database was not established!");
        }
        return regions;
    }
}