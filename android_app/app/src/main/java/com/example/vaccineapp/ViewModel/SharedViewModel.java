package com.example.vaccineapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends AndroidViewModel {

    //method to monitor the string of gender to change lottie animation
    private MutableLiveData<String> gender;

    public SharedViewModel(@NonNull Application application) {
        super(application);
    }

    //method to get the mutable live data
    public MutableLiveData<String> getGender(){
        return gender;
    }

    //method to set the mutable live data
    public void setGender(String firebaseDataGender){
        gender.postValue(firebaseDataGender);
    }

}
