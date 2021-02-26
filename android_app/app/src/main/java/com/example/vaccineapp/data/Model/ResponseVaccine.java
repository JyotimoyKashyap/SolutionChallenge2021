package com.example.vaccineapp.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseVaccine {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("vaccine")
    @Expose
    private VaccineDetails Details;

    public ResponseVaccine(String status, VaccineDetails vaccineDetails) {
        this.status = status;
        this.Details = vaccineDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VaccineDetails getVacDetails() {
        return Details;
    }

    public void setVacDetails(VaccineDetails vaccineDetails) {
        this.Details = vaccineDetails;
    }
}
