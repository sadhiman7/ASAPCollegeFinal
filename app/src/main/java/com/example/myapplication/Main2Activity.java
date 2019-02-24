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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {
    EditText ename, epass, cpass, email;
    Button register;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ename = (EditText) findViewById(R.id.editText5);
        email = (EditText) findViewById(R.id.editText6);
        epass = (EditText) findViewById(R.id.editText2);
        cpass = (EditText) findViewById(R.id.editText4);
        register = (Button) findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ename.getText().toString();
                String mail = email.getText().toString();
                String pass = epass.getText().toString();
                String con_pass = cpass.getText().toString();
                if (!con_pass.equals(pass)) {
                    cpass.setError("Password is wrong. Write it again");
                    cpass.requestFocus();
                    return;
                }
                if (mail.indexOf('@') == -1) {
                    email.setError("Invalid email entered");
                    email.requestFocus();
                    return;
                }
                if (mail.isEmpty()) {
                    email.setError("Invalid email");
                    email.requestFocus();
                    return;
                }
                if (pass.isEmpty()) {
                    epass.setError("Invalid password");
                    epass.requestFocus();
                    return;
                }
                if (pass.length() < 6) {
                    epass.setError("Password should be minimum of 6 characters");
                    epass.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(mail, pass)
                        .addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Log.d("output", "createUserWithEmail:success");
                                    Toast.makeText(Main2Activity.this, "Success.",Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    finish();
                                } else {

                                    Log.w("output", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Main2Activity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
            }
        });
    }
}

