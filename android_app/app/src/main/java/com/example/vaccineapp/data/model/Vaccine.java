package com.example.vaccineapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vaccine {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("whenToGive")
    @Expose
    private String whenToGive;

    @SerializedName("dose")
    @Expose
    private String dose;

    @SerializedName("route")
    @Expose
    private String route;

    @SerializedName("site")
    @Expose
    private String site;

    public Vaccine(String name, String whenToGive, String dose, String route, String site) {
        this.name = name;
        this.whenToGive = whenToGive;
        this.dose = dose;
        this.route = route;
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWhenToGive() {
        return whenToGive;
    }

    public void setWhenToGive(String whenToGive) {
        this.whenToGive = whenToGive;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
