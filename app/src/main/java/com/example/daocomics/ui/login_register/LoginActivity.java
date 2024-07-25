package com.example.daocomics.ui.login_register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.example.daocomics.MainActivity;
import com.example.daocomics.R;
import com.example.daocomics.Utils;
import com.example.daocomics.adapter.ScreenSlidePageAdapter;
import com.example.daocomics.databinding.ActivityLoginBinding;
import com.example.daocomics.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        sharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        getsavedLogin();
        clicked();

    }

    private void getsavedLogin() {

        String usnSaved = sharedPreferences.getString(Utils.ACCOUNT_RMB_US_LOGIN, "");
        String uspSaved = sharedPreferences.getString(Utils.ACCOUNT_RMB_PASS, "");
        if(usnSaved.isEmpty())
            return;
        binding.cbGhiNhoDN.setChecked(true);
        binding.edLoginUsername.getEditText().setText(usnSaved);
        binding.edLoginPassword.getEditText().setText(uspSaved);

        binding.edLoginUsername.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.cbGhiNhoDN.setChecked(false);
                binding.edLoginPassword.getEditText().setText(null);
            }
        });



    }

    public void clicked() {
        binding.btnLogin.setOnClickListener(v->login());
        binding.btnRegis.setOnClickListener(v->register());
        binding.btQuenMK.setOnClickListener(v->forgotpass());
    }

    private void forgotpass() {
        startActivity(new Intent(this,ForgotPassWordActivity.class));
    }

    private void register() {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    private void login() {
        String usname = binding.edLoginUsername.getEditText().getText().toString().trim();
        String pass = binding.edLoginPassword.getEditText().getText().toString().trim();

        if(checkEmailType(usname)){
            firebaseAuth.signInWithEmailAndPassword(usname,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    getUsdataandNext();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this,"Sai tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                }
            });
        }

        firestore.collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    String s = documentSnapshot.getString("usName");
                    if (usname.equals(s)){
                        String e = documentSnapshot.getString("email");
                        firebaseAuth.signInWithEmailAndPassword(e,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                getUsdataandNext();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this,"Sai tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
            }
        });
    }
    private void getUsdataandNext(){
        savedLogin();
        Intent i = new Intent(LoginActivity.this,MainActivity.class);
        LoginActivity.this.finish();
        startActivity(i);
    }

    private boolean checkEmailType(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    void savedLogin(){
        if(binding.cbGhiNhoDN.isChecked())
        {
            String savedLUSn = binding.edLoginUsername.getEditText().getText().toString().trim();
            String savedLUSp = binding.edLoginPassword.getEditText().getText().toString().trim();
            editor.putString(Utils.ACCOUNT_RMB_US_LOGIN, savedLUSn);
            editor.putString(Utils.ACCOUNT_RMB_PASS, savedLUSp);
            editor.commit();
        }
        else if(!binding.cbGhiNhoDN.isChecked()){
            editor.remove(Utils.ACCOUNT_RMB_US_LOGIN);
            editor.remove(Utils.ACCOUNT_RMB_PASS);
            editor.commit();
        }
    }


}