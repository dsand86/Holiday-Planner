package com.andreiciubotaru.holidayplanner.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    // PATH to a folder where user has write permission
    private static String PATH = "D:\\Holiday Planner\\hp.db";
    private static String url = "jdbc:sqlite:" + PATH;
    private static Connection connection = null;

    public static void ConnectToDB() {
        try {
            connection = DriverManager.getConnection(url);
            connection.createStatement().execute("PRAGMA foreign_keys = ON");
            //System.out.println("Connection to: " + PATH + " database has been established.");
        } catch (SQLException e) {
            System.out.println("Could not connect to: " + PATH + " database!\nReason: " + e.getMessage());
        }/* finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Could not connect to: " + PATH + " database");
                System.out.println("Reason: " + e.getMessage());
            }
        }*/
    }

    public static Connection getConnection() {
        ConnectToDB();
        try {
            if (connection != null) {
                return connection;
            }
        } catch (Exception e) {
            System.out.println("Connection to database was not established!\nReason: " + e.getMessage());
        }
        return null;
    }

    private final static String createUsersTable = "CREATE TABLE IF NOT EXISTS Users (\n" +
            "UserID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "Name TEXT,\n" +
            "Email TEXT NOT NULL UNIQUE,\n" +
            "Password TEXT NOT NULL,\n" +
            "CountryID INTEGER NOT NULL DEFAULT '0',\n" +
            "RegionID INTEGER NOT NULL DEFAULT '0',\n" +
            "City TEXT,\n" +
            "IsAdmin INTEGER DEFAULT '0',\n" +
            "MustResetPassword INTEGER DEFAULT '0',\n" +
            "AccountCreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP\n" +
            ");";

    private final static String createCountriesTable = "CREATE TABLE IF NOT EXISTS Countries (\n" +
            "CountryID INTEGER NOT NULL PRIMARY KEY,\n" +
            "CountryName TEXT NOT NULL UNIQUE\n" +
            ");";

    private final static String createRegionsTable = "CREATE TABLE IF NOT EXISTS Regions (\n" +
            "CountryID INTEGER NOT NULL,\n" +
            "RegionID INTEGER NOT NULL PRIMARY KEY,\n" +
            "RegionName TEXT NOT NULL,\n" +
            "FOREIGN KEY (CountryID) REFERENCES Countries(CountryID)\n" +
            ");";

    private final static String createNationalHolidaysTable = "CREATE TABLE IF NOT EXISTS NationalHolidays (\n" +
            "RegionID INTEGER NOT NULL,\n" +
            "NationalHolidays TEXT NOT NULL,\n" +
            "PRIMARY KEY (RegionID, NationalHolidays),\n" +
            "FOREIGN KEY (RegionID) REFERENCES Regions(RegionID)\n" +
            ");";

    private final static String createSimulationsTable = "CREATE TABLE IF NOT EXISTS Simulations (\n" +
            "SimulationID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "UserID TEXT NOT NULL,\n" +
            "SimulationDates TEXT NOT NULL,\n" +
            "FOREIGN KEY (UserID) REFERENCES Users(UserID)\n" +
            ");";

    public static void createTables() {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                statement.addBatch(createUsersTable);
                statement.addBatch(createCountriesTable);
                statement.addBatch(createRegionsTable);
                statement.addBatch(createNationalHolidaysTable);
                statement.addBatch(createSimulationsTable);
                statement.executeBatch();
                statement.close();
                connection.close();
                System.out.println("Tables created successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Couldn't create tables!\nReason: " + e.getMessage());
        }
    }

    private final static String insertCountries = "INSERT INTO Countries (CountryID, CountryName) VALUES\n" +
            "('0', 'N/A'),\n" +
            "('1', 'Romania'),\n" +
            "('2', 'Germany');";

    private final static String insertRegions = "INSERT INTO Regions (CountryID, RegionID, RegionName) VALUES\n" +
            "('0', '0', 'N/A'),\n" +
            "('1', '1', 'Romania'),\n" +
            "('2', '2', 'Germany'),\n" +
            "('2', '3', 'Baden-Württemberg'),\n" +
            "('2', '4', 'Bayern'),\n" +
            "('2', '5', 'Berlin'),\n" +
            "('2', '6', 'Brandenburg'),\n" +
            "('2', '7', 'Bremen'),\n" +
            "('2', '8', 'Hamburg'),\n" +
            "('2', '9', 'Hessen'),\n" +
            "('2', '10', 'Mecklenburg-Vorpommern'),\n" +
            "('2', '11', 'Niedersachsen'),\n" +
            "('2', '12', 'Nordrhein-Westfalen'),\n" +
            "('2', '13', 'Rheinland-Pfalz'),\n" +
            "('2', '14', 'Saarland'),\n" +
            "('2', '15', 'Sachsen'),\n" +
            "('2', '16', 'Sachsen-Anhalt'),\n" +
            "('2', '17', 'Schleswig-Holstein'),\n" +
            "('2', '18', 'Thüringen');";

    private final static String insertNationalHolidays = "INSERT INTO NationalHolidays (RegionID, NationalHolidays) VALUES\n" +
            "('1', '2019/11/30'),\n" +
            "('1', '2019/12/01'),\n" +
            "('1', '2019/12/25'),\n" +
            "('1', '2019/12/26'),\n" +
            "('1', '2020/01/01'),\n" +
            "('1', '2020/01/02'),\n" +
            "('1', '2020/01/24'),\n" +
            "('1', '2020/04/17'),\n" +
            "('1', '2020/04/19'),\n" +
            "('1', '2020/05/20'),\n" +
            "('1', '2020/05/01'),\n" +
            "('1', '2020/06/01'),\n" +
            "('1', '2020/07/07'),\n" +
            "('1', '2020/07/08'),\n" +
            "('1', '2020/08/15'),\n" +
            "('1', '2020/11/30'),\n" +
            "('1', '2020/12/01'),\n" +
            "('1', '2020/12/25'),\n" +
            "('1', '2020/12/26'),\n" +
            "('15', '2019/11/20'),\n" +
            "('2', '2019/12/25'),\n" +
            "('2', '2019/12/26'),\n" +
            "('2', '2020/01/01'),\n" +
            "('3', '2020/01/06'),\n" +
            "('4', '2020/01/06'),\n" +
            "('16', '2020/01/06'),\n" +
            "('5', '2020/03/08'),\n" +
            "('2', '2020/04/10'),\n" +
            "('6', '2020/01/12'),\n" +
            "('2', '2020/04/13'),\n" +
            "('2', '2020/05/01'),\n" +
            "('2', '2020/05/08'),\n" +
            "('2', '2020/05/21'),\n" +
            "('6', '2020/05/31'),\n" +
            "('2', '2020/06/01'),\n" +
            "('3', '2020/06/11'),\n" +
            "('4', '2020/06/11'),\n" +
            "('9', '2020/06/11'),\n" +
            "('12', '2020/06/11'),\n" +
            "('13', '2020/06/11'),\n" +
            "('14', '2020/06/11'),\n" +
            "('4', '2020/08/08'),\n" +
            "('4', '2020/08/15'),\n" +
            "('14', '2020/08/15'),\n" +
            "('2', '2020/10/03'),\n" +
            "('6', '2020/10/31'),\n" +
            "('7', '2020/10/31'),\n" +
            "('8', '2020/10/31'),\n" +
            "('10', '2020/10/31'),\n" +
            "('11', '2020/10/31'),\n" +
            "('15', '2020/10/31'),\n" +
            "('16', '2020/10/31'),\n" +
            "('17', '2020/10/31'),\n" +
            "('18', '2020/10/31'),\n" +
            "('3', '2020/11/01'),\n" +
            "('4', '2020/11/01'),\n" +
            "('12', '2020/11/01'),\n" +
            "('13', '2020/11/01'),\n" +
            "('14', '2020/11/01'),\n" +
            "('15', '2020/11/18'),\n" +
            "('2', '2020/12/25'),\n" +
            "('2', '2020/12/26');";

    public static void fillDefaultTables() {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                statement.addBatch(insertCountries);
                statement.addBatch(insertRegions);
                statement.addBatch(insertNationalHolidays);
                statement.executeBatch();
                statement.close();
                connection.close();
                System.out.println("Content inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Couldn't insert content!\nReason: " + e.getMessage());
        }
    }

    private final static String insertUsersContent = "INSERT INTO Users (Name, Email, Password, CountryID, RegionID, City, IsAdmin) VALUES\n" +
            "('SysAdmin', 'sa@holidayplanner.com', 'SysPass1234', '0', '0', 'München', '1'),\n" +
            "('Andrei Ciubotaru', 'andrei.ciubotaru86@gmail.com', 'StrongPa$$19', '2', '5', 'München', '1'),\n" +
            "('John does', 'john.doe@yahoo.com', 'test1234', '0', '0', 'Iasi', '0'),\n" +
            "('test user', 'fake@test.com', '12345', '0', '0', '', '0');";

    private final static String insertSimulationsContent = "INSERT INTO Simulations (SimulationID, UserID, SimulationDates) VALUES\n" +
            "('1', '1','2019/11/30,2019/12/01,2019/12/25,2019/12/26,2020/01/01,2020/01/02,2020/01/24,2020/04/17,2020/04/19'),\n" +
            "('2', '2','2019/11/30,2019/12/01,2019/12/25,2019/12/26,2020/01/01,2020/01/02,2020/01/24,2020/04/17,2020/04/19'),\n" +
            "('3', '3','2019/11/30,2019/12/01,2019/12/25,2019/12/26,2020/01/01,2020/01/02,2020/01/24,2020/04/17,2020/04/19');";

    public static void fillTablesWithDummyData() {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                statement.addBatch(insertUsersContent);
                statement.addBatch(insertSimulationsContent);
                statement.executeBatch();
                statement.close();
                connection.close();
                System.out.println("Dummy content inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Couldn't insert dummy content!\nReason: " + e.getMessage());
        }
    }
}