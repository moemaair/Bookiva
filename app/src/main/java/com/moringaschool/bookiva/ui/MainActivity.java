package com.moringaschool.bookiva.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.bookiva.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.loginbutton) Button loginbutton;
    @BindView(R.id.signupbutton) Button signupbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loginbutton.setOnClickListener(this);
        signupbutton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == loginbutton){
            Intent intent = new Intent(MainActivity.this, ActivityLogin.class);
            startActivity(intent);
            finish();

        }
        if(view == signupbutton){
            Intent intent = new Intent(MainActivity.this, ActivitySignup.class);
            startActivity(intent);
            finish();
        }
    }
}