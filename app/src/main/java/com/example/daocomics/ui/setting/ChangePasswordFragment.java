package com.example.daocomics.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.daocomics.MainActivity;
import com.example.daocomics.R;
import com.example.daocomics.ui.login_register.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.nio.file.FileStore;

public class ChangePasswordFragment extends Fragment {
    TextInputLayout a,b,c;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_password, container, false);
         a = v.findViewById(R.id.edCurPass);
         b = v.findViewById(R.id.edNewPass);
         c = v.findViewById(R.id.edReNewPass);
        MaterialButton s = v.findViewById(R.id.btnSaveChange);
        ImageButton back = v.findViewById(R.id.btnBack);
        check();
        s.setOnClickListener(vs->savePassChange());
        back.setOnClickListener(bk->getActivity().finish());
        return v;
    }

    private void savePassChange() {
        String a1 =  a.getEditText().getText().toString().trim();
        String b1 =  b.getEditText().getText().toString().trim();
        String c1 =  c.getEditText().getText().toString().trim();
        if(a.getError()==null && b.getError()== null && !a1.isEmpty() && !b1.isEmpty() && !c1.isEmpty() )
        {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            user.updatePassword(c1).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) {
                       /* FirebaseFirestore.getInstance().collection("Users").document(user.getUid()).update("passW",c1);*/
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(getActivity(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        getActivity().startActivity(new Intent(getActivity(),LoginActivity.class));
                    }
                }
            });
        }
        else
            Toast.makeText(getActivity(), "Không được để trống", Toast.LENGTH_SHORT).show();
    }

    private void check() {
        a.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkCurPass(a.getEditText().getText().toString().trim());
            }
        });
        b.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(b.getEditText().getText().length() < 8 )
                   b.setError("Mật khẩu phải có ít nhất 8 ký tự");
                else b.setError(null);
            }
        });
        c.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!c.getEditText().getText().toString().equals(b.getEditText().getText().toString())) {

                    c.setError("Không khớp với mật khẩu đã nhập");
                }
                else c.setError(null);
            }
        });
    }

    void checkCurPass(String cur){
        if(cur.length()<8){
            a.setError("Hãy nhập mật khẩu hiện tại của bạn");
            return;
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(),cur);
        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                    a.setError(null);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                a.setError("Sai mật khẩu");
            }
        });

    }
}