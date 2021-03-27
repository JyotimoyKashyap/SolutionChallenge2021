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
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("BabyId",id);
        editor.apply();
    }

    @Override
    public void AddParent(String id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ParentId", id);
        editor.apply();
    }

    @Override
    public void setLocationPermission(Boolean access) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("location_permission", access);
        editor.apply();
    }

    @Override
    public void showPrivacyPolicy(Boolean shown) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("privacy_policy" , shown);
    }


    @Override
    public String RetrieveBabyId() {
        return sharedPreferences.getString("BabyId",null);
    }

    @Override
    public String RetrieveParentId() {
        return sharedPreferences.getString("ParentId",null);
    }

    @Override
    public Boolean retrieveLocationPermission() {
        return sharedPreferences.getBoolean("location_permission" , false);
    }

    @Override
    public Boolean retrievePrivacyPolicy() {
        return sharedPreferences.getBoolean("privacy_policy", false);
    }


}
