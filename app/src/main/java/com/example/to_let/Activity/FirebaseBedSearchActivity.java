package com.example.to_let.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_let.Adapter.FirebaseSearchAdapter;
import com.example.to_let.Model.FirebaseSearchModel;
import com.example.to_let.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseBedSearchActivity extends AppCompatActivity  {
    private SearchView searchView;
    private RecyclerView recyclerView;
    private FirebaseSearchAdapter adapter;
    private ArrayList<FirebaseSearchModel> FirebaseSearchList;

    DatabaseReference dbArtists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_bed_search);
        searchView=findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,linearLayoutManager.getOrientation());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        //1. SELECT * FROM Artists
        dbArtists = FirebaseDatabase.getInstance().getReference().child("PostInformation");
        Query query = FirebaseDatabase.getInstance().getReference("PostInformation")
                .orderByChild("bedroom")
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
            adapter = new FirebaseSearchAdapter (FirebaseBedSearchActivity.this, FirebaseSearchList);
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
    @Override
    protected void onStart() {
        super.onStart();

        if(searchView!=null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }

    private void search(String str){
        ArrayList<FirebaseSearchModel> myList=new ArrayList<>();
        for(FirebaseSearchModel object: FirebaseSearchList){
            if(object.getBedroom().toLowerCase().contains(str.toLowerCase())){
                myList.add(object);
            }

            adapter = new FirebaseSearchAdapter(this,myList);
            recyclerView.setAdapter(adapter);
        }

    }


}
