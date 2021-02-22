package com.example.vaccineapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseVaccineDetails {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("vaccines")
    @Expose
    private List<VaccineDetails> vaccineDetails;

    public ResponseVaccineDetails(String status, List<VaccineDetails> vaccineDetails) {
        this.status = status;
        this.vaccineDetails = vaccineDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<VaccineDetails> getVaccineDetails() {
        return vaccineDetails;
    }

    public void setVaccineDetails(List<VaccineDetails> vaccineDetails) {
        this.vaccineDetails = vaccineDetails;
    }
}
