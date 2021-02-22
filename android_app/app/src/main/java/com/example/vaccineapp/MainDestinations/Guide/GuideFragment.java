package com.example.vaccineapp.MainDestinations.Guide;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vaccineapp.R;
import com.example.vaccineapp.data.Api.ApiService;
import com.example.vaccineapp.data.Model.CovidResponse;
import com.example.vaccineapp.databinding.FragmentGuidBinding;
import com.google.android.material.transition.MaterialSharedAxis;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GuideFragment extends Fragment {

    private FragmentGuidBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGuidBinding.inflate(inflater, container, false);
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.X, false));
        View view = binding.getRoot();

        //setting retrofit for getting response from other api
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://covid-19-tracking.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit2.create(ApiService.class);

        Call<CovidResponse> covidResponseCall = apiService.getIndiaData();
        covidResponseCall.enqueue(new Callback<CovidResponse>() {
            @Override
            public void onResponse(Call<CovidResponse> call, Response<CovidResponse> response) {
                if(response.code() < 300){
                    Log.d("Guide", "success");
                }else{
                    Log.d("Guide", "Bad Request");
                }
            }

            @Override
            public void onFailure(Call<CovidResponse> call, Throwable t) {

            }
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}