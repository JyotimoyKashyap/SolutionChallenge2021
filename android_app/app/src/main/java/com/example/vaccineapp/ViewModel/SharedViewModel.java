package com.example.vaccineapp.ViewModel;

import android.animation.ValueAnimator;
import android.app.Application;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.DecimalFormat;

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
        Log.d("Guide" , maxCount + " : this is total recovered");
        String count = "";
        for(char k : maxCount.toCharArray()){
            if((int) k > 47 && (int) k < 58){
                count = count + k;
            }
        }
        Log.d("Guide" , count + " : this is total recovered without commas");
        long max = 0;
        if(count.substring(0,1) != "+"){
            max = Long.valueOf(count.substring(1, count.length()));
        }
        else if(count == null) {
            max = 0;
        }else{
            max = Long.valueOf(count);
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setGroupingSize(3);
        decimalFormat.setGroupingUsed(true);

        ValueAnimator animator = ValueAnimator.ofInt(0, (int) max);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //textView.setText(valueAnimator.getAnimatedValue().toString());
                textView.setText(decimalFormat.format(valueAnimator.getAnimatedValue()).toString());
            }
        });

        animator.start();
    }

}
