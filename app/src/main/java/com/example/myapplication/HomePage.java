package com.example.myapplication;

import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {

    CardView DocUpload;
    boolean doubleBackToExitPressedOnce = false;
    CardView ClgInfo, ExamInfo;
    Button logout;
    GoogleSignInClient nGoogleSignInClient;
    GoogleApiClient mGoogleApiClient;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    protected void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ClgInfo = (CardView)findViewById(R.id.clginfo);
        ExamInfo = (CardView)findViewById(R.id.examinfo);
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
        ExamInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openexaminfo();
            }
        });

        logout = (Button) findViewById(R.id.button3);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                finish();
            }
        });



    }
    public void openDocUpload()
    {
        Intent intent = new Intent(this, DocUpload.class);
        startActivity(intent);
    }


    public void openexaminfo()
    {
        Intent intent = new Intent(this, Exams.class);
        startActivity(intent);
    }

    public void opencollegeinfo()
    {
        Intent intent = new Intent(this, collegeinfo.class);
        startActivity(intent);
    }

    private void signOut()
    {
        FirebaseAuth.getInstance().signOut();
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status){
                        startActivity(new Intent(HomePage.this, MainActivity.class));
                    }
                });
    }
}
