package com.example.vaccineapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseHosDetails {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("hospitals")
    @Expose
    private List<HospitalDetails> hospitalDetailsList;

    public ResponseHosDetails(String status, List<HospitalDetails> hospitalDetailsList) {
        this.status = status;
        this.hospitalDetailsList = hospitalDetailsList;
    }

    public String getStatus() {
        return status;
    }



    public List<HospitalDetails> getHospitalDetailsList() {
        return hospitalDetailsList;
    }

    public void setHospitalDetailsList(List<HospitalDetails> hospitalDetailsList) {
        this.hospitalDetailsList = hospitalDetailsList;
    }
}
