package com.example.vaccineapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorDetails {

    @SerializedName("_id")
    @Expose
    private String doctor_id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("specialization")
    @Expose
    private String specialization;

    @SerializedName("hospital")
    @Expose
    private String hospital;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    @SerializedName("_v")
    @Expose
    private Integer _v;

    public DoctorDetails(String doctor_id, String name, String email, String specialization, String hospital, String address, String createdAt, String updatedAt, Integer _v) {
        this.doctor_id = doctor_id;
        this.name = name;
        this.email = email;
        this.specialization = specialization;
        this.hospital = hospital;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this._v = _v;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer get_v() {
        return _v;
    }

    public void set_v(Integer _v) {
        this._v = _v;
    }
}
