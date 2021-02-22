package com.example.vaccineapp.data.Api;

import com.example.vaccineapp.data.Model.CovidTracker.CovidResponse;
import com.example.vaccineapp.data.model.ResponseDocDetails;
import com.example.vaccineapp.data.model.ResponseDoctor;
import com.example.vaccineapp.data.model.ResponseHosDetails;
import com.example.vaccineapp.data.model.ResponseHospital;
import com.example.vaccineapp.data.model.ResponseVaccine;
import com.example.vaccineapp.data.model.ResponseVaccineDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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
}
