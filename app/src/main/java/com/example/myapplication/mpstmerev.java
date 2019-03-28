package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class mpstmerev extends AppCompatActivity {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;
    Button submitrev;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Blog,BlogViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>
                (Blog.class, R.layout.blog_row, BlogViewHolder.class, mDatabase){

            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position){

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());

            }

        };

        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        public BlogViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;
        }
        public void setTitle(String title)
        {
            TextView post_title = (TextView)mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }
        public void setDesc(String desc)
        {
            TextView post_desc = (TextView)mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpstmerev);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("MPSTMEReviews");
        mDatabase.keepSynced(true);

        mBlogList=(RecyclerView)findViewById(R.id.myrecycleview);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

        submitrev = (Button)findViewById(R.id.inrev);
        submitrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent temp = new Intent(mpstmerev.this, reviewsubmit.class);
                startActivity(temp);
            }
        });



    }
}
