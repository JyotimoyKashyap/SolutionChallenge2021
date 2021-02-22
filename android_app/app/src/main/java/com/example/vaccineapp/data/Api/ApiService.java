package com.example.vaccineapp.data.Api;

import android.content.Context;

import com.example.vaccineapp.data.Model.CovidResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {

    @Headers({"x-rapidapi-key:ec147f51cdmshd3ec85881d7532fp169b1ejsn03dfe947e1ff", "x-rapidapi-host:covid-19-tracking.p.rapidapi.com"})
    @GET("v1/india")
    Call<CovidResponse> getIndiaData();
}
