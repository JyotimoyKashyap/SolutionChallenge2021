package com.example.vaccineapp.AppPreferences;

public interface SharedPrefsHelper {
    void AddBabyId(String id);
    void AddParent(String id);

    String RetrieveBabyId();
    String RetrieveParentId();
}
