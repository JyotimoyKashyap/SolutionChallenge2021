package com.example.vaccineapp.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterBaby {

        @SerializedName("name")
        @Expose
        public String name;

        @SerializedName("dateOfBirth")
        @Expose
        public String dateOfBirth;

        @SerializedName("monthOfBirth")
        @Expose
        public String monthOfBirth;

        @SerializedName("yearOfBirth")
        @Expose
        public String yearOfBirth;

        @SerializedName("age")
        @Expose
        public String age;

        @SerializedName("motherName")
        @Expose
        public String motherName;

        @SerializedName("fatherName")
        @Expose
        public String fatherName;

    public RegisterBaby(String name, String dateOfBirth,
                        String monthOfBirth, String yearOfBirth, String age, String motherName, String fatherName) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.age = age;
        this.motherName = motherName;
        this.fatherName = fatherName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
}
