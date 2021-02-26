package com.example.vaccineapp.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseVacTaken {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("vaccines")
    @Expose
    private List<Vaccine> vacDetailsList;

    public ResponseVacTaken(String status, List<Vaccine> vacDetailsList) {
        this.status = status;
        this.vacDetailsList = vacDetailsList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Vaccine> getVac_detailsList() {
        return vacDetailsList;
    }

    public void setVac_detailsList(List<Vaccine> vacDetailsList) {
        this.vacDetailsList = vacDetailsList;
    }
}
