package com.example.app_truyen_tranh.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_truyen_tranh.MainActivity;
import com.example.app_truyen_tranh.R;

public class LoginActivity extends AppCompatActivity {
    Button signIn, signUpnow;
    EditText edUserNamedb, edPassdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");
        anhxa();
        signIn.setOnClickListener(funLogin());
        signUpnow.setOnClickListener(funRegister());


    }

    @NonNull
    private View.OnClickListener funLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUserNamedb.getText().toString().trim();
                String password = edPassdb.getText().toString().trim();
                if (checkUserName(username) && checkPassword(password)) {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        };
    }

    @NonNull
    private View.OnClickListener funRegister() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        };
    }


    private boolean checkPassword(String password) {
        if(password.isEmpty()){
            edPassdb.setError("Vui Long Nhap");
            return false;
        }
        return true;
    }

    private boolean checkUserName(String username) {
        if(username.isEmpty()){
            edUserNamedb.setError("Vui long nhap");
            return false;
        }
        return true;
    }

    private void anhxa() {
        signIn =findViewById(R.id.btnLog);
        signUpnow=findViewById(R.id.btnSignUp);
        edUserNamedb=findViewById(R.id.edUser_log);
        edPassdb=findViewById(R.id.edPass_log);
    }
}