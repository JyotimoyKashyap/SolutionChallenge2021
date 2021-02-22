package com.example.vaccineapp.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.vaccineapp.data.api.ApiHelper;
import com.example.vaccineapp.data.model.ResponseVaccine;
import com.example.vaccineapp.data.model.ResponseVaccineDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VaccineViewModel extends AndroidViewModel {


    private ApiHelper apiHelper;
    private MutableLiveData<ResponseVaccine> vaccineRes;
    private MutableLiveData<ResponseVaccineDetails> vaccineDetails;

    public VaccineViewModel(@NonNull Application application) {
        super(application);
        apiHelper = new ApiHelper(application);
        vaccineRes = new MutableLiveData<ResponseVaccine>();
        vaccineDetails = new MutableLiveData<ResponseVaccineDetails>();

    }

    public MutableLiveData<ResponseVaccine> getVaccineRes()
    {
        return  vaccineRes;
    }

    public MutableLiveData<ResponseVaccineDetails> getAllVaccinesResponse()
    {
        return vaccineDetails;
    }

    public void getVaccine(String id)
    {
        apiHelper.GetVaccine(id).enqueue(new Callback<ResponseVaccine>() {
            @Override
            public void onResponse(Call<ResponseVaccine> call, Response<ResponseVaccine> response) {
                if(response.code()<300){
                    vaccineRes.postValue(response.body());
                }else if(response.code()>400){
                    vaccineRes.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<ResponseVaccine> call, Throwable t) {
                vaccineRes.postValue(null);
            }
        });
    }

    public void getAllVaccines()
    {
        apiHelper.GetAllVaccines().enqueue(new Callback<ResponseVaccineDetails>() {
            @Override
            public void onResponse(Call<ResponseVaccineDetails> call, Response<ResponseVaccineDetails> response) {
                if(response.code()<300){
                    vaccineDetails.postValue(response.body());
                }else if(response.code()>400){
                    vaccineDetails.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<ResponseVaccineDetails> call, Throwable t) {
                vaccineDetails.postValue(null);
            }
        });
    }
}
