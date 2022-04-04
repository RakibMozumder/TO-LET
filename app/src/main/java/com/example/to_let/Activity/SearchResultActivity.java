package com.example.to_let.Activity;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.to_let.Adapter.CustomPostDownloadApapter;
import com.example.to_let.Model.PostInformation;
import com.example.to_let.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity  {
    private Button areaButton,latestButton,priceButton,bedButton,ratingButton;
    private ListView listView;
    DatabaseReference databaseReference;
    private List<PostInformation> postInformationList;
    private CustomPostDownloadApapter customPostDownloadApapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      //  requestWindowFeature(Window.FEATURE_NO_TITLE);  //remove tittle bar

        areaButton=findViewById(R.id.areaId);
        latestButton=findViewById(R.id.latestId);
        priceButton=findViewById(R.id.priceId);
        bedButton=findViewById(R.id.bedId);
        ratingButton=findViewById(R.id.ratingId);
        areaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchResultActivity.this, FirebaseAreaSearchActivity.class);
                startActivity(intent);
            }
        });
        latestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchResultActivity.this, FirebaseBedSearchActivity.class);
                startActivity(intent);
            }
        });
        priceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchResultActivity.this, FirebaseLatestSearchActivity.class);
                startActivity(intent);
            }
        });
        bedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchResultActivity.this, FirebasePriceSearchActivity.class);
                startActivity(intent);
            }
        });
        ratingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchResultActivity.this, FirebaseFlatSizeSearchActivity.class);
                startActivity(intent);
            }
        });
        //ratingButton.setOnClickListener(this);
        databaseReference= FirebaseDatabase.getInstance().getReference("PostInformation");
        postInformationList=new ArrayList<>();
        listView=findViewById(R.id.searchResultListViewId);


    }

   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
    }*/


/*@Override
    public void onClick(View v) {
    if (v.getId()==R.id.areaId) {
        Intent intent = new Intent(SearchResultActivity.this, FirebaseAreaSearchActivity.class);
        startActivity(intent);
    }
    else if (v.getId()==R.id.latestId) {
        Intent intent = new Intent(SearchResultActivity.this, FirebaseLatestSearchActivity.class);
        startActivity(intent);
    }
    else if (v.getId()==R.id.priceId) {
        Intent intent = new Intent(SearchResultActivity.this, FirebasePriceSearchActivity.class);
        startActivity(intent);
    }
    else if (v.getId()==R.id.bedId) {
        Intent intent = new Intent(SearchResultActivity.this, FirebaseBedSearchActivity.class);
        startActivity(intent);
    }
    else if (v.getId()==R.id.ratingId) {
        Intent intent = new Intent(SearchResultActivity.this, FirebaseRatingSearchActivity.class);
        startActivity(intent);
    }
}*/

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postInformationList.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    PostInformation postInformation=dataSnapshot1.getValue(PostInformation.class);
                    customPostDownloadApapter=new CustomPostDownloadApapter(SearchResultActivity.this,postInformationList);

                    postInformationList.add(postInformation);
                    //adapter=new ArrayAdapter(ProfileActivity.this,R.layout.sample_profile_layout,userInformationList);
                    // listView.setAdapter(adapter);

                }
                listView.setAdapter(customPostDownloadApapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }



    }

