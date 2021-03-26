package com.example.vaccineapp.data.Api;


import com.example.vaccineapp.data.Model.*;
import com.example.vaccineapp.data.Model.CovidTracker.CovidResponse;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @Headers({"x-rapidapi-key:ec147f51cdmshd3ec85881d7532fp169b1ejsn03dfe947e1ff", "x-rapidapi-host:covid-19-tracking.p.rapidapi.com"})
    @GET("v1/india")
    Call<CovidResponse> getIndiaData();


    @GET("api/hospital/all")
    Call<ResponseHosDetails> GetAllHospitals();

    @GET("api/hospital/{hospitalId}")
    Call<ResponseHospital> GetHospital(@Path("hospitalId") String id);

    @GET("api/doctor/{doctorId}")
    Call<ResponseDoctor> GetDoctor(@Path("doctorId") String doctorId);

    @GET("api/doctor/hospitalwise/{hospitalId}")
    Call<ResponseDocDetails> GetAllDoctors(@Path("hospitalId") String hospitalId);

    @GET("api/vaccine/{vaccineId}")
    Call<ResponseVaccine> GetVaccine(@Path("vaccineId")String vaccineId);

    @GET("api/vaccine")
    Call<ResponseVaccineDetails> GetAllVaccines();

    @GET("api/vaccine/hospitalwise/all")
    Call<ResponseVacTaken> GetAllVaccinesHosWise();


    @POST("api/user/baby/taken/vaccine")
    Call<ResponseBabyDetails> AddVaccinesTaken(@Body VaccinesTaken vaccinesTaken);

    @POST("api/user/baby/remove/vaccine")
    Call<ResponseBabyDetails> RemoveVaccine(@Body VaccinesTaken vaccinesTaken);

    @POST("api/user/signup")
    Call<ResponseSignup> AddUser(@Body Signup signup);


    @GET("api/user/baby/details/{parentId}")
    Call<ResponseBabyDetails> GetBabyDetails(@Path("parentId") String parentId);

    @POST("api/user/baby/register/{userId}")
    Call<ResponseBabyDetails> registerBaby(@Path("userId")String userId, @Body RegisterBaby registerBaby);

}
