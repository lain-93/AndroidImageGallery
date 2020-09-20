package com.example.imagegallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.imagegallery.Data.FeaturedItem;

public class Details extends AppCompatActivity {

    ImageView imageView;
    TextView  textIdView;
    TextView  textUserView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView = (ImageView) findViewById(R.id.detailImage);
        textIdView = (TextView) findViewById(R.id.textId);
        textUserView = (TextView) findViewById(R.id.textUser);

        Bundle bundle = getIntent().getExtras();
        FeaturedItem fi = (FeaturedItem) bundle.getSerializable("itemObject");

        textIdView.setText(fi.getId());
        textUserView.setText(fi.getUser());
        Glide.with(imageView.getContext()).load(fi.getSmallImage())
                .into(imageView);
    }
}