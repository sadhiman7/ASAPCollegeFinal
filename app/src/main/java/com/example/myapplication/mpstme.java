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

    TextView Name;
    TextView Info;
    TextView hdp, hip, adp, aip;
    Button npat;
    ImageView mpstme;

    Firebase mName, mInfo, mHDom, mHInt, mADom, mAInt;

    @Override
    protected void onStart() {
        super.onStart();
        mInfo = new Firebase("https://collegedatabase-4d4ec.firebaseio.com/Colleges/MPSTME/Info");
        mName = new Firebase("https://collegedatabase-4d4ec.firebaseio.com/Colleges/MPSTME/Name");
        mADom = new Firebase("https://collegedatabase-4d4ec.firebaseio.com/Colleges/MPSTME/Placement Package/B Tech/Domestic/Average");
        mAInt = new Firebase("https://collegedatabase-4d4ec.firebaseio.com/Colleges/MPSTME/Placement Package/B Tech/International/Average");
        mHDom = new Firebase("https://collegedatabase-4d4ec.firebaseio.com/Colleges/MPSTME/Placement Package/B Tech/Domestic/Highest");
        mHInt = new Firebase("https://collegedatabase-4d4ec.firebaseio.com/Colleges/MPSTME/Placement Package/B Tech/International/Highest");


        Name = (TextView)findViewById(R.id.name);
        Info = (TextView)findViewById(R.id.info);
        hdp = (TextView)findViewById(R.id.hdompack);
        hip = (TextView)findViewById(R.id.hintpack);
        adp = (TextView)findViewById(R.id.adompack);
        aip = (TextView)findViewById(R.id.aintpack);

        mADom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.getValue(String.class);
                adp.setText(temp);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mHDom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.getValue(String.class);
                hdp.setText(temp);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mAInt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.getValue(String.class);
                aip.setText(temp);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mHInt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.getValue(String.class);
                hip.setText(temp);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue(String.class);
                Name.setText(name);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String info = dataSnapshot.getValue(String.class);
                Info.setText(info);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

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
        Intent intent = new Intent (this, npat_form.class);
        startActivity(intent);
    }
}
