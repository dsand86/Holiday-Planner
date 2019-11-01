package com.andreiciubotaru.holidayplanner.controller.DataLayer;

import com.andreiciubotaru.holidayplanner.model.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

public class NationalHolidays {
    public static HashSet<String> getNationalHolidaysByRegionId(int regionId) {
        HashSet<String> nationalHolidays = new HashSet<>();
        Connection connection = Database.getConnection();

        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT NationalHolidays FROM NationalHolidays WHERE RegionId IN \n" +
                "((SELECT RegionId FROM Regions where RegionId = (SELECT CountryId FROM Regions WHERE RegionID = ?)), ?);");
                ps.setInt(1, regionId);
                ps.setInt(2, regionId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    nationalHolidays.add(rs.getString("NationalHolidays"));
                }
                ps.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("Couldn't retrieve national holidays!\nReason: " + e.getMessage());
            }
        } else {
            System.out.println("Connection to database was not established!");
        }
        return nationalHolidays;
    }
}