package com.m_ticketing.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.m_ticketing.ini.Constants;


/**
 * Created by chris on 9/19/2016. for Twirry
 */
public class SetProfile {
    private SharedPreferences.Editor editor;

    public SetProfile(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.Session.SESSION, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public void setLoggedIn(Boolean LoggedIn) {
        editor.putBoolean(Constants.Session.LOGGEDIN, LoggedIn);
    }

    public void setUserId(String userid) {
        editor.putString(Constants.Session.USERID, userid);
    }


    public void Save() {
        editor.apply();
    }

    public void setLoggedOut() {
        editor.clear().commit();
    }
}
