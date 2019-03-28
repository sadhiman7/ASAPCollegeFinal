package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class mpstme extends AppCompatActivity {

    Button npat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpstme);

        npat = (Button)findViewById(R.id.npat);
        npat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNPAT();
            }
        });
    }
    public void openNPAT()
    {
        Intent intent = new Intent (this, npat_info.class);
        startActivity(intent);
    }
}
