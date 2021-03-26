package com.example.vaccineapp.AppPreferences;

public interface SharedPrefsHelper {
    void AddBabyId(String id);
    void AddParent(String id);
    void setLocationPermission(Boolean access);
    void showPrivacyPolicy(Boolean shown);

    String RetrieveBabyId();
    String RetrieveParentId();
    Boolean retrieveLocationPermission();
    Boolean retrievePrivacyPolicy();
}
