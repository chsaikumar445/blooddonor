package com.example.team6project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Doonors extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AdminAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<AdminItem> bookitem;
    public DatabaseReference databaseReference;
    ProgressBar progressBar;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doonors);

        bookitem=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("Donors");


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdminAdapter(bookitem);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        auth=FirebaseAuth.getInstance();
        firebaseUser =auth.getCurrentUser();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        ReadWriteItemDetails post=dataSnapshot1.getValue(ReadWriteItemDetails.class);

                        Date dt = new Date();
                        SimpleDateFormat dateFormat;
                        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String date=""+dateFormat.format(dt);

                        bookitem.add(new AdminItem(post.name,post.hospitalAddress,post.mobile));
                    }

                }
//                progressBar.setVisibility(View.GONE);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}