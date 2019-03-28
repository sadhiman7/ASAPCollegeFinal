package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class npat_info extends AppCompatActivity {

    Button formnpat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npat_info);

        formnpat = (Button) findViewById(R.id.npat);

        formnpat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/DshEAYDeCWy2CC2h9"));
                startActivity(browserIntent);
                startActivity(new Intent(npat_info.this,DocUpload.class));
            }
    });}
}
