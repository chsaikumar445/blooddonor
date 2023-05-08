package com.example.team6project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class DonorDeatails extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    StorageReference storageReference;
    EditText name,age,gender,mobile,blood_group,hospital_address;
    ImageView item_pic;
    ProgressBar progressBar;
    Button post;
    static final int PICK_IMAGE_REQ=1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_deatails);

        auth=FirebaseAuth.getInstance();
        firebaseUser =auth.getCurrentUser();
        storageReference= FirebaseStorage.getInstance().getReference("PostImages");

        Uri uri= firebaseUser.getPhotoUrl();

        Picasso.with(DonorDeatails.this).load(uri);



        if(firebaseUser == null){
            Intent i=new Intent(getApplicationContext(),Login.class);
            startActivity(i);
            finish();
        }

        getSupportActionBar().setTitle("Post Item");

        name=findViewById(R.id.name_edit_text);
        age=findViewById(R.id.age_edit_tv);
        gender=findViewById(R.id.gender_edit_tv);
        mobile=findViewById(R.id.phone_edit_tv);
        blood_group=findViewById(R.id.blood_group_edit_tv);
        hospital_address=findViewById(R.id.hospital_address_edit_tv);
        progressBar=findViewById(R.id.progress_bar);
        post=findViewById(R.id.post_btn);



        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ItemName = name.getText().toString();
                String ageStr = age.getText().toString();
                String genderStr = gender.getText().toString();
                String mobileStr=mobile.getText().toString();
                String blood_group_str=blood_group.getText().toString();
                String hospital_address_str=hospital_address.getText().toString();

                if (ItemName.length() == 0 || ageStr.length() == 0 || genderStr.length() == 0 ) {
                    Toast.makeText(getApplicationContext(), "Fill All the Above Info", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                try {
                                ReadWriteItemDetails itemDetailsObject = new ReadWriteItemDetails(ItemName, ageStr, genderStr,mobileStr,blood_group_str,hospital_address_str);

                                DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Donors");
                                referenceProfile.child(firebaseUser.getUid()).push().setValue(itemDetailsObject).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                                                Intent i = new Intent(getApplicationContext(), GridLayout.class);
                                                startActivity(i);
                                                finish();
                                            }
                                            else {
                                                Toast.makeText(getApplicationContext(), "Failed To Save Data", Toast.LENGTH_SHORT).show();
                                            }
                                    }
                                });


                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Try Again",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}