package com.example.vaccineapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.vaccineapp.data.Api.ApiHelper;
import com.example.vaccineapp.data.Model.RegisterBaby;
import com.example.vaccineapp.data.Model.ResponseBabyDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BabyViewModel extends AndroidViewModel {

    private ApiHelper apiHelper;
    private MutableLiveData<ResponseBabyDetails> babyDetailsResponse;


    public BabyViewModel(@NonNull Application application) {
        super(application);
        apiHelper = new ApiHelper(application);
        babyDetailsResponse = new MutableLiveData<ResponseBabyDetails>();
    }

    public void RegisterBaby(String userId,RegisterBaby registerBaby)
    {
        apiHelper.registerBaby(userId,registerBaby).enqueue(new Callback<ResponseBabyDetails>() {
            @Override
            public void onResponse(Call<ResponseBabyDetails> call, Response<ResponseBabyDetails> response) {
                if(response.code()<300)
                {
                    babyDetailsResponse.postValue(response.body());
                }
                else if(response.code()>400)
                {
                    babyDetailsResponse.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<ResponseBabyDetails> call, Throwable t) {
                babyDetailsResponse.postValue(null);
            }
        });
    }
}
