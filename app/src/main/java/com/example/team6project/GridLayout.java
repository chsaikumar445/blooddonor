package com.example.team6project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class GridLayout extends AppCompatActivity {

    ImageView donate,hospital,firstAid,donors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);

        donate =findViewById(R.id.alrerts_img);
        donors=findViewById(R.id.donors_img);
        hospital=findViewById(R.id.hospital_img);
        firstAid=findViewById(R.id.first_aid_img);

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),DonorDeatails.class);
                startActivity(i);
            }
        });

        donors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Doonors.class);
                startActivity(i);
            }
        });

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),BloodGroup.class);
                startActivity(i);
            }
        });

        firstAid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FirstAidDetails.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);

        MenuItem logoutItem=menu.findItem(R.id.action_logout);

        logoutItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                finish();
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}