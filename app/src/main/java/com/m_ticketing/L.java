package com.m_ticketing;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by james on 6/20/16.
 */
public class L {
    public static void t(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void T(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /*this is mine*/
    public static void d(String message) {
        Log.d("fika_android", message);
    }
}
