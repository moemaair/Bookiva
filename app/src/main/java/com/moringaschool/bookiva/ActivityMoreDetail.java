package com.moringaschool.bookiva;

import android.graphics.Color;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.moringaschool.bookiva.model.Items;

public class ActivityMoreDetail extends AppCompatActivity {
    ImageView ImageViewMoreDetail;
    TextView rate, pageCount, publishedDate, bookTitle, descriptionOfBook;

    Items items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_of_the_book);

        rate = (TextView) findViewById(R.id.rate);
        pageCount = (TextView) findViewById(R.id.pageCount);
        publishedDate = (TextView) findViewById(R.id.publishedDate);
        bookTitle = (TextView) findViewById(R.id.bookTitle);
        descriptionOfBook = (TextView) findViewById(R.id.descriptionOfBook);
        ImageViewMoreDetail = (ImageView) findViewById(R.id.ImageViewMoreDetail);

//        Picasso.get().load(getIntent().getStringExtra("ImageViewMoreDetail")).into(ImageViewMoreDetail);

        Glide.with(this).load(getIntent().getStringExtra("ImageViewMoreDetail"))
                .into(ImageViewMoreDetail);
        rate.setText(getIntent().getIntExtra("rate", 0) + "/" + "5");
        pageCount.setText(getIntent().getStringExtra("pageCount"));
        publishedDate.setText(getIntent().getStringExtra("publishedDate"));
        bookTitle.setText(getIntent().getStringExtra("bookTitle"));

        if(descriptionOfBook.getText() == " "){
            descriptionOfBook.setText("No Description");
            descriptionOfBook.setGravity(Gravity.CENTER);
        }
        else {
            descriptionOfBook.setText(getIntent().getStringExtra("descriptionOfBook"));
        }

    }

}