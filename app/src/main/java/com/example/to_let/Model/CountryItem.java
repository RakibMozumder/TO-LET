package com.example.to_let.Model;


public class CountryItem {
    private int flagImage;
    private String countryName;


    public CountryItem(String countryName,int flagImage) {
        this.countryName = countryName;
        this.flagImage = flagImage;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getFlagImage() {
        return flagImage;
    }
}