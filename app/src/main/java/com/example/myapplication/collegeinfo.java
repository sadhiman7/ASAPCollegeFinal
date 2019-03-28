package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class collegeinfo extends AppCompatActivity {

    CardView mpstme, djsce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collegeinfo);

        mpstme = (CardView)findViewById(R.id.mpstme);
        djsce = (CardView)findViewById(R.id.djsce);

        mpstme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmpstme();
            }
        });
        djsce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(collegeinfo.this,djsce.class));
            }
        });
    }

    public void openmpstme()
    {
        Intent intent = new Intent(this, mpstme.class);
        startActivity(intent);
    }
}
