package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mhcet extends AppCompatActivity {

    Button appmhcet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhcet);

        appmhcet = (Button)findViewById(R.id.applymhcet);

        appmhcet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dtemaharashtra.gov.in/mhtcet2019/"));
                startActivity(browserIntent);
            }
        });

    }
}
