package com.example.vaccineapp.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseBabyVacTaken {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("baby")
    @Expose
    private BabyVacTaken babyVacTaken;

    public ResponseBabyVacTaken(String status, BabyVacTaken babyVacTaken) {
        this.status = status;
        this.babyVacTaken = babyVacTaken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BabyVacTaken getBabyVacTaken() {
        return babyVacTaken;
    }

    public void setBabyVacTaken(BabyVacTaken babyVacTaken) {
        this.babyVacTaken = babyVacTaken;
    }
}
