package com.example.henilsarang.lapitchatapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_login extends AppCompatActivity {

    private Toolbar mToolbar;

    private TextInputLayout mLoginEmail;
    private TextInputLayout mLoginPassword;
    private Button btn_login;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();
        mToolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login to your Account");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        mLoginEmail= findViewById(R.id.login_email);
        mLoginPassword = findViewById(R.id.login_password);
        btn_login = findViewById(R.id.login_btn);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Working","Yes");

                String email = mLoginEmail.getEditText().getText().toString();
                String pass = mLoginPassword.getEditText().getText().toString();

                if(!TextUtils.isEmpty(email ) || !TextUtils.isEmpty(pass)){

                    loginuser(email,pass);
                }

            }

        });



    }

    private void loginuser(String email,String password){

    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {


            if(task.isSuccessful()){
                Intent mainIntent = new Intent(activity_login.this,MainActivity.class);
                //mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
                finish();

            }
            else {
                Toast.makeText(getApplicationContext(),"Cannot sign in please check form and try again",Toast.LENGTH_SHORT).show();

            }

        }
    });


    }
}
