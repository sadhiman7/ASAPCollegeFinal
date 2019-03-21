package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;

public class HomePage extends AppCompatActivity {

    CardView DocUpload;
    CardView ClgInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ClgInfo = (CardView)findViewById(R.id.clginfo);
        DocUpload = (CardView)findViewById(R.id.docup);

        DocUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDocUpload();
            }
        });
        ClgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencollegeinfo();
            }
        });
    }
    public void openDocUpload()
    {
        Intent intent = new Intent(this, DocUpload.class);
        startActivity(intent);
    }
    public void opencollegeinfo()
    {
        Intent intent = new Intent(this, collegeinfo.class);
        startActivity(intent);
    }
}
