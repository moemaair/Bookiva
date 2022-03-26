package com.moringaschool.bookiva.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.moringaschool.bookiva.R;

import butterknife.ButterKnife;

public class ActivityLogin extends AppCompatActivity{
    private static final String TAG = ActivityLogin.class.getSimpleName();


    private Button toSignUp;
    private FirebaseAuth mAuth;
    private Button loginUserButton;
    private EditText email_login, password_login;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_login);
        toSignUp = findViewById(R.id.signup_button_activity);
        email_login = findViewById(R.id.email_login);
        password_login = findViewById(R.id.password_login);
        loginUserButton = findViewById(R.id.loginbutton);

        toSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityLogin.this, ActivitySignup.class);
                startActivity(intent);
            }
        });

        loginUserButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                loginWithPassword();
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            //            FirebaseUser user = mAuth.getCurrentUser();// check user in FirebaseBD
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    Intent intent = new Intent(ActivityLogin.this, ActivityDetail.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
        mAuth = FirebaseAuth.getInstance();

    }

    private void loginWithPassword() {
        String email = email_login.getText().toString().trim();
        String pass = password_login.getText().toString().trim();

        if(email.equals("")){
            email_login.setError("Enter your Email!");
            return;
        }
        if(pass.equals("")){
            password_login.setError("Enter your Password! ");
            return;
        }
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        Log.d(TAG, "onComplete: log in" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}