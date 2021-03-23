package com.example.vaccineapp.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.vaccineapp.AppPreferences.Preferences;
import com.example.vaccineapp.data.Api.ApiHelper;
import com.example.vaccineapp.data.Model.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VaccineViewModel extends AndroidViewModel {


    private ApiHelper apiHelper;
    private MutableLiveData<ResponseVaccine> vaccineRes;
    private MutableLiveData<ResponseVaccineDetails> vaccineDetails;
    private MutableLiveData<ResponseVacTaken> vacTaken;
    private MutableLiveData<ResponseBabyDetails> babyResponse;
    private MutableLiveData<ResponseSignup> SignUpResponse;
    private Preferences preferences;


    public VaccineViewModel(@NonNull Application application) {
        super(application);
        apiHelper = new ApiHelper(application);
        preferences = new Preferences(application);
        vaccineRes = new MutableLiveData<ResponseVaccine>();
        vaccineDetails = new MutableLiveData<ResponseVaccineDetails>();
        vacTaken = new MutableLiveData<ResponseVacTaken>();
        SignUpResponse = new MutableLiveData<ResponseSignup>();
        babyResponse = new MutableLiveData<ResponseBabyDetails>();



    }



    public MutableLiveData<ResponseVaccine> getVaccineRes()
    {
        return  vaccineRes;
    }

    public MutableLiveData<ResponseVaccineDetails> getAllVaccinesResponse()
    {
        return vaccineDetails;
    }

    public MutableLiveData<ResponseVacTaken> getVacTakenRes() {
        return vacTaken;
    }


    public MutableLiveData<ResponseSignup> getSignUpResponse()
    {
        return SignUpResponse;
    }

    public MutableLiveData<ResponseBabyDetails> getBabyResponse()
    {
        return babyResponse;
    }


    public void SignUp(Signup signup)
    {
        apiHelper.AddUser(signup).enqueue(new Callback<ResponseSignup>() {
            @Override
            public void onResponse(Call<ResponseSignup> call, Response<ResponseSignup> response) {
                if(response.code()<300){
                    SignUpResponse.postValue(response.body());
                    String parentId = response.body().getUser().getId();
                    preferences.AddParent(parentId);
                    //Parent id to firebase
                    FirebaseAuth mAuth;
                    mAuth = FirebaseAuth.getInstance();
                    String user = mAuth.getCurrentUser().getUid();
                    DatabaseReference databaseReference;
                    databaseReference = FirebaseDatabase.getInstance().getReference("Baby_Data");
                    databaseReference.child(user).child("Parent_Id").setValue(parentId);
                    Log.d("sign up", String.valueOf(response.code())+" : success");
                    Log.i("parentID",parentId);
                }else if(response.code()>400){
                    SignUpResponse.postValue(null);
                    Log.d("sign ups", String.valueOf(response.code())+" : failure");
                }
            }

            @Override
            public void onFailure(Call<ResponseSignup> call, Throwable t) {
                SignUpResponse.postValue(null);
            }
        });
    }

    public void getVaccine(String id)
    {
        apiHelper.GetVaccine(id).enqueue(new Callback<ResponseVaccine>() {
            @Override
            public void onResponse(Call<ResponseVaccine> call, Response<ResponseVaccine> response) {
                if(response.code()<300){
                    vaccineRes.postValue(response.body());
                    Log.d("vaccines", String.valueOf(response.code())+" : success");
                }else if(response.code()>400){
                    vaccineRes.postValue(null);
                    Log.d("vaccines", String.valueOf(response.code())+" : fail");
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

    public void getVaccinesHosWise()
    {
        apiHelper.GetAllVaccinesHosWise().enqueue(new Callback<ResponseVacTaken>() {
            @Override
            public void onResponse(Call<ResponseVacTaken> call, Response<ResponseVacTaken> response) {
                if(response.code()<300){
                    vacTaken.postValue(response.body());
                }else if(response.code()>400){
                    vacTaken.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseVacTaken> call, Throwable t) {
                vacTaken.postValue(null);
            }
        });
    }


}
