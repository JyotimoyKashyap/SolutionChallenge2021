package com.example.vaccineapp.ViewModel;

import android.annotation.TargetApi;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.vaccineapp.data.api.ApiHelper;
import com.example.vaccineapp.data.Model.*;


import java.util.AbstractMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalViewModel extends AndroidViewModel {

    private ApiHelper apiHelper;
    private MutableLiveData<ResponseHospital> hospitalRes;
    private MutableLiveData<ResponseHosDetails> hosDetails;
    private MutableLiveData<ResponseDoctor> doctorRes;
    private MutableLiveData<ResponseDocDetails> doctorDetails;


    public HospitalViewModel(@NonNull Application application) {
        super(application);
        apiHelper = new ApiHelper(application);
        hospitalRes = new MutableLiveData<ResponseHospital>();
        hosDetails = new MutableLiveData<ResponseHosDetails>();
        doctorRes = new MutableLiveData<ResponseDoctor>();
        doctorDetails = new MutableLiveData<ResponseDocDetails>();
    }

    public MutableLiveData<ResponseHosDetails> getAllHospitalsRes() {
        return hosDetails;
    }

    public MutableLiveData<ResponseHospital> getHospitalRes() {
        return hospitalRes;
    }

    public MutableLiveData<ResponseDoctor> getDoctorRes() {
        return doctorRes;
    }

    public MutableLiveData<ResponseDocDetails> getAllDoctorsRes() {
        return doctorDetails;
    }

    public void getAllHospitals()
    {
        apiHelper.GetAllHospitals().enqueue(new Callback<ResponseHosDetails>() {
            @Override
            public void onResponse(Call<ResponseHosDetails> call, Response<ResponseHosDetails> response) {
                if(response.code()<300){
                    hosDetails.postValue(response.body());
                }else if(response.code()>400){
                    hosDetails.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<ResponseHosDetails> call, Throwable t) {
                hosDetails.postValue(null);
            }
        });
    }

    public void getHospital(String id)
    {
        apiHelper.GetHospital(id).enqueue(new Callback<ResponseHospital>() {
            @Override
            public void onResponse(Call<ResponseHospital> call, Response<ResponseHospital> response) {
                if(response.code()<300){
                    hospitalRes.postValue(response.body());
                }else if(response.code()>400){
                    hospitalRes.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseHospital> call, Throwable t) {
                hospitalRes.postValue(null);
            }
        });
    }

    public void getAllDoctors(String hospitalId)
    {
        apiHelper.GetAllDoctors(hospitalId).enqueue(new Callback<ResponseDocDetails>() {
            @Override
            public void onResponse(Call<ResponseDocDetails> call, Response<ResponseDocDetails> response) {
                if(response.code()<300){
                    doctorDetails.postValue(response.body());
                }else if(response.code()>400){
                    doctorDetails.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<ResponseDocDetails> call, Throwable t) {
                doctorDetails.postValue(null);
            }
        });
    }

    public void getDoctor(String id)
    {
        apiHelper.GetDoctor(id).enqueue(new Callback<ResponseDoctor>() {
            @Override
            public void onResponse(Call<ResponseDoctor> call, Response<ResponseDoctor> response) {
                if(response.code()<300){
                    doctorRes.postValue(response.body());
                }else if(response.code()>400){
                    doctorRes.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<ResponseDoctor> call, Throwable t) {
                doctorRes.postValue(null);
            }
        });
    }

}
