package com.example.vaccineapp.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterUser {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("dateOfBirth")
    @Expose
    private String dob;

    @SerializedName("monthOfBirth")
    @Expose
    private String mob;

    @SerializedName("yearOfBirth")
    @Expose
    private String yob;

    @SerializedName("age")
    @Expose
    private String age;

    @SerializedName("motherName")
    @Expose
    private String mother;

    @SerializedName("fatherName")
    @Expose
    private String father;

    public RegisterUser(String name, String dob, String mob, String yob, String age, String mother, String father) {
        this.name = name;
        this.dob = dob;
        this.mob = mob;
        this.yob = yob;
        this.age = age;
        this.mother = mother;
        this.father = father;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getYob() {
        return yob;
    }

    public void setYob(String yob) {
        this.yob = yob;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }
}
