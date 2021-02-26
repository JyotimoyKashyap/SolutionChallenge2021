package com.example.vaccineapp.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDoctor {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("doctor")
    @Expose
    private DoctorDetails details;

    public ResponseDoctor(String status, DoctorDetails details) {
        this.status = status;
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DoctorDetails getDetails() {
        return details;
    }

    public void setDetails(DoctorDetails details) {
        this.details = details;
    }
}
