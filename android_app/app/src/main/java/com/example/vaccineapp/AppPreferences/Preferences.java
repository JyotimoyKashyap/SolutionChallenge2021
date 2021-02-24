package com.example.vaccineapp.AppPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences implements SharedPrefsHelper{


    private Context context;
    private static Preferences instance;

    private static final String BABY_ID = null;
    private static final String USER_ID = null;

    private SharedPreferences sharedPreferences;

    public Preferences(Context context){
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static Preferences getInstance(Context context){
        if(instance == null){
            synchronized (Preferences.class){
                if(instance == null){
                    instance = new Preferences(context);
                }
            }
        }

        return instance;
    }


    @Override
    public void AddBabyId(String id) {

    }

    @Override
    public void AddUserId(String id) {

    }

    @Override
    public String RetrieveBabyId() {
        return null;
    }

    @Override
    public String RetrieveUserId() {
        return null;
    }
}
