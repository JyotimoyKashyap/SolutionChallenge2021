package com.example.vaccineapp.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseHospital {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("hospital")
    @Expose
    private HospitalDetails hospitalDetails;

    public ResponseHospital(String status, HospitalDetails hospitalDetails) {
        this.status = status;
        this.hospitalDetails = hospitalDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HospitalDetails getHospitalDetails() {
        return hospitalDetails;
    }

    public void setHospitalDetails(HospitalDetails hospitalDetails) {
        this.hospitalDetails = hospitalDetails;
    }
}
