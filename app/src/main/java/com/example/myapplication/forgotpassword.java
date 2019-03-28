package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {

    EditText fpass;
    Button res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        fpass = (EditText)findViewById(R.id.editText);
        res = (Button)findViewById(R.id.reset);

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = fpass.getText().toString();
                if(em==null){
                    Toast.makeText(forgotpassword.this, "Please enter an email!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    FirebaseAuth.getInstance().sendPasswordResetEmail(em)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(forgotpassword.this, "EMail Sent!", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(forgotpassword.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                }
            }
        });

    }
}
