package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class npat_form extends AppCompatActivity {
    EditText fname, lname;
    Button apply;

    DatabaseReference reff;

    Firebase nForms;

    public static int noofforms;

    Forms forms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npat_form);

        nForms = new Firebase("https://collegedatabase-4d4ec.firebaseio.com/nForms");

        fname = (EditText) findViewById(R.id.infname);
        lname = (EditText) findViewById(R.id.inlname);
        apply = (Button) findViewById(R.id.npat);

        forms = new Forms();
        reff = FirebaseDatabase.getInstance().getReference().child("Forms");

        nForms.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nforms = dataSnapshot.getValue(String.class);
                noofforms = Integer.parseInt(nforms);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = fname.getText().toString();
                String lastname = lname.getText().toString();

                if (firstname.isEmpty()) {
                    fname.setError("Please Enter a First Name");
                    fname.requestFocus();
                    return;
                } else if (lastname.isEmpty()) {
                    lname.setError("Please Enter a First Name");
                    lname.requestFocus();
                    return;
                } else {
                    String formfname = fname.getText().toString();
                    String formlname = lname.getText().toString();

                    forms.setFname(formfname);
                    forms.setLname(formlname);


                    noofforms = noofforms + 1;
                    String snforms = Integer.toString(noofforms);

                    nForms.setValue(noofforms);

                    reff.child(snforms).setValue(forms);


                }
            }

        });
    }
}

