package com.example.vaccineapp.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseRegisterUser {

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("baby")
    @Expose
    private RegisterUserDetails userDetails;

    public ResponseRegisterUser(String status, RegisterUserDetails userDetails) {
        this.status = status;
        this.userDetails = userDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RegisterUserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(RegisterUserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
