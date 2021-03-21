package com.example.vaccineapp.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BabyVacTaken {

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

    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;

    @SerializedName("monthOfBirth")
    @Expose
    private String monthOfBirth;

    @SerializedName("yearOfBirth")
    @Expose
    private String yearOfBirth;

    @SerializedName("__v")
    @Expose
    private int __v;

    @SerializedName("vaccinesTaken")
    @Expose
    private List<VaccineDetails> vaccineDetails;

    public BabyVacTaken(String _id, String name, String age, String parent, String motherName, String fatherName,
                        String dateOfBirth, String monthOfBirth,
                        String yearOfBirth, int __v, List<VaccineDetails> vaccineDetails) {
        this._id = _id;
        this.name = name;
        this.age = age;
        this.parent = parent;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.dateOfBirth = dateOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.__v = __v;
        this.vaccineDetails = vaccineDetails;
    }

    public void set_id(String _id){
        this._id = _id;
    }
    public String get_id(){
        return this._id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAge(String age){
        this.age = age;
    }
    public String getAge(){
        return this.age;
    }
    public void setParent(String parent){
        this.parent = parent;
    }
    public String getParent(){
        return this.parent;
    }
    public void setMotherName(String motherName){
        this.motherName = motherName;
    }
    public String getMotherName(){
        return this.motherName;
    }
    public void setFatherName(String fatherName){
        this.fatherName = fatherName;
    }
    public String getFatherName(){
        return this.fatherName;
    }
    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public String getDateOfBirth(){
        return this.dateOfBirth;
    }
    public void setMonthOfBirth(String monthOfBirth){
        this.monthOfBirth = monthOfBirth;
    }
    public String getMonthOfBirth(){
        return this.monthOfBirth;
    }
    public void setYearOfBirth(String yearOfBirth){
        this.yearOfBirth = yearOfBirth;
    }
    public String getYearOfBirth(){
        return this.yearOfBirth;
    }
    public void set__v(int __v){
        this.__v = __v;
    }
    public int get__v(){
        return this.__v;
    }
}
