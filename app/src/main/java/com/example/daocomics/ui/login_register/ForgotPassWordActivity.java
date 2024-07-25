package com.example.daocomics.ui.login_register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.daocomics.R;
import com.example.daocomics.databinding.ActivityForgotPassWordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ForgotPassWordActivity extends AppCompatActivity {
    ActivityForgotPassWordBinding binding;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass_word);
        binding = ActivityForgotPassWordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnCancel.setOnClickListener(v-> finish());
        binding.btnSend.setOnClickListener(v->sendRP());
    }

    private void sendRP() {
        email = binding.emailFG.getEditText().getText().toString().trim();
        if(!checkEmailType(email)){
            binding.emailFG.setError("Email không hợp lệ");
            return;
        }
        binding.emailFG.setError(null);


        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(ForgotPassWordActivity.this, "Xin vui lòng kiểm tra hộp thư của bạn", Toast.LENGTH_LONG).show();
                        ForgotPassWordActivity.this.finish();
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