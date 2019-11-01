package com.andreiciubotaru.holidayplanner.controller.DataLayer;

import com.andreiciubotaru.holidayplanner.model.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.TreeMap;

public class Countries {

    public static TreeMap<Integer, String> getCountries() {
        TreeMap<Integer, String> countries = new TreeMap<>();
        Connection connection = Database.getConnection();

        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Countries;");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    countries.put(rs.getInt("CountryID"), rs.getString("CountryName"));
                }
                ps.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("Couldn't retrieve countries!\nReason: " + e.getMessage());
            }
        } else {
            System.out.println("Connection to database was not established!");
        }
        return countries;
    }
}
