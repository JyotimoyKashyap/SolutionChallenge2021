package com.example.vaccineapp.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.vaccineapp.data.Api.ApiHelper;
import com.example.vaccineapp.data.Model.RegisterBaby;
import com.example.vaccineapp.data.Model.ResponseBabyDetails;
import com.example.vaccineapp.data.Model.ResponseBabyVacTaken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BabyViewModel extends AndroidViewModel {

    private ApiHelper apiHelper;
    private MutableLiveData<ResponseBabyDetails> babyDetailsResponse;
    private MutableLiveData<ResponseBabyVacTaken> vaccineResponse;


    public BabyViewModel(@NonNull Application application) {
        super(application);
        apiHelper = new ApiHelper(application);
        babyDetailsResponse = new MutableLiveData<ResponseBabyDetails>();
        vaccineResponse = new MutableLiveData<ResponseBabyVacTaken>();
    }


    public MutableLiveData<ResponseBabyDetails> getResponse() {
        return babyDetailsResponse;
    }

    public MutableLiveData<ResponseBabyVacTaken> getVaccineResponse() {
        return vaccineResponse;
    }

    public void RegisterBaby(String userId, RegisterBaby registerBaby) {
        apiHelper.registerBaby(userId, registerBaby).enqueue(new Callback<ResponseBabyDetails>() {
            @Override
            public void onResponse(Call<ResponseBabyDetails> call, Response<ResponseBabyDetails> response) {
                if (response.code() < 300) {
                    babyDetailsResponse.postValue(response.body());
                    String babyid = response.body().getBabyDetails().get_id();
                    Log.d("register baby", String.valueOf(response.code())+" : success");
                    Log.d("babyid",babyid);
                } else if (response.code() > 400) {
                    babyDetailsResponse.postValue(null);
                    Log.d("register baby", String.valueOf(response.code())+" : success");
                }
            }

            @Override
            public void onFailure(Call<ResponseBabyDetails> call, Throwable t) {
                babyDetailsResponse.postValue(null);
            }
        });
    }

    public void GetVacTakenList(String parentId) {

        apiHelper.GetBabyDetails(parentId).enqueue(new Callback<ResponseBabyVacTaken>() {
            @Override
            public void onResponse(Call<ResponseBabyVacTaken> call, Response<ResponseBabyVacTaken> response) {
                if(response.code()<300)
                {
                    vaccineResponse.postValue(response.body());
                }
                else if(response.code()>400)
                {
                    vaccineResponse.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseBabyVacTaken> call, Throwable t) {
                vaccineResponse.postValue(null);
            }
        });
    }

}