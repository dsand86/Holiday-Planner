package com.andreiciubotaru.holidayplanner.model;

import java.util.Date;

public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private int countryId;
    private int regionId;
    private String city;
    protected int isAdmin = 0;
    private int mustResetPassword = 0;
    private Date AccountCreated;

    public User(String name, String email, String password, int countryId, int regionId,
                String city, int mustResetPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.countryId = countryId;
        this.regionId = regionId;
        this.city = city;
        this.mustResetPassword = mustResetPassword;
    }

    public User(int userID, String name, String email, String password, int countryId, int regionId,
                String city, int isAdmin, int mustResetPassword, Date accountCreated) {
        this.userId = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.countryId = countryId;
        this.regionId = regionId;
        this.city = city;
        this.isAdmin = isAdmin;
        this.mustResetPassword = mustResetPassword;
        this.AccountCreated = accountCreated;
    }


    public int getUserId() {
        return this.userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCountryId() {
        return this.countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getRegionId() {
        return this.regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public  int getIsAdmin() {
        return this.isAdmin;
    }

    public boolean getMustResetPassword() {
        if (this.mustResetPassword == 1)
            return true;
        return false;
    }

    public void setMustResetPassword(int mustResetPassword) {
        this.mustResetPassword = mustResetPassword;
    }

    public Date getAccountCreated() {
        return AccountCreated;
    }
}

/*class AdminUser extends User {
    public AdminUser(String Name, String Email, String Password, int CountryId, int RegionId, String City, int isAdmin) {
        super(Name, Email, Password, CountryId, RegionId, City);
        setIsAdmin(isAdmin);
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }
}*/