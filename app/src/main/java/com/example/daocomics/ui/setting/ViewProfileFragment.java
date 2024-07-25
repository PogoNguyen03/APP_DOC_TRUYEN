package com.example.daocomics.ui.setting;

import static com.example.daocomics.Utils.MY_REQUEST_CODE;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.daocomics.MainActivity;
import com.example.daocomics.R;
import com.example.daocomics.databinding.FragmentViewProfileBinding;
import com.example.daocomics.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ViewProfileFragment extends Fragment {
    FirebaseUser user;
    FragmentViewProfileBinding binding;
    Uri uri;

    Dialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentViewProfileBinding.inflate(inflater, container, false);
        View v = binding.getRoot();
        user = FirebaseAuth.getInstance().getCurrentUser();
        loadInfo();
        dialog = new Dialog(getActivity());

        event();
        return v;
    }

    private void event() {
        binding.btnChangeUsName.setOnClickListener(v->updateUsNametoFB());
        binding.btnBack.setOnClickListener(v->back());
        binding.btnChangeUsPhone.setOnClickListener(v->updateUsPhonetoFB());
        binding.imgUsAvaProfile.setOnClickListener(v->changeAva());
    }

    private void changeAva() {
        SettingActivity temp = (SettingActivity) getActivity();
        if(temp==null)
            return;
        // Choose image
        temp.openGallery();
    }

    private void updateUsPhonetoFB() {
        String x = binding.tvUsPhoneProfile.getText().toString().trim();

        dialog.setContentView(R.layout.diaglog_change_usphone);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        MaterialButton cancel = dialog.findViewById(R.id.btnCancel);
        MaterialButton save = dialog.findViewById(R.id.btnSave);
        EditText phone = dialog.findViewById(R.id.edtPhone);
        phone.setText(x);
        cancel.setOnClickListener(v-> dialog.dismiss());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = phone.getText().toString();
                FirebaseFirestore.getInstance().collection("Users").document(user.getUid()).update("phoneNb",t);
                loadInfo();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void back() {
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

    private void updateUsNametoFB() {

        String x = binding.tvUsNameProfile.getText().toString().trim();

        dialog.setContentView(R.layout.diaglog_change_usname);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        MaterialButton cancel = dialog.findViewById(R.id.btnCancel);
        MaterialButton save = dialog.findViewById(R.id.btnSave);
        EditText usname = dialog.findViewById(R.id.edtName);
        usname.setText(x);
        cancel.setOnClickListener(v-> dialog.dismiss());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = usname.getText().toString().trim();
                if(t.length()<1)
                    t = "Ẩn danh";
                FirebaseFirestore.getInstance().collection("Users").document(user.getUid()).update("name",t);
                loadInfo();
                dialog.dismiss();
            }
        });
        dialog.show();
    }



    private void loadInfo() {
        FirebaseFirestore.getInstance().collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    String s = documentSnapshot.getString("email");
                    if(user.getEmail().equals(s)){
                        User us = documentSnapshot.toObject(User.class);
                        binding.tvUsNameProfile.setText(us.getName());
                        binding.tvUsAccProfile.setText(us.getUsName());
                        binding.tvUsMailProfile.setText(us.getEmail());
                        binding.tvUsPhoneProfile.setText(us.getPhoneNb());
                        Glide.with(getActivity()).load(user.getPhotoUrl())
                                        .error(R.drawable.baseline_sentiment_very_satisfied_24).into(binding.imgUsAvaProfile);

                    }
                }
            }
        });
    }
    public void setBitmapImageView(Bitmap bitmap){
        binding.imgUsAvaProfile.setImageBitmap(bitmap);
    }
    public void setUri(Uri uri){
        this.uri = uri;
    }
}