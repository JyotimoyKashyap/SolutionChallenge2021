package com.example.vaccineapp.data.Api;

import android.content.Context;

import com.example.vaccineapp.data.Model.CovidResponse;
import com.example.vaccineapp.retrofit.RetrofitProvider;

import retrofit2.Call;

public class ApiHelper implements ApiService{

    private static ApiHelper instance;
    private ApiService api;

    public ApiHelper(Context context) {
        api = RetrofitProvider.getInstance(context).create(ApiService.class);
    }

    public static ApiHelper getInstance(Context context){
        if(instance == null) {
            synchronized (ApiHelper.class) {
                if (instance == null) {
                    instance = new ApiHelper(context);
                }
            }
        }
        return instance;

    }


    @Override
    public Call<CovidResponse> getIndiaData() {
        /** Do not change this because it is overridden in the guide fragment */
        return null;
    }
}
