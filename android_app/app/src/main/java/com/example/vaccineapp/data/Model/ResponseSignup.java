package com.example.vaccineapp.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSignup {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("user")
    @Expose
    private UserDetails userDetails;

    public ResponseSignup(String status, UserDetails userDetails) {
        this.status = status;
        this.userDetails = userDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDetails getUser() {
        return userDetails;
    }

    public void setUser(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
