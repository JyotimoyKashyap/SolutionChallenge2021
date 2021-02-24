package com.example.vaccineapp.AppPreferences;

public interface SharedPrefsHelper {
    void AddBabyId(String id);
    void AddUserId(String id);

    String RetrieveBabyId();
    String RetrieveUserId();
}
