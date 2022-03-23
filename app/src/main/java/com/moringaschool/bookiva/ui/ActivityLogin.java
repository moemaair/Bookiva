package com.moringaschool.bookiva.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.bookiva.R;

import butterknife.ButterKnife;

public class ActivityLogin extends AppCompatActivity{
   private Button toSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_login);
        toSignUp = findViewById(R.id.signup_button_activity);
        toSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityLogin.this, ActivitySignup.class);
                startActivity(intent);
            }
        });
    }


}