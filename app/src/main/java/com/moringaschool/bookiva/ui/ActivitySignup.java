package com.moringaschool.bookiva.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.moringaschool.bookiva.R;

import butterknife.ButterKnife;

public class ActivitySignup extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ActivitySignup.class.getSimpleName();

    private EditText email, username, password, confirm_password;

    //Firebase
     FirebaseAuth mAuth;
     FirebaseAuth.AuthStateListener mAuthStateListener;


    private Button toLogIn;
    private Button signupbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        //data binding
        setContentView(R.layout.activity_signup);
        email = findViewById(R.id.email_signup);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password_signup);
        confirm_password = findViewById(R.id.confirm_password);
        toLogIn = findViewById(R.id.login_back);
        signupbtn = findViewById(R.id.signup_button_activity);


        signupbtn.setOnClickListener(this);
        toLogIn.setOnClickListener(this);

        createAuthStateListener();

    }

    @Override
    public void onClick(View view) {
       if(view == signupbtn ){
           createNewUser();
       }
       if(view == toLogIn){
           Intent intent = new Intent(ActivitySignup.this, ActivityLogin.class);
           startActivity(intent);
       }
    }

    private void createNewUser() {
        final String name = username.getText().toString().trim(); //geting username Widget text
        final String email_address = email.getText().toString().trim(); //geting email Widget text
        String mPassword = password.getText().toString().trim();
        String confirmPassword = confirm_password.getText().toString().trim();

        boolean validEmail = isValidEmail(email_address);
        boolean validName = isValidName(name);
        boolean validPassword = isValidPassword(mPassword, confirmPassword);

        if (!validEmail || !validName || !validPassword) return;

        mAuth.createUserWithEmailAndPassword(email_address, mPassword).addOnCompleteListener(this,task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "Authentication successful");
                Toast.makeText(ActivitySignup.this, "log in successfully", Toast.LENGTH_SHORT).show();
            } else {
                Log.e(TAG, "createNewUser:", task.getException());
                Toast.makeText(ActivitySignup.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    private boolean isValidEmail(String email_address) {
        boolean isGoodEmail = (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email_address).matches());
        if (!isGoodEmail) {
            email.setError("Please enter a valid email address");
            return false;
        }
        return true;
    }

    private boolean isValidName(String name) {
        if (name.equals("")) {
            username.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String mPassword, String confirmPassword) {
        if (password.length() < 6) {
            password.setError("Please create a password containing at least 6 characters");
            return false;}
//        } else if (!password.equals(confirmPassword)) {
//            confirm_password.setError("Passwords do not match");
//            return false;
//        }
        return true;
    }

    private void createAuthStateListener() {
        mAuthStateListener = new FirebaseAuth.AuthStateListener() { // we want to inform the app for success authentication
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
//                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (firebaseAuth.getCurrentUser() != null) {
                    Intent intent = new Intent(ActivitySignup.this, ActivityLogin.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }




}