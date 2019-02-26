package com.m_ticketing;

import android.content.Context;
import android.content.SharedPreferences;




public class Settings {


    public static final String PREFS_NAME = "MyPreferences";
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    Context context;

    public Settings(Context context) {
        settings = context.getSharedPreferences(PREFS_NAME, 0);
        this.context = context;
    }

    //First timelaunch
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor = settings.edit();
        editor.putBoolean("isfirsttimelaunch", isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return settings.getBoolean("isfirsttimelaunch", true);
    }

    //logged in
    public Boolean isLoggedIn() {
        return settings.getBoolean("loggedin", false);
    }

    public void setLoggedIn(Boolean isLogged) {
        editor = settings.edit();
        editor.putBoolean("loggedin", isLogged);
        editor.commit();
    }



    //User ID
    public int getUserId() {
        return settings.getInt("userid", 0);
    }

    public void setUserId(int userid) {
        editor = settings.edit();
        editor.putInt("userid", userid);
        editor.commit();
    }

    //User Name
    public String getUserName() {
        return settings.getString("username", "");
    }

    public void setUserName(String username) {
        editor = settings.edit();
        editor.putString("username", username);
        editor.commit();
    }

    //User Email
    public String getUserEmail() {
        return settings.getString("useremail", "");
    }

    public void setUserEmail(String useremail) {
        editor = settings.edit();
        editor.putString("usernemail", useremail);
        editor.commit();
    }

    /*profile complete*/
    public void setProfileComplete(boolean isProfileComplete) {
        editor = settings.edit();
        editor.putBoolean("isProfileComplete", isProfileComplete);
        editor.commit();
    }

    public boolean isProfileComplete() {
        return settings.getBoolean("isProfileComplete", true);
    }

    /*invalidate all settings*/
    public void inValidateAllSettings() {
        context.getSharedPreferences(PREFS_NAME, 0).edit().clear().commit();
        L.t(context, "Clearing the house, Please wait");
    }


}
