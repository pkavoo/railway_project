package com.m_ticketing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Polycarp on 8/9/2016.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

    }

    public void click(View v) {
        switch (v.getId()) {
            case R.id.one:
                startActivity(new Intent(getBaseContext(), Activity_Ticketing.class));
                break;
            case R.id.two:
                startActivity(new Intent(getBaseContext(), Activity_Ticketing.class));
                break;
            case R.id.three:
                startActivity(new Intent(getBaseContext(), TrainSchedule.class));
                break;
        }


    }


}