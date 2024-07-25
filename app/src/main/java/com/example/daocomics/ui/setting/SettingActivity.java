package com.example.daocomics.ui.setting;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.daocomics.R;
import com.example.daocomics.Utils;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.IOException;

public class SettingActivity extends AppCompatActivity {
    private ViewProfileFragment viewProfileFragment = new ViewProfileFragment();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        int x = getIntent().getIntExtra("fragment",0);

        chooseFragment(x);
    }


    private void chooseFragment(int x)
    {
        switch(x) {
            case 1:
                loadFragment(viewProfileFragment);
                return ;
            case 2:
                loadFragment(new ChangePasswordFragment());
                return ;
            case 3:
                loadFragment(new FeedbackFragment());
                return ;
            case 4:
                loadFragment(new AboutUsFragment());
                return ;
        }
    }
    void loadFragment(Fragment fmNew)
    {
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.setting_frag,fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }

    public void openGallery(){
        ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        viewProfileFragment.setUri(uri);

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SettingActivity.this,"OK",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            viewProfileFragment.setBitmapImageView(bitmap);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}