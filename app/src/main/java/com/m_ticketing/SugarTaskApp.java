package com.m_ticketing;

import com.orm.SugarApp;
import com.orm.SugarContext;

/**
 * Created by CYMMOH on 8/11/2016.
 */
public class SugarTaskApp extends SugarApp{
    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());
    }
}
