package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class reviewsubmit extends AppCompatActivity {

    EditText review;
    Button submit;

    String name;

    Reviews reviews;

    DatabaseReference reff;

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference dname = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        dname.child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewsubmit);

        review = (EditText)findViewById(R.id.review);
        submit = (Button)findViewById(R.id.submitbutton);

        reviews = new Reviews();
        reff = FirebaseDatabase.getInstance().getReference().child("MPSTMEReviews");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rev = review.getText().toString();
                if (rev==null){
                    Toast.makeText(reviewsubmit.this, "Please enter a review", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    reviews.setTitle(name);
                    reviews.setDesc(rev);
                    reff.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(reviews)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(reviewsubmit.this, "Review Successfully Posted!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(reviewsubmit.this, Reviews.class);
                                    startActivity(intent);
                                }
                            });
                }
            }
        });

    }
}
