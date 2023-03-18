package com.example.app_truyen_tranh.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_truyen_tranh.R;

public class RegisterActivity extends AppCompatActivity {
    Button imaLogoJ;
    Button signIn, signUpnow;
    EditText edUserNamedb, edPassdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");
        imaLogoJ=findViewById(R.id.imaLogo);
        imaLogoJ.setOnClickListener(view -> finish());

    }
}