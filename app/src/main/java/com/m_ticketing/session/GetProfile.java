package com.m_ticketing.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.m_ticketing.ini.Constants;


/**
 * Created by chris on 9/19/2016. for Twirry
 */
public class GetProfile {
    private SharedPreferences sharedPreferences;

    public GetProfile(Context context) {
        this.sharedPreferences = context.getSharedPreferences(Constants.Session.SESSION, context.MODE_PRIVATE);
    }

    public boolean LoggedIn() {
        return sharedPreferences.getBoolean(Constants.Session.LOGGEDIN, false);
    }

    public String UserId() {
        return sharedPreferences.getString(Constants.Session.USERID, null);
    }



}
