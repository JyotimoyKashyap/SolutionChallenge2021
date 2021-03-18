package com.example.vaccineapp.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String Name;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("__v")
    @Expose
    private String _v;

    @SerializedName("token")
    @Expose
    private String token;

    public User(String id, String name, String phone, String email, String address, String _v, String token) {
        this.id = id;
        Name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this._v = _v;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String get_v() {
        return _v;
    }

    public void set_v(String _v) {
        this._v = _v;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
