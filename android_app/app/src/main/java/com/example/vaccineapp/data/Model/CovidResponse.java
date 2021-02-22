package com.example.vaccineapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CovidResponse {

    @SerializedName("Active Cases_text")
    @Expose
    private String activeCases;

    @SerializedName("Country_text")
    @Expose
    private String country;

    @SerializedName("New Cases_text")
    @Expose
    private String newCases;

    @SerializedName("New Deaths_text")
    @Expose
    private String newDeaths;

    @SerializedName("Total Cases_text")
    @Expose
    private String totalCases;

    @SerializedName("Total Recovered_text")
    @Expose
    private String totalRecovered;

    public CovidResponse(String activeCases, String country, String newCases, String newDeaths, String totalCases, String totalRecovered) {
        this.activeCases = activeCases;
        this.country = country;
        this.newCases = newCases;
        this.newDeaths = newDeaths;
        this.totalCases = totalCases;
        this.totalRecovered = totalRecovered;
    }

    public String getActiveCases() {
        return activeCases;
    }

    public String getCountry() {
        return country;
    }

    public String getNewCases() {
        return newCases;
    }

    public String getNewDeaths() {
        return newDeaths;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public String getTotalRecovered() {
        return totalRecovered;
    }
}
