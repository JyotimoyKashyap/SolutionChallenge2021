package com.example.vaccineapp.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Signup {

    @SerializedName("uid")
    @Expose
    private String uid;

    @SerializedName("email")
    @Expose
    private String email;

    public Signup(String uid, String email) {
        this.uid = uid;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
