package com.example.daocomics.ui.login_register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import com.example.daocomics.R;
import com.example.daocomics.Utils;
import com.example.daocomics.databinding.ActivityLoginBinding;
import com.example.daocomics.databinding.ActivityRegisterBinding;
import com.example.daocomics.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        sharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        event();
        
    }

    private void event() {
        boolean isValid = CheckValid();
        binding.btnCreateUS.setOnClickListener(v->regis(isValid));
        binding.btnBack.setOnClickListener(v->finish());


    }

    private boolean CheckValid(){
        //Check UsName length + Usname Exis
        binding.edRegisterUserName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(binding.edRegisterUserName.getEditText().getText().length()<=6)
                    binding.edRegisterUserName.setError("Tên đăng nhập phải có ít nhất 7 ký tự");
                else {
                    firestore.collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                String s = documentSnapshot.getString("usName");
                                if (binding.edRegisterUserName.getEditText().getText().toString().equals(s))
                                    binding.edRegisterUserName.setError("Tài khoản đã tồn tại");
                                else binding.edRegisterUserName.setError(null);
                            }
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        //Check UsPass length
        binding.edRegisterUserPassWord.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(binding.edRegisterUserPassWord.getEditText().getText().length() < 8 )
                    binding.edRegisterUserPassWord.setError("Mật khẩu phải có ít nhất 8 ký tự");
                else binding.edRegisterUserPassWord.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //Check RePass
        binding.edRegisterRePassWord.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!binding.edRegisterRePassWord.getEditText().getText().toString().equals(binding.edRegisterUserPassWord.getEditText().getText().toString())) {

                    binding.edRegisterRePassWord.setError("Không khớp với mật khẩu đã nhập");
                }
                else binding.edRegisterRePassWord.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
        //Check Email
        binding.edRegisterEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!checkEmailType(binding.edRegisterEmail.getEditText().getText().toString()))
                    binding.edRegisterEmail.setError("Không đúng định dạng email");
                else  binding.edRegisterEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return  binding.edRegisterUserName.getError()==null
                && binding.edRegisterUserPassWord.getError()==null
                && binding.edRegisterRePassWord.getError()==null
                && binding.edRegisterEmail.getError()==null;

    }

    private void regis(Boolean isValid) {
        String name = binding.edRegisterName.getText().toString().trim();
        String username = binding.edRegisterUserName.getEditText().getText().toString().trim();
        String  pass = binding.edRegisterUserPassWord.getEditText().getText().toString().trim();
        String repass = binding.edRegisterRePassWord.getEditText().getText().toString().trim();
        String phone = binding.edRegisterUserPhoneNb.getText().toString().trim();
        String email = binding.edRegisterEmail.getEditText().getText().toString().trim();

        if(!isValid || name.length()<1 || pass.length()<1 || repass.length()<1 || username.length()<1 || email.length()<1) {
            Toast.makeText(RegisterActivity.this,"Vui lòng điền đúng thông tin",Toast.LENGTH_LONG).show();
            return;
        }

        User us = new User(name,username,phone,email);

        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firestore.collection("Users")
                            .document(FirebaseAuth.getInstance().getUid())
                            .set(us);
                        Toast.makeText(RegisterActivity.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                   finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Email đã đăng ký",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
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
}