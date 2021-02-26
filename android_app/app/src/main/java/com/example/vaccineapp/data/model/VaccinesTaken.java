package com.example.vaccineapp.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VaccinesTaken {

    @SerializedName("babyId")
    @Expose
    private String baby_id;

    @SerializedName("vaccineId")
    @Expose
    private String vaccine_id;

    public VaccinesTaken(String baby_id, String vaccine_id) {
        this.baby_id = baby_id;
        this.vaccine_id = vaccine_id;
    }

    public String getBaby_id() {
        return baby_id;
    }


    public void setBaby_id(String baby_id) {
        this.baby_id = baby_id;
    }

    public String getVaccine_id() {
        return vaccine_id;
    }

    public void setVaccine_id(String vaccine_id) {
        this.vaccine_id = vaccine_id;
    }
}
