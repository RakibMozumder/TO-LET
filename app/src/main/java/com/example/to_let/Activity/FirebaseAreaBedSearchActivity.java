package com.example.to_let.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.to_let.R;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_let.Adapter.FirebaseSearchAdapter;
import com.example.to_let.Model.FirebaseSearchModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseAreaBedSearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseSearchAdapter adapter;
    private ArrayList<FirebaseSearchModel> FirebaseSearchList;

    DatabaseReference dbArtists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_area_bed_search);



        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,linearLayoutManager.getOrientation());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        //1. SELECT * FROM Artists
        dbArtists = FirebaseDatabase.getInstance().getReference().child("PostInformation");
        Query query = FirebaseDatabase.getInstance().getReference("PostInformation")
                .orderByChild("area").orderByChild("bedroom")
                // .equalTo("3")
                ;
        query.addListenerForSingleValueEvent(valueEventListener);
    }

        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                FirebaseSearchList = new ArrayList<FirebaseSearchModel>();
                // FirebaseSearchList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    //   UserInformation userInformation = dataSnapshot1.child(userID).getValue(UserInformation.class);

                    FirebaseSearchList.add(dataSnapshot1.getValue(FirebaseSearchModel.class));
                }
                adapter = new FirebaseSearchAdapter (FirebaseAreaBedSearchActivity.this, FirebaseSearchList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };


}

