package com.andreiciubotaru.holidayplanner.controller.DataLayer;

import com.andreiciubotaru.holidayplanner.model.Database;
import com.andreiciubotaru.holidayplanner.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Users {
    public static User getUserDataByEmail(String email) {
        User u = null;
        Connection connection = Database.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Users WHERE Email = ?;");
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    u = new User(rs.getInt("UserID"), rs.getString("Name"), rs.getString("Email"), rs.getString("Password"),
                            rs.getInt("CountryID"), rs.getInt("RegionID"), rs.getString("City"),
                            rs.getInt("IsAdmin"), rs.getInt("MustResetPassword"), sdf.parse(rs.getString("AccountCreatedDate")));
                }
                ps.close();
                connection.close();
            } catch (ParseException e) {
                System.out.println("Couldn't parse date!\nReason: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Couldn't retrieve user data!\nReason: " + e.getMessage());
            }
        } else {
            System.out.println("Connection to database was not established!");
        }
        return u;
    }

    public static void createUser(String name, String email, String password, int countryId, int regionId,
                                  String city, int mustResetPassword) {
        User u = new User(name, email, password, countryId, regionId, city, mustResetPassword);
        Connection connection = Database.getConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO Users (Name, Email, Password, \n" +
                        "CountryID, RegionID, City, MustResetPassword) VALUES (?, ?, ?, ?, ?, ?, ?);");
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);
                ps.setInt(4, countryId);
                ps.setInt(5, regionId);
                ps.setString(6, city);
                ps.setInt(7, mustResetPassword);
                ResultSet rs = ps.executeQuery();
                ps.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("Couldn't add new user data!\nReason: " + e.getMessage());
            }
        } else {
            System.out.println("Connection to database was not established!");
        }
    }
}
