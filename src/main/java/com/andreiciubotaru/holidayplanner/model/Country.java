package com.andreiciubotaru.holidayplanner.model;

public class Country implements Comparable<Country> {
    private int countryId;
    private String countryName;

    public Country() {
    }

    /*public Country(String countryName) {
        this.countryName = countryName;
    }*/

    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public int compareTo(Country country) {
        return this.getCountryName().compareTo(country.getCountryName());
    }
}