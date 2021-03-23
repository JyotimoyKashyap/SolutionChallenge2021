package com.example.vaccineapp.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.vaccineapp.AppPreferences.Preferences;
import com.example.vaccineapp.data.Api.ApiHelper;
import com.example.vaccineapp.data.Model.RegisterBaby;
import com.example.vaccineapp.data.Model.ResponseBabyDetails;
import com.example.vaccineapp.data.Model.VaccinesTaken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BabyViewModel extends AndroidViewModel {

    private ApiHelper apiHelper;
    private MutableLiveData<ResponseBabyDetails> babyDetailsResponse;
    private Preferences preferences;


    public BabyViewModel(@NonNull Application application) {
        super(application);
        apiHelper = new ApiHelper(application);
        preferences = new Preferences(application);
        babyDetailsResponse = new MutableLiveData<ResponseBabyDetails>();

    }


    public MutableLiveData<ResponseBabyDetails> getResponse() {
        return babyDetailsResponse;
    }


    public void RegisterBaby(String userId, RegisterBaby registerBaby) {
        apiHelper.registerBaby(userId, registerBaby).enqueue(new Callback<ResponseBabyDetails>() {
            @Override
            public void onResponse(Call<ResponseBabyDetails> call, Response<ResponseBabyDetails> response) {
                if (response.code() < 300) {
                    babyDetailsResponse.postValue(response.body());
                    String babyid = response.body().getBabyDetails().get_id();
                    preferences.AddBabyId(babyid);
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


    }

    public void AddVaccines(VaccinesTaken vaccinesTaken)
    {
        apiHelper.AddVaccinesTaken(vaccinesTaken).enqueue(new Callback<ResponseBabyDetails>() {
            @Override
            public void onResponse(Call<ResponseBabyDetails> call, Response<ResponseBabyDetails> response) {
                if(response.code()<300)
                {
                    babyDetailsResponse.postValue(response.body());
                    Log.d("apicall ",response.message() + "  "+ response.code()+ "  "+ " success");
                }
                else if(response.code()>400)
                {
                    babyDetailsResponse.postValue(null);
                    Log.d("apicall ",response.message() + "  "+ response.code()+ "  "+ "failure");
                }
            }

            @Override
            public void onFailure(Call<ResponseBabyDetails> call, Throwable t) {
                babyDetailsResponse.postValue(null);
            }
        });
    }

}