package com.example.vaccineapp.ViewModel;

import android.animation.ValueAnimator;
import android.app.Application;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends AndroidViewModel {

    //method to monitor the string of gender to change lottie animation
    private MutableLiveData<String> gender;

    public SharedViewModel(@NonNull Application application) {
        super(application);
        gender = new MutableLiveData<>();
    }

    //method to get the mutable live data
    public MutableLiveData<String> getGender(){
        return gender;
    }

    //method to set the mutable live data
    public void setGender(String firebaseDataGender){
        gender.postValue(firebaseDataGender);

    }

    //function for animating the text in counting manner
    public void startCountAnimation(TextView textView, String maxCount){
        int max = 0;
        if(maxCount.substring(0,1) != "+"){
            max = Integer.valueOf(maxCount.substring(1, maxCount.length()));
        }
        else if(maxCount == null) {
            max = 0;
        }else{
            max = Integer.valueOf(maxCount);
        }

        ValueAnimator animator = ValueAnimator.ofInt(0, max);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                textView.setText(valueAnimator.getAnimatedValue().toString());
            }
        });

        animator.start();
    }

}
