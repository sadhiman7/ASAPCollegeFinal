package com.example.myapplication;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public int[] slide_image = {
            R.drawable.collegeinfo,
            R.drawable.compexam,
            R.drawable.docup
    };

    public String[] slide_headings = {
            "COLLEGEINFO",
            "EXAMINFO",
            "DOCUMENTUPLOAD"
    };

    public String[] slide_desc = {
            "asap asap asap asap asap asap asap asap asap asap asap asap asap asap asap asap asap asap asap asap",
            "asap asap asap asap asap asap asap asap asap asap asap asap asap asap asap asap asap asap asap asap",
            "asap asap asap asap asap asap asap asap asap asap asap asap asap asap asap asap asap asap asap asap "
        };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slidelayout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeadings = (TextView) view.findViewById(R.id.slide_headings);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_image[position]);
        slideHeadings.setText(slide_headings[position]);
        slideDescription.setText(slide_desc[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((RelativeLayout)object);
    }

}
