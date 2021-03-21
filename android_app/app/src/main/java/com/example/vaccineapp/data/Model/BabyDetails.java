package com.example.vaccineapp.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BabyDetails {

    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("age")
    @Expose
    private String age;

    @SerializedName("parent")
    @Expose
    private String parent;

    @SerializedName("motherName")
    @Expose
    private String motherName;

    @SerializedName("fatherName")
    @Expose
    private String fatherName;

    @SerializedName("__v")
    private Integer __v;


    public BabyDetails(String _id, String name, String age, String parent,
                       String motherName, String fatherName, Integer __v) {
        this._id = _id;
        this.name = name;
        this.age = age;
        this.parent = parent;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.__v = __v;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Integer get__v() {
        return __v;
    }

    public void set__v(Integer __v) {
        this.__v = __v;
    }

    }

