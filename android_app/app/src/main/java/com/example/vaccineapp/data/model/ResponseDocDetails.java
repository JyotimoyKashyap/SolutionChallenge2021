package com.example.vaccineapp.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDocDetails {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("doctors")
    @Expose
    private List<DoctorDetails> doctorDetails;

    public ResponseDocDetails(String status, List<DoctorDetails> doctorDetails) {
        this.status = status;
        this.doctorDetails = doctorDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DoctorDetails> getDoctorDetails() {
        return doctorDetails;
    }

    public void setDoctorDetails(List<DoctorDetails> doctorDetails) {
        this.doctorDetails = doctorDetails;
    }
}
