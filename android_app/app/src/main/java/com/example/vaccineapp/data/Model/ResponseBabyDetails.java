package com.example.vaccineapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseBabyDetails {

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("baby")
    @Expose
    private BabyDetails babyDetails;

    public ResponseBabyDetails(String status, BabyDetails babyDetails) {
        this.status = status;
        this.babyDetails = babyDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BabyDetails getBabyDetails() {
        return babyDetails;
    }

    public void setBaby_details(BabyDetails baby_details) {
        this.babyDetails = babyDetails;
    }
}
