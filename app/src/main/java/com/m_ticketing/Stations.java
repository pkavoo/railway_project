package com.m_ticketing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Polycarp on 8/9/2016.
 */
public class Stations extends Activity {

    Button nairobi,kisumu, nakuru,eldoret,mombasa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stations);
        nairobi=(Button) findViewById(R.id.nairobi);
        kisumu=(Button) findViewById(R.id.kisumu);
        nakuru=(Button) findViewById(R.id.nakuru);
        eldoret=(Button) findViewById(R.id.eldoret);
        mombasa=(Button) findViewById(R.id.mombasa);


        nairobi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), Nairobi.class));
            }
        });

nakuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), Nairobi.class));
            }
        });

  eldoret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), Nairobi.class));
            }
        });

     mombasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), Nairobi.class));
            }
        });

       kisumu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), Nairobi.class));
            }
        });
    }


}
