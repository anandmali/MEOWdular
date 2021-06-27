package com.anandmali.aisledesign.network;

import android.content.SharedPreferences;
import android.util.Log;

import javax.inject.Inject;


public class SessionManager {

    private final SharedPreferences sharedPreferences;
    private final String PREF_TOKE = "TOKEN";

    @Inject
    public SessionManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveToken(String token) {
        Log.e("Token => ", token);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_TOKE, token);
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(PREF_TOKE, "default_token");
    }
}
