package com.example.vaccineapp.data.api;

import android.content.Context;


import com.example.vaccineapp.data.Api.ApiService;
import com.example.vaccineapp.data.Model.*;
import com.example.vaccineapp.data.Model.CovidTracker.CovidResponse;
import com.example.vaccineapp.retrofit.RetrofitProvider;

import retrofit2.Call;

public class ApiHelper implements ApiService {

    private static ApiHelper instance;
    private ApiService api;
    public ApiHelper(Context context) {
        api = RetrofitProvider.getInstance(context).create(ApiService.class);
    }

    public static ApiHelper getInstance(Context context){
        if(instance == null){
            synchronized (ApiHelper.class){
                if(instance == null){
                    instance = new ApiHelper(context);
                }
            }
        }
        return instance;
    }

    @Override
    public Call<CovidResponse> getIndiaData() {
        return null;
    }

    @Override
    public Call<ResponseHosDetails> GetAllHospitals() {
        return api.GetAllHospitals();
    }

    @Override
    public Call<ResponseHospital> GetHospital(String id) {
        return api.GetHospital(id);
    }

    @Override
    public Call<ResponseDoctor> GetDoctor(String doctorId) {
        return api.GetDoctor(doctorId);
    }

    @Override
    public Call<ResponseDocDetails> GetAllDoctors(String hospitalId) {
        return api.GetAllDoctors(hospitalId);
    }

    @Override
    public Call<ResponseVaccine> GetVaccine(String vaccineId) {
        return api.GetVaccine(vaccineId);
    }

    @Override
    public Call<ResponseVaccineDetails> GetAllVaccines() {
        return api.GetAllVaccines();
    }

    @Override
    public Call<ResponseVacTaken> GetAllVaccinesHosWise() {
        return api.GetAllVaccinesHosWise();
    }

    @Override
    public Call<ResponseBabyDetails> AddVaccinesTaken(VaccinesTaken vaccinesTaken) {
        return api.AddVaccinesTaken(vaccinesTaken);
    }

    @Override
    public Call<ResponseBabyDetails> RemoveVaccine(VaccinesTaken vaccinesTaken) {
        return api.RemoveVaccine(vaccinesTaken);
    }
}
