package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class  MainActivity extends AppCompatActivity {

    int RC_SIGN_IN = 0;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    EditText email, password;
    Button login, signup, fpass;
    ImageView homelogo;
    private FirebaseAuth mAuth;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signInButton = findViewById(R.id.sign_in_button);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null)
            startActivity(new Intent(getApplicationContext(), HomePage.class));

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
                Intent i = new Intent("com.example.myapplication.Main3Activity");
                startActivity(i);
            }
        });

        fpass = (Button) findViewById(R.id.fpass);
        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent temp = new Intent(MainActivity.this, forgotpassword.class);
                startActivity(temp);
            }
        });

        email = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        login = (Button) findViewById(R.id.button2);
        signup = (Button) findViewById(R.id.button);
        homelogo = (ImageView) findViewById(R.id.imageView4);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.example.myapplication.Main2Activity");
                startActivity(i);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String logmail = email.getText().toString();

                String logpass = password.getText().toString();
                if (logmail.isEmpty()) {
                    email.setError("Enter email");
                } else if (logpass.isEmpty()) {
                    password.setError("Enter password");
                } else {
                    mAuth.signInWithEmailAndPassword(logmail, logpass)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                        FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
                                        boolean emailVerified = user1.isEmailVerified();

                                        if(emailVerified==false)
                                        {
                                            Toast.makeText(MainActivity.this,"Please verify your email",Toast.LENGTH_LONG).show();
                                            FirebaseAuth.getInstance().signOut();
                                        }

                                        else{
                                        Log.d("Output", "signInWithEmail:success");
                                        Toast.makeText(MainActivity.this, "Success!!", Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent i1 = new Intent("com.example.myapplication.HomePage");
                                        startActivity(i1);
                                    } }else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("Output", "signInWithEmail:failure", task.getException());
                                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });

                }
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            startActivity(new Intent(MainActivity.this, HomePage.class));
        } catch (ApiException e) {
            Log.w("Error", "Sign in result failed, error code:" + e.getStatusCode());
            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }
}