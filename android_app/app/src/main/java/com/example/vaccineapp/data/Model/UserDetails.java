package com.example.vaccineapp.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetails {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("uid")
    @Expose
    private String uid;

    @SerializedName("_v")
    @Expose
    private Integer _v;


    public UserDetails(String id, String email, String uid, Integer _v) {
        this.id = id;
        this.email = email;
        this.uid = uid;
        this._v = _v;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer get_v() {
        return _v;
    }

    public void set_v(Integer _v) {
        this._v = _v;
    }

}
