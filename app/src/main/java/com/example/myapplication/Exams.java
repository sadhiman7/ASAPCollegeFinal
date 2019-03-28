package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Exams extends AppCompatActivity {

    CardView npat, mhcet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);

        mhcet=(CardView)findViewById(R.id.mhcetinfo);
        npat=(CardView)findViewById(R.id.npatinfo);

        mhcet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( Exams.this,mhcet.class));
            }
        });

        npat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Exams.this,npat_info.class));
            }
        });

    }
}
